package ru.skypro.homework.repository.ad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.entity.UserImage;

@Repository
public interface AdImageRepository extends JpaRepository<AdImage, Long> {
    AdImage findByAdId(Long adId);
}