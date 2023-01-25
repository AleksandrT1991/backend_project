package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.repository.AdImageRepository;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdsImageService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AdsImageServiceImpl implements AdsImageService {

    private final Logger logger = LoggerFactory.getLogger(AdsImageServiceImpl.class);

    private final AdImageRepository adImageRepository;
    private final AdRepository adRepository;

    @Value("${ad.image.dir.path}")
    private String imageDir;

    public AdsImageServiceImpl(AdImageRepository adImageRepository, AdRepository adRepository) {
        this.adImageRepository = adImageRepository;
        this.adRepository = adRepository;
    }

    public void uploadPhoto(Long adId, MultipartFile file) throws IOException {
        Optional<Ad> ad = adRepository.findAdById(adId);

        Path filePath = Path.of(imageDir, adId + "." + getExtension(Objects.requireNonNull(file.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        AdImage adImage = findAdImage(adId);
        adImage.setAdId(adId);
        adImage.setFilePath(filePath.toString());
        adImage.setFileSize(file.getSize());
        adImage.setMediaType(file.getContentType());

        adImageRepository.save(adImage);
    }

    public AdImage findAdImage(Long adId) {
        Optional<AdImage> userImageByUserId = Optional.ofNullable(adImageRepository.findByAdId(adId));
        return userImageByUserId.orElse(new AdImage());
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

    @Override
    public void updateAdImage(Long id, File file) {

    }
    public void updateAdsImage(Long id, File file) {
        Optional<AdImage> adImage = adImageRepository.findById(id);
        adImageRepository.save(adImage.get());
    }
}
