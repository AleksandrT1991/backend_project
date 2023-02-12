package ru.skypro.homework.service;

import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateAdsDto;
import ru.skypro.homework.dto.ad.FullAdDto;
import ru.skypro.homework.dto.wrappers.ResponseWrapperAds;
import ru.skypro.homework.dto.wrappers.ResponseWrapperComments;

import java.util.List;

public interface AdsService {
    ResponseWrapperAds getAds();

    AdDto createAd(CreateAdsDto createAdsDto);

    ResponseWrapperComments getComments(Long adPk);

    AdCommentDto addComments(Long adPk, AdCommentDto adCommentDto);

    FullAdDto getFullAd(Long id);

    void removeAds(Long pk);

    AdDto updateAds(Long id, CreateAdsDto adDto);

    AdCommentDto getComments(Long id, Long adPk);

    void deleteComments(Long id, Long adPk);

    AdCommentDto updateComments(Long id, Long adPk, AdCommentDto adCommentDto);

    ResponseWrapperAds getAdsMe();
}
