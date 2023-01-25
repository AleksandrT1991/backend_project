package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.repository.AdCommentRepository;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdsService;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdRepository adRepository;
    private final AdCommentRepository adCommentRepository;

    public AdsServiceImpl(AdRepository adRepository, AdCommentRepository adCommentRepository) {
        this.adRepository = adRepository;
        this.adCommentRepository = adCommentRepository;
    }

    public void getAd(AdDto ad) {
        adRepository.findById(ad.getId());
    }

    public void addAd(AdDto ad) {
        adRepository.save(AdMapper.INSTANCE.toEntity(ad));
    }

    @Override
    public void getComments(String adPk) {

    }

//    public List<AdComment> getComments(String adPk) {
//        Ad ad = adRepository.findByPk(adPk);
//    }

    public void addComments(String adPk) {
        
    }

    public void getFullAd(Long id) {
        adRepository.findById(id);
    }

    public void removeAds(Long id) {
        
    }

    public void updateAds(Long id) {
        
    }

    public void getComments(Long id, String adPk) {
        
    }

    public void deleteComments(Long id, String adPk) {
        
    }

    public void updateComments(Long id, String adPk) {
        
    }

    public void getAdsMe(AdDto ad) {
        
    }
}
