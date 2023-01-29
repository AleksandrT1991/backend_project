package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserImageService;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//
//    public void updateUserImage(Long userId, MultipartFile file) throws IOException {
//        Optional<User> user = findUser(userId);
//        Path filePath = Path.of(avatarsDir, studentId + "." + getExtension(file.getOriginalFilename()));
//        Files.createDirectories(filePath.getParent());
//        Files.deleteIfExists(filePath);
//
//        try (InputStream is = file.getInputStream();
//             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
//             BufferedInputStream bis = new BufferedInputStream(is, 1024);
//             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
//        ) {
//            bis.transferTo(bos);
//        }
//        UserImage userImage = userImageRepository.findByUserId(userId).orElseGet(UserImage::new);
//        userImage.setUser(user);
//        userImage.setFilePath(filePath.toString());
//        userImage.setFileSize(file.getSize());
//        userImage.setMediaType(file.getContentType());
//        userImage.setData(file.getBytes());
//
//        userRepository.save(userImage);
//    }

//    @Override
//    public User setPassword (String currentPassword, String newPassword) {
//        return userRepository.save(currentPassword, newPassword);
//    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public List<User> getAllUser (){
        return userRepository.findAll();
    }
    @Override
    public Optional<User> findUser(Long userId){
        return userRepository.findUserById(userId);
    }
}
