package ru.skypro.homework.service;

import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateAdDto;
import ru.skypro.homework.dto.ad.FullAdDto;
import ru.skypro.homework.dto.wrappers.ResponseWrapperAds;
import ru.skypro.homework.dto.wrappers.ResponseWrapperComments;

import java.util.List;
import java.util.Optional;

public interface AdsService {

    public ResponseWrapperAds getAds();

    public AdDto createAdd(CreateAdDto createAdDto);

    public ResponseWrapperComments getComments(Long adPk);

    public AdCommentDto addComments(Long adPk, AdCommentDto adCommentDto);

    public FullAdDto getFullAd(Long id);

    public void removeAds(Long id);

    public AdDto updateAds(Long id, CreateAdDto createAdDto);

    public void deleteComments(Long id, Long adPk);

    public AdCommentDto updateComments(Long id, Long adPk, AdCommentDto adCommentDto) ;

    Optional<AdCommentDto> getComments(Long id, Long adPk);

    public ResponseWrapperAds getAdsMe();
}
