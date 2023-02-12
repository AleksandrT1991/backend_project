package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.repository.ad.AdImageRepository;
import ru.skypro.homework.repository.ad.AdRepository;
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

    public AdImage findAdImage(Long adPk) {
        Optional<AdImage> adImage = Optional.ofNullable(adImageRepository.findByAdPk(adPk));
        return adImage.orElse(new AdImage());
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Override
    public void updateAdsImage(Long id, MultipartFile image) throws IOException {

        Path filePath = Path.of(imageDir, id + "." + getExtension(Objects.requireNonNull(image.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        AdImage adImage = findAdImage(id);
        adImage.setFilePath(filePath.toString());
        adImage.setFileSize(image.getSize());
        adImage.setMediaType(image.getContentType());

        adImageRepository.save(adImage);
    }

    @Override
    public void createImage(MultipartFile image) throws IOException {
        Path filePath = Path.of(imageDir,  image.getName() + "." + getExtension(Objects.requireNonNull(image.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = image.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        AdImage adImage = new AdImage();
        adImage.setFilePath(filePath.toString());
        adImage.setFileSize(image.getSize());
        adImage.setMediaType(image.getContentType());

        adImageRepository.save(adImage);
    }
}
