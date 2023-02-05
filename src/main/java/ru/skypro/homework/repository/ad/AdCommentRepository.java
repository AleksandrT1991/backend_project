package ru.skypro.homework.repository.ad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.entity.AdComment;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdCommentRepository extends JpaRepository<AdComment, Long> {
    Optional<AdComment> findByIdAndPk(Long id, Long pk);
    List<AdComment> findAllByPk(Long pk);

    AdComment findByPk(Long adPk);

    void deleteByIdAndPk(Long id, Long adPk);
}
