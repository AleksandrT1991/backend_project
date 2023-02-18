package ru.skypro.homework.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateAdsDto;
import ru.skypro.homework.dto.ad.FullAdDto;
import ru.skypro.homework.dto.wrappers.ResponseWrapperAds;
import ru.skypro.homework.dto.wrappers.ResponseWrapperComments;
import ru.skypro.homework.security.MyUser;
import ru.skypro.homework.service.AdsImageService;
import ru.skypro.homework.service.AdsService;

@ContextConfiguration(classes = {AdController.class})
@ExtendWith(SpringExtension.class)
class AdControllerTest {
    @Autowired
    private AdController adController;

    @MockBean
    private AdsImageService adsImageService;

    @MockBean
    private AdsService adsService;

    @MockBean
    private MyUser myUser;

    /**
     * Method under test: {@link AdController#getAds()}
     */
    @Test
    void testGetAds() throws Exception {
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(3);
        responseWrapperAds.setResults(new ArrayList<>());
        when(adsService.getAds()).thenReturn(responseWrapperAds);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads");
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"count\":3,\"results\":[]}"));
    }

    /**
     * Method under test: {@link AdController#getComments(Long)}
     */
    @Test
    void testGetComments() throws Exception {
        ResponseWrapperComments responseWrapperComments = new ResponseWrapperComments();
        responseWrapperComments.setCount(3);
        responseWrapperComments.setResults(new ArrayList<>());
        when(adsService.getComments((Long) any())).thenReturn(responseWrapperComments);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/{adPk}/comments", 1L);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"count\":3,\"results\":[]}"));
    }

    /**
     * Method under test: {@link AdController#getComments(Long, Long)}
     */
    @Test
    void testGetComments2() throws Exception {
        AdCommentDto adCommentDto = new AdCommentDto();
        adCommentDto.setAuthor(1L);
        adCommentDto.setCreatedAt("Jan 1, 2020 8:00am GMT+0100");
        adCommentDto.setPk(1L);
        adCommentDto.setText("Text");
        when(adsService.getComments((Long) any(), (Long) any())).thenReturn(adCommentDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/{adPk}/comments/{id}", 1L, 123L);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"author\":1,\"createdAt\":\"Jan 1, 2020 8:00am GMT+0100\",\"pk\":1,\"text\":\"Text\"}"));
    }

    /**
     * Method under test: {@link AdController#addAd(CreateAdsDto, MultipartFile)}
     */
    @Test
    void testAddAd() throws Exception {
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(3);
        responseWrapperAds.setResults(new ArrayList<>());
        when(adsService.getAds()).thenReturn(responseWrapperAds);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/ads");
        MockHttpServletRequestBuilder paramResult = getResult.param("createAdsDto", String.valueOf(new CreateAdsDto()));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("image", String.valueOf((Object) null));
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"count\":3,\"results\":[]}"));
    }

    /**
     * Method under test: {@link AdController#addComments(Long, AdCommentDto)}
     */
    @Test
    void testAddComments() throws Exception {
        AdCommentDto adCommentDto = new AdCommentDto();
        adCommentDto.setAuthor(1L);
        adCommentDto.setCreatedAt("Jan 1, 2020 8:00am GMT+0100");
        adCommentDto.setPk(1L);
        adCommentDto.setText("Text");
        when(adsService.addComments((Long) any(), (AdCommentDto) any())).thenReturn(adCommentDto);

        AdCommentDto adCommentDto1 = new AdCommentDto();
        adCommentDto1.setAuthor(1L);
        adCommentDto1.setCreatedAt("Jan 1, 2020 8:00am GMT+0100");
        adCommentDto1.setPk(1L);
        adCommentDto1.setText("Text");
        String content = (new ObjectMapper()).writeValueAsString(adCommentDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ads/{adPk}/comments", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"author\":1,\"createdAt\":\"Jan 1, 2020 8:00am GMT+0100\",\"pk\":1,\"text\":\"Text\"}"));
    }

    /**
     * Method under test: {@link AdController#getFullAd(Long)}
     */
    @Test
    void testGetFullAd() throws Exception {
        FullAdDto fullAdDto = new FullAdDto();
        fullAdDto.setAuthorFirstName("Jane");
        fullAdDto.setAuthorLastName("Doe");
        fullAdDto.setDescription("The characteristics of someone or something");
        fullAdDto.setEmail("jane.doe@example.org");
        fullAdDto.setImage(new ArrayList<>());
        fullAdDto.setPhone("4105551212");
        fullAdDto.setPk(1L);
        fullAdDto.setPrice(1L);
        fullAdDto.setTitle("Dr");
        when(adsService.getFullAd((Long) any())).thenReturn(fullAdDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/{id}", 123L);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"authorFirstName\":\"Jane\",\"authorLastName\":\"Doe\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"email\":\"jane.doe@example.org\",\"image\":[],\"phone\":\"4105551212\",\"pk\":1,\"price\":1,\"title"
                                        + "\":\"Dr\"}"));
    }

    /**
     * Method under test: {@link AdController#removeAds(Long)}
     */
    @Test
    void testRemoveAds() throws Exception {
        doNothing().when(adsService).removeAds((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/ads/{id}", 123L);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AdController#removeAds(Long)}
     */
    @Test
    void testRemoveAds2() throws Exception {
        doNothing().when(adsService).removeAds((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/ads/{id}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AdController#updateAds(Long, CreateAdsDto)}
     */
    @Test
    void testUpdateAds() throws Exception {
        AdDto adDto = new AdDto();
        adDto.setAuthor(1L);
        adDto.setImage(new ArrayList<>());
        adDto.setPk(1L);
        adDto.setPrice(1L);
        adDto.setTitle("Dr");
        when(adsService.updateAds((Long) any(), (CreateAdsDto) any())).thenReturn(adDto);

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("The characteristics of someone or something");
        createAdsDto.setPrice(1L);
        createAdsDto.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(createAdsDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/ads/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"author\":1,\"image\":[],\"pk\":1,\"price\":1,\"title\":\"Dr\"}"));
    }

    /**
     * Method under test: {@link AdController#deleteComments(Long, Long)}
     */
    @Test
    void testDeleteComments() throws Exception {
        doNothing().when(adsService).deleteComments((Long) any(), (Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/ads/{adPk}/comments/{id}", 1L,
                123L);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AdController#deleteComments(Long, Long)}
     */
    @Test
    void testDeleteComments2() throws Exception {
        doNothing().when(adsService).deleteComments((Long) any(), (Long) any());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link AdController#deleteComments(Long, Long)}
     */
    @Test
    void testDeleteComments3() throws Exception {
        doNothing().when(adsService).deleteComments((Long) any(), (Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/ads/{adPk}/comments/{id}", 1L, 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link AdController#updateComments(Long, Long, AdCommentDto)}
     */
    @Test
    void testUpdateComments() throws Exception {
        AdCommentDto adCommentDto = new AdCommentDto();
        adCommentDto.setAuthor(1L);
        adCommentDto.setCreatedAt("Jan 1, 2020 8:00am GMT+0100");
        adCommentDto.setPk(1L);
        adCommentDto.setText("Text");
        when(adsService.updateComments((Long) any(), (Long) any(), (AdCommentDto) any())).thenReturn(adCommentDto);

        AdCommentDto adCommentDto1 = new AdCommentDto();
        adCommentDto1.setAuthor(1L);
        adCommentDto1.setCreatedAt("Jan 1, 2020 8:00am GMT+0100");
        adCommentDto1.setPk(1L);
        adCommentDto1.setText("Text");
        String content = (new ObjectMapper()).writeValueAsString(adCommentDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/ads/{adPk}/comments/{id}", 1L, 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"author\":1,\"createdAt\":\"Jan 1, 2020 8:00am GMT+0100\",\"pk\":1,\"text\":\"Text\"}"));
    }

    /**
     * Method under test: {@link AdController#getAdsMe()}
     */
    @Test
    void testGetAdsMe() throws Exception {
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(3);
        responseWrapperAds.setResults(new ArrayList<>());
        when(adsService.getAdsMe((String) any())).thenReturn(responseWrapperAds);
        when(myUser.getUsername()).thenReturn("janedoe");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ads/me");
        MockMvcBuilders.standaloneSetup(adController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"count\":3,\"results\":[]}"));
    }
}

