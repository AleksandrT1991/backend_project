package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.service.UserService;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
    @PostMapping("/set_password")
    public PasswordDto setPassword(@RequestBody PasswordDto passwordDto) throws CredentialNotFoundException {
        return userService.setPassword(passwordDto);
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
    @GetMapping("/me")
    public UserDto getUser() {
        return userService.getUser();
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
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        Optional<UserDto> result = userService.updateUser(userDto);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(result.get());
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
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@RequestParam MultipartFile image) throws Exception {
        if (image.getSize() > 1024 * 300) {
            ResponseEntity.badRequest().body("File is to big");
        }
        userService.updateUserImage(image);
        return ResponseEntity.ok().build();
    }

}
