package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.UserService;

import java.io.File;
import java.util.List;
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
    public ResponseEntity<String> updateUserImage(@RequestParam MultipartFile file) throws Exception {
        if (file.getSize() > 1024 * 300) {
            ResponseEntity.badRequest().body("File is to big");
        }
        userService.updateUserImage(file);
        return ResponseEntity.ok().build();
    }

//    /**
//     *
//     * @param user
//     */
//    @PostMapping("/create_user")
//    public void createUser(@Parameter(hidden = false) @RequestBody User user) {
//        userService.addUser(user);
//    }

//    @PatchMapping("/me/image")
//    public void updateUserImage(@RequestBody File file) {
//        userService.updateUserImage(file);
//    }
//

//    @DeleteMapping(value = "/{userName}")
//    public ResponseEntity deleteUserByName(@PathVariable String userName) {
//        userService.deleteByUserName(userName);
//        return ResponseEntity.ok().build();
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteUsers(@PathVariable Long id) {
//        userService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
}

