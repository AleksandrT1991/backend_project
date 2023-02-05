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
import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateAdDto;
import ru.skypro.homework.dto.ad.FullAdDto;
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

    //TODO: кастомные ошибки
    //TODO: логирование
    //TODO: тесты
    //TODO: возврат респонсов
    //TODO: опшеналы
    //TODO: сваггер
    //TODO: java-docs
    //TODO: аутентификация
    //TODO: работа с фронтом

    private final AdsService adsService;
    private final AdsImageService adsImageService;

    @Operation(
            //summary = "Получение всех объявлений",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = AdDto.class))
                            )
                    )
            }
    )
    @GetMapping
    public List<AdDto> getAds() {
        return adsService.getAds();
    }

    @Operation(
            //summary = "Создание объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = AdDto.class))
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
    public AdDto addAd(@RequestPart(value = "properties") CreateAdDto createAdDto,
                      @RequestPart(value = "image") MultipartFile image) throws IOException {
        adsImageService.createImage(image);
        return adsService.createAdd(createAdDto);
    }

    @Operation(
            //summary = "Получение полного объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = FullAdDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/{id}")
    public FullAdDto getFullAd(@PathVariable Long id) {
        return adsService.getFullAd(id);
    }

    @Operation(
            //summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            responseCode = "403"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public void removeAds(@PathVariable Long id) {
        adsService.removeAds(id);
    }

    @Operation(
            //summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = AdDto.class))
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
    @PatchMapping("/{id}")
    public void updateAds(@PathVariable Long id, @RequestBody CreateAdDto adDto) {
        adsService.updateAds(id, adDto);
    }

    @Operation(
            //summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = AdDto.class))
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
    @GetMapping("/me")
    public List<AdDto> getAdsMe(
                            @RequestHeader Boolean authenticated,
                            @RequestHeader List<String> authorities,
                            @RequestHeader String details,
                            @RequestHeader String principal
                            ) {
        return adsService.getAdsMe();
    }

    @Operation(
            //summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = AdCommentDto.class))
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
    @GetMapping("/{adPk}/comments")
    public List<AdCommentDto> getComments(@PathVariable Long adPk) {
        return adsService.getComments(adPk);
    }

    @Operation(
            //summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = AdCommentDto.class))
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
    @PostMapping("/{adPk}/comments")
    public AdCommentDto addComments(@PathVariable Long adPk, @RequestBody AdCommentDto adCommentDto) {
        return adsService.addComments(adPk, adCommentDto);
    }

    @Operation(
            //summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = AdCommentDto.class))
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
    @GetMapping("/{adPk}/comments/{id}")
    public AdCommentDto getComments(@PathVariable Long id, @PathVariable Long adPk) {
        return adsService.getComments(id, adPk).get();
    }

    @Operation(
            //summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
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
    @DeleteMapping("/{adPk}/comments/{id}")
    public void deleteComments(@PathVariable Long id, @PathVariable Long adPk) {
        adsService.deleteComments(id, adPk);
    }

    @Operation(
            //summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = AdCommentDto.class))
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
    @PatchMapping("/{adPk}/comments/{id}")
    public void updateComments(@PathVariable Long id, @PathVariable Long adPk, @RequestBody AdCommentDto adCommentDto) {
        adsService.updateComments(id, adPk, adCommentDto);
    }
}
