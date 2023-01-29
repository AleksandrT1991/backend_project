package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

//     User setPassword(String currentPassword, String newPassword);
//
//     void updateUserImage(Long userId, MultipartFile file);

     User addUser(User user);

     List<User> getAllUser();

     Optional<User> findUser(Long userId);


}
