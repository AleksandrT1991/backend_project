package ru.skypro.homework.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;

@Repository
public interface AuthService{
    boolean login(String userName, String password);
    boolean register(RegisterReq registerReq, Role role);
}
