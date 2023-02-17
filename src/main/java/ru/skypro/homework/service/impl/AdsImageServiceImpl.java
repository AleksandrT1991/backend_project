package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.repository.AdImageRepository;
import ru.skypro.homework.service.AdsImageService;

import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * The type Ads image service.
 */
@Service
public class AdsImageServiceImpl implements AdsImageService {

    @Value("${ad.image.dir.path}")
    private String imageDir;

    private final AdImageRepository adImageRepository;

    /**
     * event recording process
     */
    private final Logger logger = LoggerFactory.getLogger(AdsImageServiceImpl.class);

    /**
     * Instantiates a new Ads image service.
     *
     * @param adImageRepository the ad image repository
     */
    public AdsImageServiceImpl(AdImageRepository adImageRepository) {
        this.adImageRepository = adImageRepository;
    }

    @Override
    public AdImage createImage(MultipartFile image) throws IOException {
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
        adImage.setBytea(image.getBytes());
        adImage.setFilePath(filePath.toString());
        adImage.setFileSize(image.getSize());
        adImage.setMediaType(image.getContentType());

        return adImageRepository.save(adImage);
    }

    @Override
    public void updateAdsImage(Long id, MultipartFile image) throws IOException {
        logger.info("Metod\"AdsImageServiceImpl.updateAdsImage()\" was called");
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
        Optional<AdImage> adImage = adImageRepository.findById(id);
        if (adImage.isPresent()) {
            adImage.get().setBytea(image.getBytes());
            adImage.get().setFilePath(filePath.toString());
            adImage.get().setFileSize(image.getSize());
            adImage.get().setMediaType(image.getContentType());
        } else {
            throw new EntityNotFoundException();
        }

        adImageRepository.save(adImage.get());
    }

    @Override
    public byte[] getAdsImage(Long id) {
        logger.info("Metod\"AdsImageServiceImpl.findAdImage()\" was called");
        Optional<AdImage> adImage = adImageRepository.findById(id);
        return adImage.map(AdImage::getBytea).orElse(null);
    }

    private String getExtension(String fileName) {
        logger.info("Metod\"AdsImageServiceImpl.getExtension()\" was called");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * Find ad image ad image.
     *
     * @param id the id
     * @return the ad image
     */
    public AdImage findAdImage(Long id) {
        logger.info("Metod\"AdsImageServiceImpl.findAdImage()\" was called");
        Optional<AdImage> adImage = adImageRepository.findById(id);
        return adImage.orElseThrow(EntityNotFoundException::new);
    }

//    public List<AdImage> findAdImages(Long id) {
//        logger.info("Metod\"AdsImageServiceImpl.findAdImage()\" was called");
//        Optional<AdImage> adImage = adImageRepository.findById(id);
//        return adImage.orElseThrow(() -> new EntityNotFoundException());
//    }
}
