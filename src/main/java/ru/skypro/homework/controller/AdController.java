package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.service.AdsImageService;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@Tag(name = "Объявления")
public class AdController {

    private final AdsService adsService;
    private final AdsImageService adsImageService;

    @Operation(
            summary = "Получение всех объявлений",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ad.class))
                            )
                    )
            }
    )
    @GetMapping
    public List<AdDto> getAds() {
        return adsService.getAds();
    }

    @Operation(
            summary = "Создание объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Ad.class))
                            )
                    ),

                    @ApiResponse(
                            responseCode = "401"
                    ),

                    @ApiResponse(
                            responseCode = "403"
                    ),

                    @ApiResponse(
                            responseCode = "404"
                    )
            }
    )
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Ad addAd(@RequestPart(value = "properties") CreateAdDto createAdDto,
                      @RequestPart(value = "image") MultipartFile image) throws IOException {
        adsImageService.createImage(image);
        return adsService.createAdd(createAdDto);
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
    public void getFullAd(@PathVariable Long id) {
        adsService.getFullAd(id);
    }

    @DeleteMapping("/{id}")
    public void removeAds(@PathVariable Long id) {
        adsService.removeAds(id);
    }

    @PatchMapping("/{id}")
    public void updateAds(@PathVariable Long id) {
        adsService.updateAds(id);
    }

    @GetMapping("/{adPk}/comments/{id}")
    public void getComments(@PathVariable Long id, @PathVariable String adPk) {
        adsService.getComments(id, adPk);
    }

    @DeleteMapping("/{adPk}/comments/{id}")
    public void deleteComments(@PathVariable Long id, @PathVariable String adPk) {
        adsService.deleteComments(id, adPk);
    }

    @PatchMapping("/{adPk}/comments/{id}")
    public void updateComments(@PathVariable Long id, @PathVariable String adPk) {
        adsService.updateComments(id, adPk);
    }

    @GetMapping("/me")
    public void getAdsMe(@RequestBody AdDto ad) {
        adsService.getAdsMe(ad);
    }
}
