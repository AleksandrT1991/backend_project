package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.entity.UserImage;

import java.util.Optional;

/**
 * The interface User image repository.
 */
@Repository
@Transactional
public interface UserImageRepository extends JpaRepository<UserImage, Long> {

    /**
     * Find by user id optional.
     *
     * @param userId the user id
     * @return the optional
     */
    Optional<UserImage> findByUserId (Long userId);
}
