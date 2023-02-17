package ru.skypro.homework.dto.user;

import lombok.Data;

/**
 * The type Password dto.
 */
@Data
public class PasswordDto {

    private String currentPassword;
    private String newPassword;
}
