package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type User not found exception.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     * Instantiates a new User not found exception.
     *
     * @param str the str
     */
    public UserNotFoundException(String str) {
        super(str);
    }

    /**
     * Instantiates a new User not found exception.
     */
    public UserNotFoundException() {
        super(" Пользователь не найден");
    }
}
