package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.service.AdsService;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class AdsController {

    private final AdsService adsService;

    @GetMapping
    public void getAd(@RequestBody AdDto ad) {
        adsService.getAd(ad);
    }

    @PostMapping
    public void addAd(@RequestBody AdDto ad) {
        adsService.addAd(ad);
    }

    @GetMapping("/{adPk}/comments")
    public void getComments(@PathVariable String adPk) {
        adsService.getComments(adPk);
    }

    @PostMapping("/{adPk}/comments")
    public void addComments(@PathVariable String adPk) {
        adsService.addComments(adPk);
    }

    @GetMapping("/{id}")
    public void getFullAd(@PathVariable Integer id) {
        adsService.getFullAd(id);
    }

    @DeleteMapping("/{id}")
    public void removeAds(@PathVariable Integer id) {
        adsService.removeAds(id);
    }

    @PatchMapping("/{id}")
    public void updateAds(@PathVariable Integer id) {
        adsService.updateAds(id);
    }

    @GetMapping("/{adPk}/comments/{id}")
    public void getComments(@PathVariable Integer id, @PathVariable String adPk) {
        adsService.getComments(id, adPk);
    }

    @DeleteMapping("/{adPk}/comments/{id}")
    public void deleteComments(@PathVariable Integer id, @PathVariable String adPk) {
        adsService.deleteComments(id, adPk);
    }

    @PatchMapping("/{adPk}/comments/{id}")
    public void updateComments(@PathVariable Integer id, @PathVariable String adPk) {
        adsService.updateComments(id, adPk);
    }

    @GetMapping("/me")
    public void getAdsMe(@RequestBody AdDto ad) {
        adsService.getAdsMe(ad);
    }
}
