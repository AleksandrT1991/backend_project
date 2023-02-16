package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.AdsImageService;
import ru.skypro.homework.service.impl.AdsImageServiceImpl;
import ru.skypro.homework.service.impl.AdsServiceImpl;

import java.io.File;

@RestController
@RequestMapping("/image")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class AdsImageController {

    private final AdsImageService adsImageService;

    @PatchMapping("/{id}")
    public void updateUserImage(@PathVariable String id, @RequestBody File file) {
        adsImageService.updateUserImage(id, file);
    }

}
