package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;
import ru.skypro.homework.mappers.user.UserMapper;
import ru.skypro.homework.repository.UserImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.CredentialNotFoundException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.Files.createDirectories;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserImageRepository userImageRepository;
    /**
     * event recording process
     */
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, UserImageRepository userImageRepository) {
        this.userRepository = userRepository;
        this.userImageRepository = userImageRepository;
    }

    @Value("${user.image.dir.path}")
    private String imageDir;

    /**
     * event recording process
     * @param passwordDto
     * we take the user's password from the data badly and compare it with the current one, if the passwords match, change the password
     * @return passwordDto
     * @throws CredentialNotFoundException
     */
    @Override
    public PasswordDto setPassword(PasswordDto passwordDto) throws CredentialNotFoundException {
        logger.info("Metod\"UserServiceImpl.setPassword()\" was called");
        Long userId = 1L;
        Optional<User> user = Optional.ofNullable(userRepository.findUserById(userId).orElseThrow(EntityNotFoundException::new));
        if (user.isPresent()) {
            if (passwordDto.getCurrentPassword().equals(user.get().getPassword())) {
                user.get().setPassword(passwordDto.getNewPassword());
                userRepository.save(user.get());
            } else throw new CredentialNotFoundException();
        }
        return passwordDto;
    }

    /**
     * event recording process
     * @return
     */
    @Override
    public UserDto getUser() {
        logger.info("Metod\"UserServiceImpl.getUser()\" was called");
        Long userId = 1L;
        return UserMapper.INSTANCE.toDto(
                userRepository.findUserById(userId)
                        .orElseThrow(RuntimeException::new));
    }

    @Override
    public Optional<UserDto> updateUser(UserDto userDto) {
        logger.info("Metod\"UserServiceImpl.updateUser()\" was called");
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
        logger.info("Metod\"UserServiceImpl.updateUserImage()\" was called");
        Long userId = 1L;
        Optional<User> user = userRepository.findById(userId);
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

    private String getExtension(String fileName) {
        logger.info("Metod\"UserServiceImpl.getExtension()\" was called");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
