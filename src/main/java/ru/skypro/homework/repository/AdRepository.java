package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Ad;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * The interface Ad repository.
 */
@Repository
@Transactional
public interface AdRepository extends JpaRepository<Ad, Long> {
    Optional<Ad> findByImage_Id(Long id);


    /**
     * Find by pk ad.
     *
     * @param id the id
     * @return the ad
     */
    Ad findByPk(Long id);

    /**
     * Delete ad by pk.
     *
     * @param pk the pk
     */
    void deleteAdByPk(Long pk);

    /**
     * Find all by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    List<Ad> findAllByUser_Id(Long userId);
}
