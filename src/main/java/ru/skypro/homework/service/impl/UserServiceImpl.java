package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;
import ru.skypro.homework.exception.UserEntersIncorrectPassword;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mappers.user.UserMapper;
import ru.skypro.homework.repository.UserImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUser;
import ru.skypro.homework.security.MyUserDetailsService;
import ru.skypro.homework.service.UserService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.Files.createDirectories;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static ru.skypro.homework.mappers.user.UserMapper.toDto;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserImageRepository userImageRepository;
    public final  UserMapper userMapper;
    private final MyUserDetailsService manager;
    private final PasswordEncoder passwordEncoder;
    /**
     * event recording process
     */
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, UserImageRepository userImageRepository, UserMapper userMapper, MyUserDetailsService manager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userImageRepository = userImageRepository;
        this.userMapper = userMapper;
        this.manager = manager;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${user.image.dir.path}")
    private String imageDir;

    /**
     * event recording process
     * @param passwordDto
     * we take the user's password from the data badly and compare it with the current one, if the passwords match, change the password
     * @return passwordDto
     */
    @Override
    public void setPassword(PasswordDto passwordDto, MyUser myUser) {
        logger.info("Metod\"UserServiceImpl.setPassword()\" was called");
        String newPass = myUser.getPassword();
        if (passwordEncoder.matches(passwordDto.getCurrentPassword(), newPass)) {
            User user = myUser.getUser();
            user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
            userRepository.save(user);
        }else {
            throw new UserEntersIncorrectPassword("incorrect password");
        }
    }


//    @Override
////    public UserDto getUser() {
////        return null;
////    }

    /**
     * event recording process
     * @return
     */
    @Override
    public User getUser(String username) {
        logger.info("Metod\"UserServiceImpl.getUser()\" was called");
        return userRepository.findUserByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String username) {
        logger.info("Metod\"UserServiceImpl.updateUser()\" was called");
        User user = getUser(username);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setCity(userDto.getCity());
        User response = userRepository.save(user);
        return userMapper.toDto(response);

    }

    @Override
    public UserDto getUserDtoByUsername(String username) {
        User responce = getUser(username);
        return toDto(responce);
    }

    @Override
    public void updateUserImage(String username, MultipartFile image) throws IOException {
        logger.info("Metod\"UserServiceImpl.updateUserImage()\" was called");
        Path filePath = Path.of(imageDir, image.getName() + "." + getExtension(Objects.requireNonNull(image.getOriginalFilename())));
        createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        User imageUser = getUser(username);
        UserImage userImage = userImageRepository.findByUserId(imageUser.getId()).orElse(new UserImage());
        userImage.setFilePath(filePath.toString());
        userImage.setFileSize(image.getSize());
        userImage.setBytea(image.getBytes());
        userImage.setMediaType(image.getContentType());
        userImage.setUser(imageUser);
        userImageRepository.save(userImage);
    }

    private String getExtension(String fileName) {
        logger.info("Metod\"UserServiceImpl.getExtension()\" was called");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
