package ru.skypro.homework.service;

import org.springframework.stereotype.Repository;

import java.io.File;

public interface AdsImageService {

    public void updateAdsImage(Long id, File file);


}
