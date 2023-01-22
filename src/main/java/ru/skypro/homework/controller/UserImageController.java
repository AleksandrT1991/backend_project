package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.AdsImageService;

import java.io.File;

@RestController
@RequestMapping("/image")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class UserImageController {

    private final UserImageService adsImageService;

    @PatchMapping("/{id}")
    public void updateUserImage(@PathVariable Long id, @RequestBody File file) {
        adsImageService.updateUserImage(id, file);
    }

}
