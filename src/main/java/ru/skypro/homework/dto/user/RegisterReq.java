package ru.skypro.homework.dto.user;

import lombok.Data;

/**
 * The type Register req.
 */
@Data
public class RegisterReq {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
}
