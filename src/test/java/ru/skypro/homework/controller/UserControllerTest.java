package ru.skypro.homework.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.security.MyUser;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private AuthService authService;

    @MockBean
    private MyUser myUser;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#getUser()}
     */
    @Test
    void testGetUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setCity("Oxford");
        userDto.setEmail("jane.doe@example.org");
        userDto.setFirstName("Jane");
        userDto.setId(123L);
        userDto.setImage("Image");
        userDto.setLastName("Doe");
        userDto.setPhone("4105551212");
        userDto.setRegDate("2020-03-01");
        when(userService.getUserDtoByUsername((String) any())).thenReturn(userDto);
        when(myUser.getUsername()).thenReturn("janedoe");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/me");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"id\":123,\"lastName\":\"Doe\",\"phone\":\"4105551212\","
                                        + "\"regDate\":\"2020-03-01\",\"city\":\"Oxford\",\"image\":\"Image\"}"));
    }

    /**
     * Method under test: {@link UserController#setPassword(PasswordDto)}
     */
    @Test
    void testSetPassword() throws Exception {
        doNothing().when(userService).setPassword((PasswordDto) any(), (MyUser) any());

        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setCurrentPassword("iloveyou");
        passwordDto.setNewPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(passwordDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/set_password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link UserController#updateUser(UserDto)}
     */
    @Test
    void testUpdateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setCity("Oxford");
        userDto.setEmail("jane.doe@example.org");
        userDto.setFirstName("Jane");
        userDto.setId(123L);
        userDto.setImage("Image");
        userDto.setLastName("Doe");
        userDto.setPhone("4105551212");
        userDto.setRegDate("2020-03-01");
        when(userService.updateUser((UserDto) any(), (String) any())).thenReturn(userDto);
        when(myUser.getUsername()).thenReturn("janedoe");

        UserDto userDto1 = new UserDto();
        userDto1.setCity("Oxford");
        userDto1.setEmail("jane.doe@example.org");
        userDto1.setFirstName("Jane");
        userDto1.setId(123L);
        userDto1.setImage("Image");
        userDto1.setLastName("Doe");
        userDto1.setPhone("4105551212");
        userDto1.setRegDate("2020-03-01");
        String content = (new ObjectMapper()).writeValueAsString(userDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/users/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"id\":123,\"lastName\":\"Doe\",\"phone\":\"4105551212\","
                                        + "\"regDate\":\"2020-03-01\",\"city\":\"Oxford\",\"image\":\"Image\"}"));
    }

    /**
     * Method under test: {@link UserController#getUser()}
     */
    @Test
    void testGetUser2() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setCity("Oxford");
        userDto.setEmail("jane.doe@example.org");
        userDto.setFirstName("Jane");
        userDto.setId(123L);
        userDto.setImage("Image");
        userDto.setLastName("Doe");
        userDto.setPhone("4105551212");
        userDto.setRegDate("2020-03-01");
        when(userService.getUserDtoByUsername((String) any())).thenReturn(userDto);
        when(myUser.getUsername()).thenReturn("janedoe");
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserController#updateUserImage(MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUserImage() throws Exception {

        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.patch("/users/me/image")
                .contentType(MediaType.APPLICATION_JSON);
        MockMultipartFile value = new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")));

        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString(value));
        MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
    }
}

