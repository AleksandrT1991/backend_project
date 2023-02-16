package ru.skypro.homework.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.skypro.homework.dto.user.RegisterReq;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUser;
import ru.skypro.homework.security.MyUserDetailsService;
import ru.skypro.homework.service.UserService;

@ContextConfiguration(classes = {AuthServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AuthServiceImplTest {
    @Autowired
    private AuthServiceImpl authServiceImpl;

    @MockBean
    private MyUserDetailsService myUserDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link AuthServiceImpl#login(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLogin() throws UsernameNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at ru.skypro.homework.security.MyUser.getPassword(MyUser.java:30)
        //       at ru.skypro.homework.service.impl.AuthServiceImpl.login(AuthServiceImpl.java:44)
        //   See https://diff.blue/R013 to resolve this issue.

        when(myUserDetailsService.loadUserByUsername((String) any())).thenReturn(new MyUser());
        authServiceImpl.login("janedoe", "iloveyou");
    }

    /**
     * Method under test: {@link AuthServiceImpl#login(String, String)}
     */
    @Test
    void testLogin2() throws UnsupportedEncodingException, UsernameNotFoundException {
        UserImage userImage = new UserImage();
        userImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage.setFilePath("/directory/foo.txt");
        userImage.setFileSize(3L);
        userImage.setId(123L);
        userImage.setMediaType("Metod\"AuthServiceImpl.login()\" was called");
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
        userImage1.setMediaType("Metod\"AuthServiceImpl.login()\" was called");
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
        when(myUserDetailsService.loadUserByUsername((String) any())).thenReturn(myUser);
        when(passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(true);
        assertTrue(authServiceImpl.login("janedoe", "iloveyou"));
        verify(myUserDetailsService).loadUserByUsername((String) any());
        verify(passwordEncoder).matches((CharSequence) any(), (String) any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#register(RegisterReq)}
     */
    @Test
    void testRegister() throws UnsupportedEncodingException {
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

        UserImage userImage2 = new UserImage();
        userImage2.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage2.setFilePath("/directory/foo.txt");
        userImage2.setFileSize(3L);
        userImage2.setId(123L);
        userImage2.setMediaType("Media Type");
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
        Optional<User> ofResult = Optional.of(user4);
        when(userRepository.save((User) any())).thenReturn(user2);
        when(userRepository.findUserByUsername((String) any())).thenReturn(ofResult);

        RegisterReq registerReq = new RegisterReq();
        registerReq.setFirstName("Jane");
        registerReq.setLastName("Doe");
        registerReq.setPassword("iloveyou");
        registerReq.setPhone("4105551212");
        registerReq.setUsername("janedoe");
        assertFalse(authServiceImpl.register(registerReq));
        verify(userRepository).findUserByUsername((String) any());
    }
}

