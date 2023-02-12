package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;
import ru.skypro.homework.mappers.user.UserMapper;
import ru.skypro.homework.repository.user.UserImageRepository;
import ru.skypro.homework.repository.user.UserRepository;
import ru.skypro.homework.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.Files.createDirectories;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserImageRepository userImageRepository;


    public UserServiceImpl(UserRepository userRepository, UserImageRepository userImageRepository) {
        this.userRepository = userRepository;
        this.userImageRepository = userImageRepository;
    }

    @Value("${user.image.dir.path}")
    private String imageDir;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUser(Long userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public void deleteByUserName(String userName) {
        userRepository.deleteByUsername(userName);

    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDto> updateUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.toEntity(userDto);
        Optional<User> optional = userRepository.findById(user.getId());
        if (!optional.isPresent()) {
            return Optional.empty();
        } else {
            user.setId(optional.get().getId());
            return Optional.of(UserMapper.INSTANCE.toDto(userRepository.save(user)));
        }
    }

    @Override
    public void updateUserImage(MultipartFile file) throws IOException {
        Optional<User> user = userRepository.findById(1L);
        Path filePath = Path.of(imageDir, file.getName() + "." + getExtension(Objects.requireNonNull(file.getOriginalFilename())));
        createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        UserImage userImage = new UserImage();
        userImage.setUser(user.orElse(new User()));
        userImage.setFilePath(filePath.toString());
        userImage.setFileSize(file.getSize());
        userImage.setMediaType(file.getContentType());
        userImageRepository.save(userImage);
    }

    @Override
    public PasswordDto setPassword(PasswordDto passwordDto) {
        Long userId = 1L;
        Optional<User> user = Optional.ofNullable(userRepository.findUserById(userId).orElseThrow(() -> new EntityNotFoundException()));
        if (user.isPresent()) {
            if (passwordDto.getCurrentPassword().equals(user.get().getPassword())) {
                user.get().setPassword(passwordDto.getNewPassword());
                userRepository.save(user.get());
            }
        }
        return passwordDto;
    }

    @Override
    public UserDto getUser() {
        Long userId = 1L;
        return UserMapper.INSTANCE.toDto(
                userRepository.findUserById(userId)
                        .orElseThrow(RuntimeException::new));
    }

    //    private String getExtensions(String fileName) {
//        return fileName.substring(fileName.lastIndexOf(".") + 1);
//    }
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
//    @Override
//    public void setPassword() {
//        userRepository.setPassword();
//    }
}
