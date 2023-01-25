package ru.skypro.homework.service;

import org.springframework.stereotype.Repository;

import java.io.File;

public interface AdsImageService {

    void updateAdImage(Long id, File file);

    public void updateAdsImage(Long id, File file);


}
