package ru.skypro.homework.service;

import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateAdDto;
import ru.skypro.homework.dto.ad.FullAdDto;
import ru.skypro.homework.entity.Ad;

import java.util.List;

public interface AdsService {

    public List<AdDto> getAds();

    public void addAd(AdDto ad);

    public Ad createAdd(CreateAdDto createAdDto);

    public void getComments(String adPk);

    public void addComments(String adPk);

    public FullAdDto getFullAd(Long id);

    public void removeAds(Long id);

    public void updateAds(Long id);

    public void getComments(Long id, String adPk);

    public void deleteComments(Long id, String adPk);

    public void updateComments(Long id, String adPk) ;

    public void getAdsMe(AdDto ad);
}
