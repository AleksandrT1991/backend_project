package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Ads not found exception.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AdsNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Ads not found exception.
     *
     * @param str the str
     */
    public AdsNotFoundException(String str) {
        super (str);
    }
}
