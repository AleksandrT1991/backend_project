package ru.skypro.homework.service;

import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.user.RegisterReq;

/**
 * The interface Auth service.
 */
@Repository
public interface AuthService{

    /**
     * Login boolean.
     *
     * @param userName the user name
     * @param password the password
     * @return the boolean
     */
    boolean login(String userName, String password);

    /**
     * Register boolean.
     *
     * @param registerReq the register req
     * @return the boolean
     */
    boolean register(RegisterReq registerReq);
}
