package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.entity.UserImage;
import ru.skypro.homework.repository.AdImageRepository;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserImageRepository;
import ru.skypro.homework.service.AdsImageService;

import java.io.File;
import java.util.Optional;

@Service
public class AdsImageServiceImpl implements AdsImageService {

    private final Logger logger = LoggerFactory.getLogger(AdsImageServiceImpl.class);

    private final AdImageRepository adImageRepository;

    public AdsImageServiceImpl(AdImageRepository adImageRepository) {
        this.adImageRepository = adImageRepository;
    }

    public void updateAdsImage(Long id, File file) {
        Optional<AdImage> adImage = adImageRepository.findById(id);
        adImageRepository.save(adImage.get());
    }
}
