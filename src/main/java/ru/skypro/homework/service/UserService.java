package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.security.MyUser;

import javax.security.auth.login.CredentialNotFoundException;
import java.io.IOException;

public interface UserService {

    void setPassword(PasswordDto passwordDto, MyUser myUser) throws CredentialNotFoundException;
//
//    UserDto getUser();

    User getUser(String username);

    UserDto updateUser(UserDto userDto, String username);

    UserDto getUserDtoByUsername(String username);

    void updateUserImage(String username, MultipartFile image) throws IOException;
}
