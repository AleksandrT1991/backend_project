package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdImage;

import java.io.IOException;
import java.util.List;

public interface AdsImageService {

    AdImage createImage(MultipartFile image) throws IOException;

    void updateAdsImage(Long id, MultipartFile image) throws IOException;

    byte[] getAdsImage(Long id);
}
