package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdImage;

import javax.transaction.Transactional;

/**
 * The interface Ad image repository.
 */
@Repository
@Transactional
public interface AdImageRepository extends JpaRepository<AdImage, Long> {
    /**
     * Find by ad pk ad image.
     *
     * @param adPk the ad pk
     * @return the ad image
     */
    AdImage findByAdPk(Long adPk);

    /**
     * Delete ad image by ad pk pk.
     *
     * @param pk the pk
     */
    void deleteAdImageByAdPk_Pk(Long pk);
}
