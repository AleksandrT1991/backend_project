package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;
import ru.skypro.homework.repository.user.UserImageRepository;
import ru.skypro.homework.repository.user.UserRepository;
import ru.skypro.homework.service.UserImageService;
import ru.skypro.homework.service.UserService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import static io.swagger.v3.core.util.AnnotationsUtils.getExtensions;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class UserImageServiceImpl implements UserImageService {

    private final UserImageRepository userImageRepository;
    private final UserService userService;

    public UserImageServiceImpl(UserImageRepository userImageRepository, UserService userService) {
        this.userImageRepository = userImageRepository;
        this.userService = userService;
    }
    @Value("${user.image.dir.path}")
    private String imageDir;

    @Override
    public void uploadPhoto(Long userId, MultipartFile file) throws IOException {
        Optional<User> user = userService.findUser(userId);
        Path filePath = Path.of(imageDir, userId + "." + getExtensions(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        UserImage userImage = findUserImage(userId);
        userImage.setUserId(userId);
        userImage.setFilePath(filePath.toString());
        userImage.setFileSize(file.getSize());
        userImage.setMediaType(file.getContentType());

        userImageRepository.save(userImage);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public UserImage findUserImage(Long userId) {
        Optional<UserImage> userImageByUserId = Optional.ofNullable(userImageRepository.findByUserId(userId));
        return userImageByUserId.orElse(new UserImage());
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private byte[] generateImagePreview(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }
}
