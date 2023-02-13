package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdsImageService {

    void createImage(MultipartFile image) throws IOException;

    void updateAdsImage(Long id, MultipartFile image) throws IOException;
}
