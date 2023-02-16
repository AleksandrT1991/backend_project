package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.entity.Ad;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AdRepository extends JpaRepository<Ad, Long> {


    Ad findByPk(Long id);

    void deleteAdByPk(Long pk);

    List<Ad> findAllByUser_Id(Long userId);
}
