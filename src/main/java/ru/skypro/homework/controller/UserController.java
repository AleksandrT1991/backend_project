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
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     *
     * @param user
     * @return
     */
    @PatchMapping("/me/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Optional<User> result = userService.updateUser(user);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(result.get());
    }

    /**
     *
     * @return
     */
    @GetMapping("/me/getUser")
        public List<User> getUser() {
        return userService.getAllUser();
    }

    /**
     * @param currentPassword
     * @param newPassword
     */
    @PostMapping("/set_password")
    public void setPassword(@RequestBody String currentPassword, @RequestBody String newPassword) {
//        userService.setPassword(currentPassword,newPassword);
    }

    @PatchMapping(value = "/me/image updateUserImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@PathVariable Long id,
                                                  @RequestParam MultipartFile userImage) throws Exception {
        if (userImage.getSize() > 1024 * 300) {
            ResponseEntity.badRequest().body("File is to big");
        }
        userService.updateUserImage(id, userImage);
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

