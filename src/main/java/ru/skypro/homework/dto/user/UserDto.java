package ru.skypro.homework.dto.user;

import lombok.Data;

/**
 * The type User dto.
 */
@Data
public class UserDto {

    private String email;
    private String firstName;
    private Long id;
    private String lastName;
    private String phone;
    private String regDate;
    private String city;
    private String image;
}
