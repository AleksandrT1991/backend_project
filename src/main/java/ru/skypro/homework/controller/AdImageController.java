package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.service.AdsImageService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class AdImageController {

    private final AdsImageService adsImageService;

    private final Logger logger = LoggerFactory.getLogger(AdImageController.class);
    /**
     * event recording process
     */

    @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateAdsImage(@PathVariable Long id, @RequestParam MultipartFile image) throws IOException {
        logger.info("Controller\"AdImageController.updateAdsImage()\" was called");
        adsImageService.updateAdsImage(id, image);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] getAdsImage(@PathVariable Long id) {
        logger.info("Controller\"AdImageController.getAdsImage()\" was called");
        return adsImageService.getAdsImage(id);
    }



}
