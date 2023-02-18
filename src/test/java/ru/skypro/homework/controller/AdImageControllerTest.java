package ru.skypro.homework.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.AdsImageService;

@ContextConfiguration(classes = {AdImageController.class})
@ExtendWith(SpringExtension.class)
class AdImageControllerTest {
    @Autowired
    private AdImageController adImageController;

    @MockBean
    private AdsImageService adsImageService;

    /**
     * Method under test: {@link AdImageController#getAdsImage(Long)}
     */
    @Test
    void testGetAdsImage() throws Exception {
        when(adsImageService.getAdsImage((Long) any())).thenReturn("AAAAAAAA".getBytes("UTF-8"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/image/{id}", 123L);
        MockMvcBuilders.standaloneSetup(adImageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("image/png"))
                .andExpect(MockMvcResultMatchers.content().string("AAAAAAAA"));
    }

    /**
     * Method under test: {@link AdImageController#getAdsImage(Long)}
     */
    @Test
    void testGetAdsImage2() throws Exception {
        when(adsImageService.getAdsImage((Long) any())).thenReturn("AAAAAAAA".getBytes("UTF-8"));
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adImageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AdImageController#updateAdsImage(Long, MultipartFile)}
     */
    @Test
    void testUpdateAdsImage() throws Exception {
        MockHttpServletRequestBuilder patchResult = MockMvcRequestBuilders.patch("/image/{id}", 123L);
        MockHttpServletRequestBuilder requestBuilder = patchResult.param("image", String.valueOf((Object) null));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adImageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }
}

