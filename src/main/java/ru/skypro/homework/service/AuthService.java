package ru.skypro.homework.service;

import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.user.RegisterReq;
import ru.skypro.homework.dto.enums.Role;

@Repository
public interface AuthService{
    boolean login(String userName, String password);

    boolean register(RegisterReq registerReq);
}
