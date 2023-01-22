package ru.skypro.homework.dto.user;

import lombok.Data;

@Data
public class UserDto {

    private String phone;
    private String lastName;
    private String firstName;
    private String email;
    private Integer id;
    private String username;
    private String password;
}
