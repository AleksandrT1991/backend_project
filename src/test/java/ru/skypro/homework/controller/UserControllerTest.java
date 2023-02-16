package ru.skypro.homework.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.skypro.homework.service.UserService;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

//    /**
//     * Method under test: {@link UserController#setPassword(PasswordDto)}
//     */
//    @Test
//    void testSetPassword() throws Exception {
//        PasswordDto passwordDto = new PasswordDto();
//        passwordDto.setCurrentPassword("iloveyou");
//        passwordDto.setNewPassword("iloveyou");
//        when(userService.setPassword((PasswordDto) any())).thenReturn(passwordDto);
//
//        PasswordDto passwordDto1 = new PasswordDto();
//        passwordDto1.setCurrentPassword("iloveyou");
//        passwordDto1.setNewPassword("iloveyou");
//        String content = (new ObjectMapper()).writeValueAsString(passwordDto1);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/set_password")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(userController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string("{\"currentPassword\":\"iloveyou\",\"newPassword\":\"iloveyou\"}"));
//    }

//    /**
//     * Method under test: {@link UserController#getUser()}
//     */
//    @Test
//    void testGetUser() throws Exception {
//        UserDto userDto = new UserDto();
//        userDto.setCity("Oxford");
//        userDto.setEmail("jane.doe@example.org");
//        userDto.setFirstName("Jane");
//        userDto.setId(123L);
//        userDto.setImage("Image");
//        userDto.setLastName("Doe");
//        userDto.setPhone("4105551212");
//        userDto.setRegDate("2020-03-01");
//        when(userService.getUser()).thenReturn(userDto);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/me");
//        MockMvcBuilders.standaloneSetup(userController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"id\":123,\"lastName\":\"Doe\",\"phone\":\"4105551212\","
//                                        + "\"regDate\":\"2020-03-01\",\"city\":\"Oxford\",\"image\":\"Image\"}"));
//    }

//    /**
//     * Method under test: {@link UserController#getUser()}
//     */
//    @Test
//    void testGetUser2() throws Exception {
//        UserDto userDto = new UserDto();
//        userDto.setCity("Oxford");
//        userDto.setEmail("jane.doe@example.org");
//        userDto.setFirstName("Jane");
//        userDto.setId(123L);
//        userDto.setImage("Image");
//        userDto.setLastName("Doe");
//        userDto.setPhone("4105551212");
//        userDto.setRegDate("2020-03-01");
//        when(userService.getUser()).thenReturn(userDto);
//        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
//                .formLogin();
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//    /**
//     * Method under test: {@link UserController#updateUser(UserDto)}
//     */
//    @Test
//    void testUpdateUser() throws Exception {
//        UserDto userDto = new UserDto();
//        userDto.setCity("Oxford");
//        userDto.setEmail("jane.doe@example.org");
//        userDto.setFirstName("Jane");
//        userDto.setId(123L);
//        userDto.setImage("Image");
//        userDto.setLastName("Doe");
//        userDto.setPhone("4105551212");
//        userDto.setRegDate("2020-03-01");
//        Optional<UserDto> ofResult = Optional.of(userDto);
//        when(userService.updateUser((UserDto) any())).thenReturn(ofResult);
//
//        UserDto userDto1 = new UserDto();
//        userDto1.setCity("Oxford");
//        userDto1.setEmail("jane.doe@example.org");
//        userDto1.setFirstName("Jane");
//        userDto1.setId(123L);
//        userDto1.setImage("Image");
//        userDto1.setLastName("Doe");
//        userDto1.setPhone("4105551212");
//        userDto1.setRegDate("2020-03-01");
//        String content = (new ObjectMapper()).writeValueAsString(userDto1);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/users/me")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(userController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"id\":123,\"lastName\":\"Doe\",\"phone\":\"4105551212\","
//                                        + "\"regDate\":\"2020-03-01\",\"city\":\"Oxford\",\"image\":\"Image\"}"));
//    }
//
//    /**
//     * Method under test: {@link UserController#updateUser(UserDto)}
//     */
//    @Test
//    void testUpdateUser2() throws Exception {
//        when(userService.updateUser((UserDto) any())).thenReturn(Optional.empty());
//
//        UserDto userDto = new UserDto();
//        userDto.setCity("Oxford");
//        userDto.setEmail("jane.doe@example.org");
//        userDto.setFirstName("Jane");
//        userDto.setId(123L);
//        userDto.setImage("Image");
//        userDto.setLastName("Doe");
//        userDto.setPhone("4105551212");
//        userDto.setRegDate("2020-03-01");
//        String content = (new ObjectMapper()).writeValueAsString(userDto);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/users/me")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//    /**
//     * Method under test: {@link UserController#updateUserImage(MultipartFile)}
//     */
//    @Test
//    void testUpdateUserImage() throws Exception {
//        MockHttpServletRequestBuilder patchResult = MockMvcRequestBuilders.patch("/users/me/image");
//        MockHttpServletRequestBuilder requestBuilder = patchResult.param("image", String.valueOf((Object) null));
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
//    }
}

