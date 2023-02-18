package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.entity.User;

import java.util.Optional;

/**
 * The interface User repository.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find user by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<User> findUserByUsername(String username);
}
