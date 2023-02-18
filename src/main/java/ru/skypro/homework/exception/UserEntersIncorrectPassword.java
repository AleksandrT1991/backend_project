package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserEntersIncorrectPassword extends RuntimeException {
    public UserEntersIncorrectPassword(String message) {
        super(message);
    }
}
