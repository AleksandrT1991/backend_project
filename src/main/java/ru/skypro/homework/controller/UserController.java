package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * @param userDto
     * @return
     */
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        Optional<UserDto> result = userService.updateUser(userDto);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(result.get());
    }

    /**
     * @return
     */
    @GetMapping("/me")
    public UserDto getUser() {
        return userService.getUser();
    }

    @PostMapping("/set_password")
    public void setPassword(@RequestBody PasswordDto passwordDto) {
        userService.setPassword(passwordDto);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@RequestParam MultipartFile image) throws Exception {
        if (image.getSize() > 1024 * 300) {
            ResponseEntity.badRequest().body("File is to big");
        }
        userService.updateUserImage(image);
        return ResponseEntity.ok().build();
    }
}

