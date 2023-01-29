package ru.skypro.homework.repository;

import liquibase.license.LicenseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findUserById(Long userId);

//    User save(String currentPassword, String newPassword);
}
