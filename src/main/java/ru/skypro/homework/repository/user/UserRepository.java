package ru.skypro.homework.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findUserById(Long userId);

    Optional<User> findByUserName(String userName);

    void deleteByUserName(String userName);

//    User save(String currentPassword, String newPassword);
}
