package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.UserService;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/me")
    public UserDto updateUser(@RequestBody UserDto user) {
        System.out.println("hello");
        return new UserDto();
    }

    /**
     *
     * @return
     */
    @GetMapping("/me")
        public List<User> getAllUser() {
        return userService.getAllUser();
    }
    /**
     *
     * @param user
     */
    @PostMapping("/create_user")
    public void createUser(@Parameter(hidden = false) @RequestBody User user) {
        userService.addUser(user);
    }

    /**
     *
     * @param currentPassword
     * @param newPassword
     */
//    @PostMapping("/set_password")
//    public void setPassword(@RequestBody String currentPassword, @RequestBody String newPassword) {
//        userService.setPassword(currentPassword, newPassword);
//    }

//    @PatchMapping("/me/image")
//    public void updateUserImage(@RequestBody File file) {
//        userService.updateUserImage(file);
//    }
//
//    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> updateUserImage(@PathVariable Long id,
//                                                  @RequestParam MultipartFile userImage) throws Exception {
//        if (userImage.getSize() > 1024 * 300) {
//            ResponseEntity.badRequest().body("File is to big");
//        }
//        userService.updateUserImage(id, userImage);
//        return ResponseEntity.ok().build();
//    }
}
