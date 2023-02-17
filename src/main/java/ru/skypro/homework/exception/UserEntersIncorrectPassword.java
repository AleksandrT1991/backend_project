package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type User enters incorrect password.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserEntersIncorrectPassword extends RuntimeException {
    /**
     * Instantiates a new User enters incorrect password.
     *
     * @param message the message
     */
    public UserEntersIncorrectPassword(String message) {
        super(message);
    }
}
