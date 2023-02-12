package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdsImageService {

    void updateAdsImage(Long id, MultipartFile image) throws IOException;
    void createImage(MultipartFile file) throws IOException;
}
