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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateAdsDto;
import ru.skypro.homework.dto.ad.FullAdDto;
import ru.skypro.homework.dto.wrappers.ResponseWrapperAds;
import ru.skypro.homework.dto.wrappers.ResponseWrapperComments;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.security.MyUser;
import ru.skypro.homework.service.AdsImageService;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * The type Ad controller.
 */
@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class AdController {

    private final AdsService adsService;
    private final AdsImageService adsImageService;

    private final MyUser myUser;
    /**
     * event recording process
     */
    private final Logger logger = LoggerFactory.getLogger(AdController.class);

    /**
     * Gets ads.
     *
     * @return the ads
     */
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
    public ResponseEntity<ResponseWrapperAds> getAds() {
        logger.info("Controller\"AdController.getAds()\" was called");
        Optional<ResponseWrapperAds> result = Optional.ofNullable(adsService.getAds());
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result.get());
        }
    }

    /**
     * Add ad ad dto.
     *
     * @param createAdsDto the create ads dto
     * @param image        the image
     * @return the ad dto
     * @throws IOException the io exception
     */
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
    @PreAuthorize("isAuthenticated()")
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public AdDto addAd(@RequestPart(value = "properties") CreateAdsDto createAdsDto,
                       @RequestPart(value = "image") MultipartFile image) throws IOException {
        logger.info("Controller\"AdController.addAd()\" was called");
        List<AdImage> imageSaved = List.of(adsImageService.createImage(image));
        return adsService.createAd(createAdsDto, imageSaved);
    }

    /**
     * Gets comments.
     *
     * @param adPk the ad pk
     * @return the comments
     */
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
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{adPk}/comments")
    public ResponseEntity<ResponseWrapperComments> getComments(@PathVariable Long adPk) {
        logger.info("Controller\"AdController.getComments()\" was called");
        Optional<ResponseWrapperComments> result = Optional.ofNullable(adsService.getComments(adPk));
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result.get());
        }
    }

    /**
     * Add comments ad comment dto.
     *
     * @param adPk         the ad pk
     * @param adCommentDto the ad comment dto
     * @return the ad comment dto
     */
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
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{adPk}/comments")
    public AdCommentDto addComments(@PathVariable Long adPk, @RequestBody AdCommentDto adCommentDto) {
        logger.info("Controller\"AdController.addComments()\" was called");
        return adsService.addComments(adPk, adCommentDto);
    }

    /**
     * Gets full ad.
     *
     * @param id the id
     * @return the full ad
     */
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
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<FullAdDto> getFullAd(@PathVariable Long id) {
        logger.info("Controller\"AdController.getFullAd()\" was called");
        Optional<FullAdDto> result = Optional.ofNullable(adsService.getFullAd(id));
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result.get());
        }
    }

    /**
     * Remove ads.
     *
     * @param id the id
     */
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
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public void removeAds(@PathVariable Long id) {
        logger.info("Controller\"AdController.removeAds()\" was called");
        adsService.removeAds(id);
    }

    /**
     * Update ads ad dto.
     *
     * @param id    the id
     * @param adDto the ad dto
     * @return the ad dto
     */
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
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{id}")
    public AdDto updateAds(@PathVariable Long id, @RequestBody CreateAdsDto adDto) {
        logger.info("Controller\"AdController.updateAds()\" was called");
        return adsService.updateAds(id, adDto);
    }

    /**
     * Gets comments.
     *
     * @param id   the id
     * @param adPk the ad pk
     * @return the comments
     */
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
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{adPk}/comments/{id}")
    public ResponseEntity<AdCommentDto> getComments(@PathVariable Long id, @PathVariable Long adPk) {
        logger.info("Controller\"AdController.getComments()\" was called");
        Optional<AdCommentDto> result = Optional.of(adsService.getComments(adPk, id));
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result.get());
        }
    }

    /**
     * Delete comments.
     *
     * @param id   the id
     * @param adPk the ad pk
     */
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
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{adPk}/comments/{id}")
    public void deleteComments(@PathVariable Long id, @PathVariable Long adPk) {
        logger.info("Controller\"AdController.deleteComments()\" was called");
        adsService.deleteComments(id, adPk);
    }

    /**
     * Update comments ad comment dto.
     *
     * @param id           the id
     * @param adPk         the ad pk
     * @param adCommentDto the ad comment dto
     * @return the ad comment dto
     */
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
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{adPk}/comments/{id}")
    public AdCommentDto updateComments(@PathVariable Long id, @PathVariable Long adPk,
                                       @RequestBody AdCommentDto adCommentDto) {
        logger.info("Controller\"AdController.updateComments()\" was called");
        return adsService.updateComments(id, adPk, adCommentDto);
    }

    /**
     * Gets ads me.
     *
     * @return the ads me
     */
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
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe() {
        logger.info("Controller\"AdController.getAdsMe()\" was called");
        ResponseWrapperAds result = adsService.getAdsMe(myUser.getUsername());
        return ResponseEntity.ok(result);
    }
}
