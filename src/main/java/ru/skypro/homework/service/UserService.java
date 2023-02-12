package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;

import javax.security.auth.login.CredentialNotFoundException;
import java.io.IOException;
import java.util.Optional;

public interface UserService {

    PasswordDto setPassword(PasswordDto passwordDto) throws CredentialNotFoundException;

    UserDto getUser();

    Optional<UserDto> updateUser(UserDto userDto);

    void updateUserImage(MultipartFile image) throws IOException;
}
