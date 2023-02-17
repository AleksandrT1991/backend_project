package ru.skypro.homework.dto.user;

import lombok.Data;

/**
 * The type Login req.
 */
@Data
public class LoginReq {

    private String password;
    private String username;
}
