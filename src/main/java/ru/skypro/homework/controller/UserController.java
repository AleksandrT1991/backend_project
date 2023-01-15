package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.io.File;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PatchMapping("/me")
    public UserDto updateUser(@RequestBody UserDto user) {
        System.out.println("hello");
        return new UserDto();
    }

    @GetMapping("/me")
    public UserDto getUser(@RequestBody UserDto user) {
        //тут сервис юзера
        return new UserDto();
    }

    @PostMapping("/set_password")
    public void setPassword(@RequestBody String currentPassword, @RequestBody String newPassword) {
        userService.setPassword(currentPassword, newPassword);
    }

    @PatchMapping("/me/image")
    public void updateUserImage(@RequestBody File file) {
        userService.updateUserImage(file);
    }
}
