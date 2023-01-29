package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.AdsImageService;
import ru.skypro.homework.service.UserImageService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/image")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class UserImageController {

    private final UserImageService userImageService;



    @PatchMapping(value = "/{id}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@PathVariable Long userId,
                                                  @RequestParam MultipartFile userImage) throws IOException {
        if (userImage.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        userImageService.uploadPhoto(userId, userImage);
        return ResponseEntity.ok().build();
    }

}
