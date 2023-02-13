package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateAdsDto;
import ru.skypro.homework.dto.ad.FullAdDto;
import ru.skypro.homework.dto.wrappers.ResponseWrapperAds;
import ru.skypro.homework.dto.wrappers.ResponseWrapperComments;
import ru.skypro.homework.service.AdsImageService;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class AdController {

    private final AdsService adsService;
    private final AdsImageService adsImageService;
    /**
     * event recording process
     */
    private final Logger logger = LoggerFactory.getLogger(AdController.class);

    @Operation(
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
    public ResponseWrapperAds getAds() {
        logger.info("Controller\"AdController.getAds()\" was called");
        return adsService.getAds();
    }

    @Operation(
            summary = "addAds",
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
    public AdDto addAd(@RequestPart(value = "properties") CreateAdsDto createAdsDto,
                       @RequestPart(value = "image") MultipartFile image) throws IOException {
        logger.info("Controller\"AdController.addAd()\" was called");
        adsImageService.createImage(image);
        return adsService.createAd(createAdsDto);
    }

    @Operation(
            summary = "getComments",
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
    public ResponseWrapperComments getComments(@PathVariable Long adPk) {
        logger.info("Controller\"AdController.getComments()\" was called");
        return adsService.getComments(adPk);
    }

    @Operation(
            summary = "addComments",
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
        logger.info("Controller\"AdController.addComments()\" was called");
        return adsService.addComments(adPk, adCommentDto);
    }

    @Operation(
            summary = "getFullAdd",
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
        logger.info("Controller\"AdController.getFullAd()\" was called");
        return adsService.getFullAd(id);
    }

    @Operation(
            summary = "removeAds",
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
        logger.info("Controller\"AdController.removeAds()\" was called");
        adsService.removeAds(id);
    }

    @Operation(
            summary = "updateAds",
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
    public AdDto updateAds(@PathVariable Long id, @RequestBody CreateAdsDto adDto) {
        logger.info("Controller\"AdController.updateAds()\" was called");
        return adsService.updateAds(id, adDto);
    }

    @Operation(
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
        logger.info("Controller\"AdController.getComments()\" was called");
        return adsService.getComments(id, adPk);
    }

    @Operation(
            summary = "deleteComments",
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
        logger.info("Controller\"AdController.deleteComments()\" was called");
        adsService.deleteComments(id, adPk);
    }

    @Operation(
            summary = "updateComments",
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
    public AdCommentDto updateComments(@PathVariable Long id, @PathVariable Long adPk,
                                       @RequestBody AdCommentDto adCommentDto) {
        logger.info("Controller\"AdController.updateComments()\" was called");
        return adsService.updateComments(id, adPk, adCommentDto);
    }

    @Operation(
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
    public ResponseWrapperAds getAdsMe(
            @RequestHeader Boolean authenticated,
            @RequestHeader List<String> authorities,
            @RequestHeader String details,
            @RequestHeader String principal
    ) {
        logger.info("Controller\"AdController.getAdsMe()\" was called");
        return adsService.getAdsMe();
    }

}
