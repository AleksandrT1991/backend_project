package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.service.impl.AdsServiceImpl;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

    private final AdsServiceImpl adsService;

    public AdsController(AdsServiceImpl adsService) {
        this.adsService = adsService;
    }

    @GetMapping
    public void getAd(@RequestBody AdDto ad) {
        adsService.getAd(ad);
    }

    @PostMapping
    public void addAd(@RequestBody AdDto ad) {
        adsService.addAd(ad);
    }

    @GetMapping("/ads/{adPk}/comments")
    public void getComments(@PathVariable String adPk) {
        adsService.getComments(adPk);
    }

    @GetMapping("/ads/{adPk}/comments")
    public void addComments(@PathVariable String adPk) {
        adsService.addComments(adPk);
    }

    @GetMapping
    public void getFullAd(@PathVariable Integer id) {
        adsService.getFullAd(id);
    }

    @DeleteMapping("/ads/{id}")
    public void removeAds(@PathVariable Integer id) {
        adsService.removeAds(id);
    }

    @PatchMapping("/ads/{id}")
    public void updateAds(@PathVariable Integer id) {
        adsService.updateAds(id);
    }

    @GetMapping("/ads/{adPk}/comments/{id}")
    public void getComments(@PathVariable Integer id, @PathVariable String adPk) {
        adsService.getComments(id, adPk);
    }

    @DeleteMapping("/ads/{adPk}/comments/{id}")
    public void deleteComments(@PathVariable Integer id, @PathVariable String adPk) {
        adsService.deleteComments(id, adPk);
    }

    @PatchMapping("/ads/{adPk}/comments/{id}")
    public void updateComments(@PathVariable Integer id, @PathVariable String adPk) {
        adsService.updateComments(id, adPk);
    }

    @GetMapping("/ads/me")
    public void getAdsMe(@RequestBody AdDto ad) {
        adsService.getAdsMe(ad);
    }
}
