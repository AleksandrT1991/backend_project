package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.user.RegisterReq;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUserDetailsService;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final MyUserDetailsService manager;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final UserRepository userRepository;

    public AuthServiceImpl(MyUserDetailsService manager, PasswordEncoder passwordEncoder, UserService userService, UserRepository userRepository)  {
        this.manager = manager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.userRepository = userRepository;
    }
    /**
     * event recording process
     */
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public boolean login(String userName, String password) {
        logger.info("Metod\"AuthServiceImpl.login()\" was called");
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        return passwordEncoder.matches(password, encryptedPassword);
    }

    @Override
    public boolean register(RegisterReq registerReq) {
        logger.info("Metod\"AuthServiceImpl.register()\" was called");
        Optional<User> userByUsername = userRepository.findUserByUsername(registerReq.getUsername());
        if (userByUsername.isPresent()) {
            return false;
        }
        User userSaved = new User();
        userSaved.setUsername(registerReq.getUsername());
        userSaved.setPassword(registerReq.getPassword());
        userSaved.setFirstName(registerReq.getFirstName());
        userSaved.setLastName(registerReq.getLastName());
        userSaved.setRegDate(LocalDate.now().toString());
        userSaved.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        userSaved.setPhone(registerReq.getPhone());
        userRepository.save(userSaved);
        return true;
    }
}
