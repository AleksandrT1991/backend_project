package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface UserImageService {

    void uploadPhoto(Long userId, MultipartFile file) throws IOException;

}
