package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateAdDto;
import ru.skypro.homework.dto.ad.FullAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.mappers.ad.AdsMapper;
import ru.skypro.homework.mappers.ad.CreateAdsMapper;
import ru.skypro.homework.mappers.ad.FullAdMapper;
import ru.skypro.homework.repository.ad.AdCommentRepository;
import ru.skypro.homework.repository.ad.AdRepository;
import ru.skypro.homework.service.AdsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdRepository adRepository;
    private final AdCommentRepository adCommentRepository;
    private final AdsMapper adsMapper;

    public AdsServiceImpl(AdRepository adRepository, AdCommentRepository adCommentRepository,
                          AdsMapper adsMapper) {
        this.adRepository = adRepository;
        this.adCommentRepository = adCommentRepository;
        this.adsMapper = adsMapper;
    }

    @Override
    public List<AdDto> getAds() {
        return adRepository.findAll().stream().map(adsMapper::toDto).collect(Collectors.toList());
    }

    public void addAd(AdDto ad) {
        adRepository.save(AdsMapper.INSTANCE.toEntity(ad));
    }

    @Override
    public Ad createAdd(CreateAdDto createAdDto) {
        return adRepository.save(CreateAdsMapper.INSTANCE.toEntity(createAdDto));
    }

    @Override
    public void getComments(String adPk) {

    }

//    public List<AdComment> getComments(String adPk) {
//        Ad ad = adRepository.findByPk(adPk);
//    }

    public void addComments(String adPk) {
        
    }

    public FullAdDto getFullAd(Long id) {
        return FullAdMapper.INSTANCE.toDto(adRepository.findById(id).get());
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
