package ru.skypro.homework.dto.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Data
public class LoginReq {

    private String password;
    private String username;
}
