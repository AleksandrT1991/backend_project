package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdImage;

import java.io.IOException;

/**
 * The interface Ads image service.
 */
public interface AdsImageService {

    /**
     * Create image ad image.
     *
     * @param image the image
     * @return the ad image
     * @throws IOException the io exception
     */
    AdImage createImage(MultipartFile image) throws IOException;

    /**
     * Update ads image.
     *
     * @param id    the id
     * @param image the image
     * @throws IOException the io exception
     */
    void updateAdsImage(Long id, MultipartFile image) throws IOException;

    /**
     * Get ads image byte [ ].
     *
     * @param id the id
     * @return the byte [ ]
     */
    byte[] getAdsImage(Long id);
}
