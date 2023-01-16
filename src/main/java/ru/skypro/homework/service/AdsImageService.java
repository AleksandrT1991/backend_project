package ru.skypro.homework.service;

import org.springframework.stereotype.Repository;

import java.io.File;

public interface AdsImageService {

    public void updateUserImage(String id, File file);
}
