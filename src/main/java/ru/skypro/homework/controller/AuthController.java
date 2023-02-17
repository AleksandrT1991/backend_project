package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.user.LoginReq;
import ru.skypro.homework.dto.user.RegisterReq;
import ru.skypro.homework.service.AuthService;

/**
 * The type Auth controller.
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    /**
     * event recording process
     */
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    /**
     * Login response entity.
     *
     * @param req the req
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req) {
        logger.info("Controller\"AuthController.login()\" was called");
        if (authService.login(req.getUsername(), req.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * Register response entity.
     *
     * @param req the req
     * @return the response entity
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReq req) {
        logger.info("Controller\"AuthController.register()\" was called");
        if (authService.register(req)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
