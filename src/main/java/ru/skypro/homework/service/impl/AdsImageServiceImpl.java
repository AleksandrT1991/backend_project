package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.repository.AdImageRepository;
import ru.skypro.homework.service.AdsImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AdsImageServiceImpl implements AdsImageService {

    @Value("${ad.image.dir.path}")
    private String imageDir;

    private final AdImageRepository adImageRepository;

    /**
     * event recording process
     */
    private final Logger logger = LoggerFactory.getLogger(AdsImageServiceImpl.class);

    public AdsImageServiceImpl(AdImageRepository adImageRepository) {
        this.adImageRepository = adImageRepository;
    }

    @Override
    public void createImage(MultipartFile image) throws IOException {
        logger.info("Metod\"AdsImageServiceImpl.createImage()\" was called");
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

    @Override
    public void updateAdsImage(Long id, MultipartFile image) throws IOException {
        logger.info("Metod\"AdsImageServiceImpl.updateAdsImage()\" was called");
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

    private String getExtension(String fileName) {
        logger.info("Metod\"AdsImageServiceImpl.getExtension()\" was called");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public AdImage findAdImage(Long adPk) {
        logger.info("Metod\"AdsImageServiceImpl.findAdImage()\" was called");
        Optional<AdImage> adImage = Optional.ofNullable(adImageRepository.findByAdPk(adPk));
        return adImage.orElse(new AdImage());
    }
}
