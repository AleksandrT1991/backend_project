package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class LoginReq {

    private String password;
    private String username;
}
