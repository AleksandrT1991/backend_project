package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdComment;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * The interface Ad comment repository.
 */
@Repository
@Transactional
public interface AdCommentRepository extends JpaRepository<AdComment, Long> {

    /**
     * Find all by pk list.
     *
     * @param adPk the ad pk
     * @return the list
     */
    List<AdComment> findAllByPk(Long adPk);

    /**
     * Find by pk and id optional.
     *
     * @param pk the pk
     * @param id the id
     * @return the optional
     */
    Optional<AdComment> findByPkAndId(Long pk, Long id);

    /**
     * Delete by id and pk.
     *
     * @param id   the id
     * @param adPk the ad pk
     */
    void deleteByIdAndPk(Long id, Long adPk);

    /**
     * Delete ad comment by pk.
     *
     * @param pk the pk
     */
    void deleteAdCommentByPk(Long pk);
}
