package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;

import java.util.Optional;

@Repository
public interface UserImageRepository extends JpaRepository <UserImage, Long> {
    UserImage findByUserId(Long userId);
}
