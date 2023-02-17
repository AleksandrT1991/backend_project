package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.security.MyUser;

import javax.security.auth.login.CredentialNotFoundException;
import java.io.IOException;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Sets password.
     *
     * @param passwordDto the password dto
     * @param myUser      the my user
     * @throws CredentialNotFoundException the credential not found exception
     */
    void setPassword(PasswordDto passwordDto, MyUser myUser) throws CredentialNotFoundException;
//
//    UserDto getUser();

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    User getUser(String username);

    /**
     * Update user user dto.
     *
     * @param userDto  the user dto
     * @param username the username
     * @return the user dto
     */
    UserDto updateUser(UserDto userDto, String username);

    /**
     * Gets user dto by username.
     *
     * @param username the username
     * @return the user dto by username
     */
    UserDto getUserDtoByUsername(String username);

    /**
     * Update user image.
     *
     * @param username the username
     * @param image    the image
     * @throws IOException the io exception
     */
    void updateUserImage(String username, MultipartFile image) throws IOException;
}
