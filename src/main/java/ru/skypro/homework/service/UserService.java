package ru.skypro.homework.service;

import org.springframework.stereotype.Repository;

import java.io.File;

public interface UserService {

    public void setPassword(String currentPassword, String newPassword);

    public void updateUserImage(File file);
}
