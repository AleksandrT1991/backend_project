package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

//     User setPassword(String currentPassword, String newPassword);
//
//     void updateUserImage(Long userId, MultipartFile file);

     User addUser(User user);

     List<User> getAllUser();

     Optional<User> findUser(Long userId);

     void deleteByUserName (String userName);

     void deleteById(Long id);

     Optional<UserDto> updateUser (UserDto userDto);

     void updateUserImage(MultipartFile userImage) throws IOException;

     PasswordDto setPassword(PasswordDto passwordDto);

     UserDto getUser();

//     void setPassword(String currentPassword, String newPassword);
}
