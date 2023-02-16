package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdImage;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AdImageRepository extends JpaRepository<AdImage, Long> {
    AdImage findByAdPk(Long adPk);

    void deleteAdImageByAdPk_Pk(Long pk);
}
