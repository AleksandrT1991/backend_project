package ru.skypro.homework.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.skypro.homework.dto.user.LoginReq;
import ru.skypro.homework.dto.user.RegisterReq;
import ru.skypro.homework.service.AuthService;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthService authService;

    /**
     * Method under test: {@link AuthController#login(LoginReq)}
     */
    @Test
    void testLogin() throws Exception {
        when(authService.login((String) any(), (String) any())).thenReturn(true);

        LoginReq loginReq = new LoginReq();
        loginReq.setPassword("iloveyou");
        loginReq.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginReq);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AuthController#login(LoginReq)}
     */
    @Test
    void testLogin2() throws Exception {
        when(authService.login((String) any(), (String) any())).thenReturn(false);

        LoginReq loginReq = new LoginReq();
        loginReq.setPassword("iloveyou");
        loginReq.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginReq);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    /**
     * Method under test: {@link AuthController#register(RegisterReq)}
     */
    @Test
    void testRegister() throws Exception {
        when(authService.register((RegisterReq) any())).thenReturn(true);

        RegisterReq registerReq = new RegisterReq();
        registerReq.setFirstName("Jane");
        registerReq.setLastName("Doe");
        registerReq.setPassword("iloveyou");
        registerReq.setPhone("4105551212");
        registerReq.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(registerReq);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AuthController#register(RegisterReq)}
     */
    @Test
    void testRegister2() throws Exception {
        when(authService.register((RegisterReq) any())).thenReturn(false);

        RegisterReq registerReq = new RegisterReq();
        registerReq.setFirstName("Jane");
        registerReq.setLastName("Doe");
        registerReq.setPassword("iloveyou");
        registerReq.setPhone("4105551212");
        registerReq.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(registerReq);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

