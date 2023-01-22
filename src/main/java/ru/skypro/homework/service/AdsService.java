package ru.skypro.homework.service;

import ru.skypro.homework.dto.ad.AdDto;

public interface AdsService {

    public void getAd(AdDto ad);

    public void addAd(AdDto ad);

    public void getComments(String adPk);

    public void addComments(String adPk);

    public void getFullAd(Long id);

    public void removeAds(Long id);

    public void updateAds(Long id);

    public void getComments(Long id, String adPk);

    public void deleteComments(Long id, String adPk);

    public void updateComments(Long id, String adPk) ;

    public void getAdsMe(AdDto ad);
}
