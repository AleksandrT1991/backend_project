package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.security.MyUser;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import javax.security.auth.login.CredentialNotFoundException;

@Slf4j
@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final MyUser myUser;
    private final AuthService authService;

    /**
     * event recording process
     */
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Operation(
            summary = "setPassword",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = PasswordDto.class))
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
    @PostMapping("/set_password")
    public ResponseEntity <Void> setPassword (@RequestBody PasswordDto passwordDto) throws CredentialNotFoundException {
        logger.info("Controller\"UserController.setPassword()\" was called");
        userService.setPassword(passwordDto, myUser);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "getUser",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
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
    public ResponseEntity<UserDto> getUser() {
        logger.info("Controller\"UserController.getUser()\" was called");
        return ResponseEntity.ok(userService.getUserDtoByUsername(myUser.getUsername()));
    }

    @Operation(
            summary = "updateUser",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204"
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
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        logger.info("Controller\"UserController.updateUser()\" was called");
        return ResponseEntity.ok(userService.updateUser(userDto, myUser.getUsername()));
    }

    @Operation(
            summary = "updateUserImage",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            responseCode = "404"
                    )
            }
    )
    @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage(@RequestBody MultipartFile image) throws Exception {
        logger.info("Controller\"UserController.updateUserImage()\" was called");
        if (image.getSize() > 1024 * 300) {
            ResponseEntity.badRequest().body("File is to big");
        }
        userService.updateUserImage(myUser.getUsername(), image);
        return ResponseEntity.ok().build();
    }
}
