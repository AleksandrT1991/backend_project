package ru.skypro.homework.repository.ad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.entity.Ad;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    Ad findByPk(Long pk);

    Optional<Ad> findAdById(Long adId);

    void deleteAdByPk(Long pk);

    List<Ad> findAllByAuthor(Long author);
}
