package ru.skypro.homework.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;
import ru.skypro.homework.exception.UserEntersIncorrectPassword;
import ru.skypro.homework.mappers.user.UserMapper;
import ru.skypro.homework.repository.UserImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUser;
import ru.skypro.homework.security.MyUserDetailsService;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private MyUserDetailsService myUserDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserImageRepository userImageRepository;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#setPassword(PasswordDto, MyUser)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetPassword() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       ru.skypro.homework.security.MyUser
        //   See https://diff.blue/R027 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at ru.skypro.homework.security.MyUser.getPassword(MyUser.java:30)
        //       at ru.skypro.homework.service.impl.UserServiceImpl.setPassword(UserServiceImpl.java:66)
        //   See https://diff.blue/R013 to resolve this issue.

        UserRepository userRepository = mock(UserRepository.class);
        UserImageRepository userImageRepository = mock(UserImageRepository.class);
        UserRepository userRepository1 = mock(UserRepository.class);
        MyUserDetailsService manager = new MyUserDetailsService(userRepository1, new MyUser());

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, userImageRepository, userMapper, manager,
                new Argon2PasswordEncoder());

        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setCurrentPassword("iloveyou");
        passwordDto.setNewPassword("iloveyou");
        userServiceImpl.setPassword(passwordDto, new MyUser());
    }

    /**
     * Method under test: {@link UserServiceImpl#setPassword(PasswordDto, MyUser)}
     */
    @Test
    void testSetPassword2() throws UnsupportedEncodingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       ru.skypro.homework.security.MyUser
        //   See https://diff.blue/R027 to resolve this issue.

        UserRepository userRepository = mock(UserRepository.class);
        UserImageRepository userImageRepository = mock(UserImageRepository.class);
        UserRepository userRepository1 = mock(UserRepository.class);
        MyUserDetailsService manager = new MyUserDetailsService(userRepository1, new MyUser());

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, userImageRepository, userMapper, manager,
                new Argon2PasswordEncoder());

        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setCurrentPassword("iloveyou");
        passwordDto.setNewPassword("iloveyou");

        UserImage userImage = new UserImage();
        userImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage.setFilePath("/directory/foo.txt");
        userImage.setFileSize(3L);
        userImage.setId(123L);
        userImage.setMediaType("Metod\"UserServiceImpl.setPassword()\" was called");
        userImage.setUser(new User());

        User user = new User();
        user.setCity("Oxford");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setImage(userImage);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhone("4105551212");
        user.setRegDate("2020-03-01");
        user.setUsername("janedoe");

        UserImage userImage1 = new UserImage();
        userImage1.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage1.setFilePath("/directory/foo.txt");
        userImage1.setFileSize(3L);
        userImage1.setId(123L);
        userImage1.setMediaType("Metod\"UserServiceImpl.setPassword()\" was called");
        userImage1.setUser(user);

        User user1 = new User();
        user1.setCity("Oxford");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setImage(userImage1);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setPhone("4105551212");
        user1.setRegDate("2020-03-01");
        user1.setUsername("janedoe");

        MyUser myUser = new MyUser();
        myUser.setUser(user1);
        assertThrows(UserEntersIncorrectPassword.class, () -> userServiceImpl.setPassword(passwordDto, myUser));
    }

    /**
     * Method under test: {@link UserServiceImpl#setPassword(PasswordDto, MyUser)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetPassword3() throws UnsupportedEncodingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       ru.skypro.homework.security.MyUser
        //   See https://diff.blue/R027 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at ru.skypro.homework.service.impl.UserServiceImpl.setPassword(UserServiceImpl.java:67)
        //   See https://diff.blue/R013 to resolve this issue.

        UserRepository userRepository = mock(UserRepository.class);
        UserImageRepository userImageRepository = mock(UserImageRepository.class);
        UserRepository userRepository1 = mock(UserRepository.class);
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, userImageRepository, userMapper,
                new MyUserDetailsService(userRepository1, new MyUser()), null);

        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setCurrentPassword("iloveyou");
        passwordDto.setNewPassword("iloveyou");

        UserImage userImage = new UserImage();
        userImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage.setFilePath("/directory/foo.txt");
        userImage.setFileSize(3L);
        userImage.setId(123L);
        userImage.setMediaType("Metod\"UserServiceImpl.setPassword()\" was called");
        userImage.setUser(new User());

        User user = new User();
        user.setCity("Oxford");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setImage(userImage);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhone("4105551212");
        user.setRegDate("2020-03-01");
        user.setUsername("janedoe");

        UserImage userImage1 = new UserImage();
        userImage1.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage1.setFilePath("/directory/foo.txt");
        userImage1.setFileSize(3L);
        userImage1.setId(123L);
        userImage1.setMediaType("Metod\"UserServiceImpl.setPassword()\" was called");
        userImage1.setUser(user);

        User user1 = new User();
        user1.setCity("Oxford");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setImage(userImage1);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setPhone("4105551212");
        user1.setRegDate("2020-03-01");
        user1.setUsername("janedoe");

        MyUser myUser = new MyUser();
        myUser.setUser(user1);
        userServiceImpl.setPassword(passwordDto, myUser);
    }

    /**
     * Method under test: {@link UserServiceImpl#setPassword(PasswordDto, MyUser)}
     */
    @Test
    void testSetPassword4() throws UnsupportedEncodingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       ru.skypro.homework.security.MyUser
        //   See https://diff.blue/R027 to resolve this issue.

        User user = new User();
        user.setCity("Oxford");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setImage(new UserImage());
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhone("4105551212");
        user.setRegDate("2020-03-01");
        user.setUsername("janedoe");

        UserImage userImage = new UserImage();
        userImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage.setFilePath("/directory/foo.txt");
        userImage.setFileSize(3L);
        userImage.setId(123L);
        userImage.setMediaType("Media Type");
        userImage.setUser(user);

        User user1 = new User();
        user1.setCity("Oxford");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setImage(userImage);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setPhone("4105551212");
        user1.setRegDate("2020-03-01");
        user1.setUsername("janedoe");

        UserImage userImage1 = new UserImage();
        userImage1.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage1.setFilePath("/directory/foo.txt");
        userImage1.setFileSize(3L);
        userImage1.setId(123L);
        userImage1.setMediaType("Media Type");
        userImage1.setUser(user1);

        User user2 = new User();
        user2.setCity("Oxford");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        user2.setImage(userImage1);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhone("4105551212");
        user2.setRegDate("2020-03-01");
        user2.setUsername("janedoe");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save((User) any())).thenReturn(user2);
        UserImageRepository userImageRepository = mock(UserImageRepository.class);
        UserRepository userRepository1 = mock(UserRepository.class);
        MyUserDetailsService manager = new MyUserDetailsService(userRepository1, new MyUser());

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, userImageRepository, userMapper, manager,
                new LdapShaPasswordEncoder());

        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setCurrentPassword("iloveyou");
        passwordDto.setNewPassword("iloveyou");

        UserImage userImage2 = new UserImage();
        userImage2.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage2.setFilePath("/directory/foo.txt");
        userImage2.setFileSize(3L);
        userImage2.setId(123L);
        userImage2.setMediaType("Metod\"UserServiceImpl.setPassword()\" was called");
        userImage2.setUser(new User());

        User user3 = new User();
        user3.setCity("Oxford");
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(123L);
        user3.setImage(userImage2);
        user3.setLastName("Doe");
        user3.setPassword("iloveyou");
        user3.setPhone("4105551212");
        user3.setRegDate("2020-03-01");
        user3.setUsername("janedoe");

        UserImage userImage3 = new UserImage();
        userImage3.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage3.setFilePath("/directory/foo.txt");
        userImage3.setFileSize(3L);
        userImage3.setId(123L);
        userImage3.setMediaType("Metod\"UserServiceImpl.setPassword()\" was called");
        userImage3.setUser(user3);

        User user4 = new User();
        user4.setCity("Oxford");
        user4.setEmail("jane.doe@example.org");
        user4.setFirstName("Jane");
        user4.setId(123L);
        user4.setImage(userImage3);
        user4.setLastName("Doe");
        user4.setPassword("iloveyou");
        user4.setPhone("4105551212");
        user4.setRegDate("2020-03-01");
        user4.setUsername("janedoe");

        MyUser myUser = new MyUser();
        myUser.setUser(user4);
        userServiceImpl.setPassword(passwordDto, myUser);
        verify(userRepository).save((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#setPassword(PasswordDto, MyUser)}
     */
    @Test
    void testSetPassword5() throws UnsupportedEncodingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       ru.skypro.homework.security.MyUser
        //   See https://diff.blue/R027 to resolve this issue.

        User user = new User();
        user.setCity("Oxford");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setImage(new UserImage());
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhone("4105551212");
        user.setRegDate("2020-03-01");
        user.setUsername("janedoe");

        UserImage userImage = new UserImage();
        userImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage.setFilePath("/directory/foo.txt");
        userImage.setFileSize(3L);
        userImage.setId(123L);
        userImage.setMediaType("Media Type");
        userImage.setUser(user);

        User user1 = new User();
        user1.setCity("Oxford");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setImage(userImage);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setPhone("4105551212");
        user1.setRegDate("2020-03-01");
        user1.setUsername("janedoe");

        UserImage userImage1 = new UserImage();
        userImage1.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage1.setFilePath("/directory/foo.txt");
        userImage1.setFileSize(3L);
        userImage1.setId(123L);
        userImage1.setMediaType("Media Type");
        userImage1.setUser(user1);

        User user2 = new User();
        user2.setCity("Oxford");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        user2.setImage(userImage1);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhone("4105551212");
        user2.setRegDate("2020-03-01");
        user2.setUsername("janedoe");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save((User) any())).thenReturn(user2);
        UserImageRepository userImageRepository = mock(UserImageRepository.class);
        UserRepository userRepository1 = mock(UserRepository.class);
        MyUserDetailsService manager = new MyUserDetailsService(userRepository1, new MyUser());

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, userImageRepository, userMapper, manager,
                new LdapShaPasswordEncoder());
        PasswordDto passwordDto = mock(PasswordDto.class);
        when(passwordDto.getNewPassword()).thenThrow(new UserEntersIncorrectPassword("Not all who wander are lost"));
        when(passwordDto.getCurrentPassword()).thenReturn("iloveyou");
        doNothing().when(passwordDto).setCurrentPassword((String) any());
        doNothing().when(passwordDto).setNewPassword((String) any());
        passwordDto.setCurrentPassword("iloveyou");
        passwordDto.setNewPassword("iloveyou");

        UserImage userImage2 = new UserImage();
        userImage2.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage2.setFilePath("/directory/foo.txt");
        userImage2.setFileSize(3L);
        userImage2.setId(123L);
        userImage2.setMediaType("Metod\"UserServiceImpl.setPassword()\" was called");
        userImage2.setUser(new User());

        User user3 = new User();
        user3.setCity("Oxford");
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(123L);
        user3.setImage(userImage2);
        user3.setLastName("Doe");
        user3.setPassword("iloveyou");
        user3.setPhone("4105551212");
        user3.setRegDate("2020-03-01");
        user3.setUsername("janedoe");

        UserImage userImage3 = new UserImage();
        userImage3.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage3.setFilePath("/directory/foo.txt");
        userImage3.setFileSize(3L);
        userImage3.setId(123L);
        userImage3.setMediaType("Metod\"UserServiceImpl.setPassword()\" was called");
        userImage3.setUser(user3);

        User user4 = new User();
        user4.setCity("Oxford");
        user4.setEmail("jane.doe@example.org");
        user4.setFirstName("Jane");
        user4.setId(123L);
        user4.setImage(userImage3);
        user4.setLastName("Doe");
        user4.setPassword("iloveyou");
        user4.setPhone("4105551212");
        user4.setRegDate("2020-03-01");
        user4.setUsername("janedoe");

        MyUser myUser = new MyUser();
        myUser.setUser(user4);
        assertThrows(UserEntersIncorrectPassword.class, () -> userServiceImpl.setPassword(passwordDto, myUser));
        verify(passwordDto).getCurrentPassword();
        verify(passwordDto).getNewPassword();
        verify(passwordDto).setCurrentPassword((String) any());
        verify(passwordDto).setNewPassword((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUser(String)}
     */
    @Test
    void testGetUser() throws UnsupportedEncodingException {
        UserImage userImage = new UserImage();
        userImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage.setFilePath("/directory/foo.txt");
        userImage.setFileSize(3L);
        userImage.setId(123L);
        userImage.setMediaType("Media Type");
        userImage.setUser(new User());

        User user = new User();
        user.setCity("Oxford");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setImage(userImage);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhone("4105551212");
        user.setRegDate("2020-03-01");
        user.setUsername("janedoe");

        UserImage userImage1 = new UserImage();
        userImage1.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage1.setFilePath("/directory/foo.txt");
        userImage1.setFileSize(3L);
        userImage1.setId(123L);
        userImage1.setMediaType("Media Type");
        userImage1.setUser(user);

        User user1 = new User();
        user1.setCity("Oxford");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setImage(userImage1);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setPhone("4105551212");
        user1.setRegDate("2020-03-01");
        user1.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user1);
        when(userRepository.findUserByUsername((String) any())).thenReturn(ofResult);
        assertSame(user1, userServiceImpl.getUser("janedoe"));
        verify(userRepository).findUserByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(UserDto, String)}
     */
    @Test
    void testUpdateUser() throws UnsupportedEncodingException {
        UserImage userImage = new UserImage();
        userImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage.setFilePath("/directory/foo.txt");
        userImage.setFileSize(3L);
        userImage.setId(123L);
        userImage.setMediaType("Media Type");
        userImage.setUser(new User());

        User user = new User();
        user.setCity("Oxford");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setImage(userImage);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhone("4105551212");
        user.setRegDate("2020-03-01");
        user.setUsername("janedoe");

        UserImage userImage1 = new UserImage();
        userImage1.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage1.setFilePath("/directory/foo.txt");
        userImage1.setFileSize(3L);
        userImage1.setId(123L);
        userImage1.setMediaType("Media Type");
        userImage1.setUser(user);

        User user1 = new User();
        user1.setCity("Oxford");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setImage(userImage1);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setPhone("4105551212");
        user1.setRegDate("2020-03-01");
        user1.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user1);

        User user2 = new User();
        user2.setCity("Oxford");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        user2.setImage(new UserImage());
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhone("4105551212");
        user2.setRegDate("2020-03-01");
        user2.setUsername("janedoe");

        UserImage userImage2 = new UserImage();
        userImage2.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage2.setFilePath("/directory/foo.txt");
        userImage2.setFileSize(3L);
        userImage2.setId(123L);
        userImage2.setMediaType("Media Type");
        userImage2.setUser(user2);

        User user3 = new User();
        user3.setCity("Oxford");
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(123L);
        user3.setImage(userImage2);
        user3.setLastName("Doe");
        user3.setPassword("iloveyou");
        user3.setPhone("4105551212");
        user3.setRegDate("2020-03-01");
        user3.setUsername("janedoe");

        UserImage userImage3 = new UserImage();
        userImage3.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage3.setFilePath("/directory/foo.txt");
        userImage3.setFileSize(3L);
        userImage3.setId(123L);
        userImage3.setMediaType("Media Type");
        userImage3.setUser(user3);

        User user4 = new User();
        user4.setCity("Oxford");
        user4.setEmail("jane.doe@example.org");
        user4.setFirstName("Jane");
        user4.setId(123L);
        user4.setImage(userImage3);
        user4.setLastName("Doe");
        user4.setPassword("iloveyou");
        user4.setPhone("4105551212");
        user4.setRegDate("2020-03-01");
        user4.setUsername("janedoe");
        when(userRepository.save((User) any())).thenReturn(user4);
        when(userRepository.findUserByUsername((String) any())).thenReturn(ofResult);

        UserDto userDto = new UserDto();
        userDto.setCity("Oxford");
        userDto.setEmail("jane.doe@example.org");
        userDto.setFirstName("Jane");
        userDto.setId(123L);
        userDto.setImage("Image");
        userDto.setLastName("Doe");
        userDto.setPhone("4105551212");
        userDto.setRegDate("2020-03-01");
        UserDto actualUpdateUserResult = userServiceImpl.updateUser(userDto, "janedoe");
        assertEquals("Oxford", actualUpdateUserResult.getCity());
        assertEquals("2020-03-01", actualUpdateUserResult.getRegDate());
        assertEquals("4105551212", actualUpdateUserResult.getPhone());
        assertEquals("Doe", actualUpdateUserResult.getLastName());
        assertEquals(
                "/image/UserImage(id=123, user=User(email=jane.doe@example.org, firstName=Jane, username=janedoe, id=123,"
                        + " lastName=Doe, phone=4105551212, regDate=2020-03-01, city=Oxford, image=UserImage(id=123, user=User"
                        + "(email=jane.doe@example.org, firstName=Jane, username=janedoe, id=123, lastName=Doe, phone=4105551212,"
                        + " regDate=2020-03-01, city=Oxford, image=UserImage(id=null, user=null, filePath=null, bytea=null,"
                        + " fileSize=null, mediaType=null), password=iloveyou), filePath=/directory/foo.txt, bytea=[65, 65, 65,"
                        + " 65, 65, 65, 65, 65], fileSize=3, mediaType=Media Type), password=iloveyou), filePath=/directory/foo.txt,"
                        + " bytea=[65, 65, 65, 65, 65, 65, 65, 65], fileSize=3, mediaType=Media Type)",
                actualUpdateUserResult.getImage());
        assertEquals(123L, actualUpdateUserResult.getId().longValue());
        assertEquals("Jane", actualUpdateUserResult.getFirstName());
        assertEquals("jane.doe@example.org", actualUpdateUserResult.getEmail());
        verify(userRepository).save((User) any());
        verify(userRepository).findUserByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(UserDto, String)}
     */
    @Test
    void testUpdateUser2() throws UnsupportedEncodingException {
        UserImage userImage = new UserImage();
        userImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage.setFilePath("/directory/foo.txt");
        userImage.setFileSize(3L);
        userImage.setId(123L);
        userImage.setMediaType("Media Type");
        userImage.setUser(new User());

        User user = new User();
        user.setCity("Oxford");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setImage(userImage);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhone("4105551212");
        user.setRegDate("2020-03-01");
        user.setUsername("janedoe");

        UserImage userImage1 = new UserImage();
        userImage1.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage1.setFilePath("/directory/foo.txt");
        userImage1.setFileSize(3L);
        userImage1.setId(123L);
        userImage1.setMediaType("Media Type");
        userImage1.setUser(user);

        User user1 = new User();
        user1.setCity("Oxford");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setImage(userImage1);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setPhone("4105551212");
        user1.setRegDate("2020-03-01");
        user1.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user1);
        when(userRepository.save((User) any())).thenThrow(new UserEntersIncorrectPassword("Not all who wander are lost"));
        when(userRepository.findUserByUsername((String) any())).thenReturn(ofResult);

        UserDto userDto = new UserDto();
        userDto.setCity("Oxford");
        userDto.setEmail("jane.doe@example.org");
        userDto.setFirstName("Jane");
        userDto.setId(123L);
        userDto.setImage("Image");
        userDto.setLastName("Doe");
        userDto.setPhone("4105551212");
        userDto.setRegDate("2020-03-01");
        assertThrows(UserEntersIncorrectPassword.class, () -> userServiceImpl.updateUser(userDto, "janedoe"));
        verify(userRepository).save((User) any());
        verify(userRepository).findUserByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserDtoByUsername(String)}
     */
    @Test
    void testGetUserDtoByUsername() throws UnsupportedEncodingException {
        UserImage userImage = new UserImage();
        userImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage.setFilePath("/directory/foo.txt");
        userImage.setFileSize(3L);
        userImage.setId(123L);
        userImage.setMediaType("Media Type");
        userImage.setUser(new User());

        User user = new User();
        user.setCity("Oxford");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setImage(userImage);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setPhone("4105551212");
        user.setRegDate("2020-03-01");
        user.setUsername("janedoe");

        UserImage userImage1 = new UserImage();
        userImage1.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage1.setFilePath("/directory/foo.txt");
        userImage1.setFileSize(3L);
        userImage1.setId(123L);
        userImage1.setMediaType("Media Type");
        userImage1.setUser(user);

        User user1 = new User();
        user1.setCity("Oxford");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setImage(userImage1);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setPhone("4105551212");
        user1.setRegDate("2020-03-01");
        user1.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user1);
        when(userRepository.findUserByUsername((String) any())).thenReturn(ofResult);
        UserDto actualUserDtoByUsername = userServiceImpl.getUserDtoByUsername("janedoe");
        assertEquals("Oxford", actualUserDtoByUsername.getCity());
        assertEquals("2020-03-01", actualUserDtoByUsername.getRegDate());
        assertEquals("4105551212", actualUserDtoByUsername.getPhone());
        assertEquals("Doe", actualUserDtoByUsername.getLastName());
        assertEquals(
                "/image/UserImage(id=123, user=User(email=jane.doe@example.org, firstName=Jane, username=janedoe, id=123,"
                        + " lastName=Doe, phone=4105551212, regDate=2020-03-01, city=Oxford, image=UserImage(id=123, user=User"
                        + "(email=null, firstName=null, username=null, id=null, lastName=null, phone=null, regDate=null, city=null,"
                        + " image=null, password=null), filePath=/directory/foo.txt, bytea=[65, 65, 65, 65, 65, 65, 65, 65],"
                        + " fileSize=3, mediaType=Media Type), password=iloveyou), filePath=/directory/foo.txt, bytea=[65, 65,"
                        + " 65, 65, 65, 65, 65, 65], fileSize=3, mediaType=Media Type)",
                actualUserDtoByUsername.getImage());
        assertEquals(123L, actualUserDtoByUsername.getId().longValue());
        assertEquals("Jane", actualUserDtoByUsername.getFirstName());
        assertEquals("jane.doe@example.org", actualUserDtoByUsername.getEmail());
        verify(userRepository).findUserByUsername((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUserImage(String, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUserImage() throws IOException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files outside the temporary directory (file '${user.image.dir.path}', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        userServiceImpl.updateUserImage("janedoe",
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUserImage(String, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUserImage2() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.nio.file.InvalidPathException: Illegal char <"> at index 28: ${user.image.dir.path}\Metod"UserServiceImpl.updateUserImage()" was called.
        //       at sun.nio.fs.WindowsPathParser.normalize(WindowsPathParser.java:182)
        //       at sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:153)
        //       at sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:77)
        //       at sun.nio.fs.WindowsPath.parse(WindowsPath.java:92)
        //       at sun.nio.fs.WindowsFileSystem.getPath(WindowsFileSystem.java:229)
        //       at java.nio.file.Path.of(Path.java:147)
        //       at ru.skypro.homework.service.impl.UserServiceImpl.updateUserImage(UserServiceImpl.java:114)
        //   See https://diff.blue/R013 to resolve this issue.

        userServiceImpl.updateUserImage("janedoe",
                new MockMultipartFile("Metod\"UserServiceImpl.updateUserImage()\" was called",
                        new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUserImage(String, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUserImage3() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at ru.skypro.homework.service.impl.UserServiceImpl.updateUserImage(UserServiceImpl.java:114)
        //   See https://diff.blue/R013 to resolve this issue.

        userServiceImpl.updateUserImage("janedoe", null);
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUserImage(String, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUserImage4() throws IOException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files outside the temporary directory (file '${user.image.dir.path}', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getName()).thenReturn("Name");
        when(multipartFile.getOriginalFilename()).thenReturn("foo.txt");
        userServiceImpl.updateUserImage("janedoe", multipartFile);
    }
}

