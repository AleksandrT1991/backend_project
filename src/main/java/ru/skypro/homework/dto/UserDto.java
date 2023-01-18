package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserDto {

    private String phone;
    private String lastName;
    private String firstName;
    private String email;
    private Integer id;
}
