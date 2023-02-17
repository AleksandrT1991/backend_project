package ru.skypro.homework.service;

import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateAdsDto;
import ru.skypro.homework.dto.ad.FullAdDto;
import ru.skypro.homework.dto.wrappers.ResponseWrapperAds;
import ru.skypro.homework.dto.wrappers.ResponseWrapperComments;
import ru.skypro.homework.entity.AdImage;

import java.util.List;
import java.util.Optional;

/**
 * The interface Ads service.
 */
public interface AdsService {
    /**
     * Gets ads.
     *
     * @return the ads
     */
    ResponseWrapperAds getAds();

    /**
     * Create ad ad dto.
     *
     * @param createAdsDto the create ads dto
     * @param imageSaved   the image saved
     * @return the ad dto
     */
    AdDto createAd(CreateAdsDto createAdsDto, List<AdImage> imageSaved);

    /**
     * Gets comments.
     *
     * @param adPk the ad pk
     * @return the comments
     */
    ResponseWrapperComments getComments(Long adPk);

    /**
     * Add comments ad comment dto.
     *
     * @param adPk         the ad pk
     * @param adCommentDto the ad comment dto
     * @return the ad comment dto
     */
    AdCommentDto addComments(Long adPk, AdCommentDto adCommentDto);

    /**
     * Gets full ad.
     *
     * @param id the id
     * @return the full ad
     */
    FullAdDto getFullAd(Long id);

    /**
     * Remove ads.
     *
     * @param pk the pk
     */
    void removeAds(Long pk);

    /**
     * Update ads ad dto.
     *
     * @param id    the id
     * @param adDto the ad dto
     * @return the ad dto
     */
    AdDto updateAds(Long id, CreateAdsDto adDto);

    /**
     * Gets comments.
     *
     * @param adPk the ad pk
     * @param id   the id
     * @return the comments
     */
    AdCommentDto getComments(Long adPk, Long id);

    /**
     * Delete comments.
     *
     * @param id   the id
     * @param adPk the ad pk
     */
    void deleteComments(Long id, Long adPk);

    /**
     * Update comments ad comment dto.
     *
     * @param id           the id
     * @param adPk         the ad pk
     * @param adCommentDto the ad comment dto
     * @return the ad comment dto
     */
    AdCommentDto updateComments(Long id, Long adPk, AdCommentDto adCommentDto);

    /**
     * Gets ads me.
     *
     * @param username the username
     * @return the ads me
     */
    ResponseWrapperAds getAdsMe(String username);
}
