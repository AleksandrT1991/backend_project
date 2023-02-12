package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdComment;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AdCommentRepository extends JpaRepository<AdComment, Long> {

    List<AdComment> findAllByPk(Long adPk);

    Optional<AdComment> findByIdAndPk(Long id, Long adPk);

    void deleteByIdAndPk(Long id, Long adPk);

    void deleteAdCommentByPk(Long pk);
}
