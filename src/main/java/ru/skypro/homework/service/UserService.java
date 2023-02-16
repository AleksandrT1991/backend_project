package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;

import javax.security.auth.login.CredentialNotFoundException;
import java.io.IOException;
import java.util.Optional;

public interface UserService {

    PasswordDto setPassword(PasswordDto passwordDto) throws CredentialNotFoundException;
//
//    UserDto getUser();

    User getUser(String username);

    Optional<UserDto> updateUser(UserDto userDto);

    UserDto getUserDtoByUsername(String username);

    void updateUserImage(String username, MultipartFile image) throws IOException;
}
