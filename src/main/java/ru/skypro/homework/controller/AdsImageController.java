package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.impl.AdsImageServiceImpl;
import ru.skypro.homework.service.impl.AdsServiceImpl;

import java.io.File;

@RestController
@RequestMapping("/image")
@CrossOrigin(value = "http://localhost:3000")
public class AdsImageController {

    private final AdsImageServiceImpl adsImageService;

    public AdsImageController(AdsImageServiceImpl adsImageService) {
        this.adsImageService = adsImageService;
    }

    @PatchMapping("/{id}")
    public void updateUserImage(@PathVariable String id, @RequestBody File file) {
        adsImageService.updateUserImage(id, file);
    }

}
