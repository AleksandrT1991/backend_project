package ru.skypro.homework.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateAdsDto;
import ru.skypro.homework.dto.ad.FullAdDto;
import ru.skypro.homework.dto.wrappers.ResponseWrapperAds;
import ru.skypro.homework.dto.wrappers.ResponseWrapperComments;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdComment;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;
import ru.skypro.homework.repository.AdCommentRepository;
import ru.skypro.homework.repository.AdImageRepository;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUser;

@ContextConfiguration(classes = {AdsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdsServiceImplTest {
    @MockBean
    private AdCommentRepository adCommentRepository;

    @MockBean
    private AdImageRepository adImageRepository;

    @MockBean
    private AdRepository adRepository;

    @Autowired
    private AdsServiceImpl adsServiceImpl;

    @MockBean
    private MyUser myUser;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link AdsServiceImpl#getAds()}
     */
    @Test
    void testGetAds() {
        ArrayList<Ad> adList = new ArrayList<>();
        when(adRepository.findAll()).thenReturn(adList);
        ResponseWrapperAds actualAds = adsServiceImpl.getAds();
        assertEquals(0, actualAds.getCount().intValue());
        assertEquals(adList, actualAds.getResults());
        verify(adRepository).findAll();
    }

    /**
     * Method under test: {@link AdsServiceImpl#getAds()}
     */
    @Test
    void testGetAds2() throws UnsupportedEncodingException {
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
        userImage.setMediaType("Metod\"AdsServiceImpl.getAds()\" was called");
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

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ArrayList<AdImage> adImageList = new ArrayList<>();
        ad.setImage(adImageList);
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user1);

        ArrayList<Ad> adList = new ArrayList<>();
        adList.add(ad);
        when(adRepository.findAll()).thenReturn(adList);
        ResponseWrapperAds actualAds = adsServiceImpl.getAds();
        assertEquals(1, actualAds.getCount().intValue());
        List<AdDto> results = actualAds.getResults();
        assertEquals(1, results.size());
        AdDto getResult = results.get(0);
        assertEquals(123L, getResult.getAuthor().longValue());
        assertEquals("Dr", getResult.getTitle());
        assertEquals(1L, getResult.getPrice().longValue());
        assertEquals(1L, getResult.getPk().longValue());
        assertEquals(adImageList, getResult.getImage());
        verify(adRepository).findAll();
    }

    /**
     * Method under test: {@link AdsServiceImpl#getAds()}
     */
    @Test
    void testGetAds3() throws UnsupportedEncodingException {
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
        userImage.setMediaType("Metod\"AdsServiceImpl.getAds()\" was called");
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

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ArrayList<AdImage> adImageList = new ArrayList<>();
        ad.setImage(adImageList);
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user1);

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

        UserImage userImage1 = new UserImage();
        userImage1.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage1.setFilePath("/directory/foo.txt");
        userImage1.setFileSize(3L);
        userImage1.setId(123L);
        userImage1.setMediaType("Metod\"AdsServiceImpl.getAds()\" was called");
        userImage1.setUser(user2);

        User user3 = new User();
        user3.setCity("Oxford");
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(123L);
        user3.setImage(userImage1);
        user3.setLastName("Doe");
        user3.setPassword("iloveyou");
        user3.setPhone("4105551212");
        user3.setRegDate("2020-03-01");
        user3.setUsername("janedoe");

        Ad ad1 = new Ad();
        ad1.setDescription("The characteristics of someone or something");
        ad1.setEmail("jane.doe@example.org");
        ad1.setImage(new ArrayList<>());
        ad1.setPhone("4105551212");
        ad1.setPk(1L);
        ad1.setPrice(1L);
        ad1.setTitle("Dr");
        ad1.setUser(user3);

        ArrayList<Ad> adList = new ArrayList<>();
        adList.add(ad1);
        adList.add(ad);
        when(adRepository.findAll()).thenReturn(adList);
        ResponseWrapperAds actualAds = adsServiceImpl.getAds();
        assertEquals(2, actualAds.getCount().intValue());
        List<AdDto> results = actualAds.getResults();
        assertEquals(2, results.size());
        AdDto getResult = results.get(0);
        assertEquals("Dr", getResult.getTitle());
        AdDto getResult1 = results.get(1);
        assertEquals("Dr", getResult1.getTitle());
        assertEquals(1L, getResult1.getPrice().longValue());
        assertEquals(1L, getResult1.getPk().longValue());
        assertEquals(adImageList, getResult1.getImage());
        assertEquals(123L, getResult1.getAuthor().longValue());
        assertEquals(1L, getResult.getPrice().longValue());
        assertEquals(1L, getResult.getPk().longValue());
        assertEquals(adImageList, getResult.getImage());
        assertEquals(123L, getResult.getAuthor().longValue());
        verify(adRepository).findAll();
    }

    /**
     * Method under test: {@link AdsServiceImpl#getAds()}
     */
    @Test
    void testGetAds4() {
        when(adRepository.findAll()).thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(EntityNotFoundException.class, () -> adsServiceImpl.getAds());
        verify(adRepository).findAll();
    }

    /**
     * Method under test: {@link AdsServiceImpl#getAds()}
     */
    @Test
    void testGetAds5() throws UnsupportedEncodingException {
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
        userImage.setMediaType("Metod\"AdsServiceImpl.getAds()\" was called");
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
        userImage1.setUser(new User());

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

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ad.setImage(new ArrayList<>());
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user2);

        AdImage adImage = new AdImage();
        adImage.setAdPk(ad);
        adImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        adImage.setFilePath("/directory/foo.txt");
        adImage.setFileSize(3L);
        adImage.setId(123L);
        adImage.setMediaType("Media Type");

        ArrayList<AdImage> adImageList = new ArrayList<>();
        adImageList.add(adImage);

        User user3 = new User();
        user3.setCity("Oxford");
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(123L);
        user3.setImage(new UserImage());
        user3.setLastName("Doe");
        user3.setPassword("iloveyou");
        user3.setPhone("4105551212");
        user3.setRegDate("2020-03-01");
        user3.setUsername("janedoe");

        UserImage userImage2 = new UserImage();
        userImage2.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage2.setFilePath("/directory/foo.txt");
        userImage2.setFileSize(3L);
        userImage2.setId(123L);
        userImage2.setMediaType("Media Type");
        userImage2.setUser(user3);

        User user4 = new User();
        user4.setCity("Oxford");
        user4.setEmail("jane.doe@example.org");
        user4.setFirstName("Jane");
        user4.setId(123L);
        user4.setImage(userImage2);
        user4.setLastName("Doe");
        user4.setPassword("iloveyou");
        user4.setPhone("4105551212");
        user4.setRegDate("2020-03-01");
        user4.setUsername("janedoe");

        UserImage userImage3 = new UserImage();
        userImage3.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage3.setFilePath("/directory/foo.txt");
        userImage3.setFileSize(3L);
        userImage3.setId(123L);
        userImage3.setMediaType("Media Type");
        userImage3.setUser(user4);

        User user5 = new User();
        user5.setCity("Oxford");
        user5.setEmail("jane.doe@example.org");
        user5.setFirstName("Jane");
        user5.setId(123L);
        user5.setImage(userImage3);
        user5.setLastName("Doe");
        user5.setPassword("iloveyou");
        user5.setPhone("4105551212");
        user5.setRegDate("2020-03-01");
        user5.setUsername("janedoe");
        Ad ad1 = mock(Ad.class);
        when(ad1.getPk()).thenReturn(1L);
        when(ad1.getPrice()).thenReturn(1L);
        when(ad1.getTitle()).thenReturn("Dr");
        when(ad1.getImage()).thenReturn(adImageList);
        when(ad1.getUser()).thenReturn(user5);
        doNothing().when(ad1).setDescription((String) any());
        doNothing().when(ad1).setEmail((String) any());
        doNothing().when(ad1).setImage((List<AdImage>) any());
        doNothing().when(ad1).setPhone((String) any());
        doNothing().when(ad1).setPk((Long) any());
        doNothing().when(ad1).setPrice((Long) any());
        doNothing().when(ad1).setTitle((String) any());
        doNothing().when(ad1).setUser((User) any());
        ad1.setDescription("The characteristics of someone or something");
        ad1.setEmail("jane.doe@example.org");
        ad1.setImage(new ArrayList<>());
        ad1.setPhone("4105551212");
        ad1.setPk(1L);
        ad1.setPrice(1L);
        ad1.setTitle("Dr");
        ad1.setUser(user1);

        ArrayList<Ad> adList = new ArrayList<>();
        adList.add(ad1);
        when(adRepository.findAll()).thenReturn(adList);
        ResponseWrapperAds actualAds = adsServiceImpl.getAds();
        assertEquals(1, actualAds.getCount().intValue());
        List<AdDto> results = actualAds.getResults();
        assertEquals(1, results.size());
        AdDto getResult = results.get(0);
        assertEquals(123L, getResult.getAuthor().longValue());
        assertEquals("Dr", getResult.getTitle());
        assertEquals(1L, getResult.getPrice().longValue());
        assertEquals(1L, getResult.getPk().longValue());
        assertEquals(1, getResult.getImage().size());
        verify(adRepository).findAll();
        verify(ad1).getPk();
        verify(ad1, atLeast(1)).getPrice();
        verify(ad1).getTitle();
        verify(ad1).getImage();
        verify(ad1, atLeast(1)).getUser();
        verify(ad1).setDescription((String) any());
        verify(ad1).setEmail((String) any());
        verify(ad1).setImage((List<AdImage>) any());
        verify(ad1).setPhone((String) any());
        verify(ad1).setPk((Long) any());
        verify(ad1).setPrice((Long) any());
        verify(ad1).setTitle((String) any());
        verify(ad1).setUser((User) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#getAds()}
     */
    @Test
    void testGetAds6() throws UnsupportedEncodingException {
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
        userImage.setMediaType("Metod\"AdsServiceImpl.getAds()\" was called");
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
        userImage1.setUser(new User());

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

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ad.setImage(new ArrayList<>());
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user2);

        AdImage adImage = new AdImage();
        adImage.setAdPk(ad);
        adImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        adImage.setFilePath("/directory/foo.txt");
        adImage.setFileSize(3L);
        adImage.setId(123L);
        adImage.setMediaType("Media Type");

        UserImage userImage2 = new UserImage();
        userImage2.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage2.setFilePath("/directory/foo.txt");
        userImage2.setFileSize(3L);
        userImage2.setId(123L);
        userImage2.setMediaType("Metod\"AdsServiceImpl.getAds()\" was called");
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

        Ad ad1 = new Ad();
        ad1.setDescription("The characteristics of someone or something");
        ad1.setEmail("jane.doe@example.org");
        ad1.setImage(new ArrayList<>());
        ad1.setPhone("4105551212");
        ad1.setPk(1L);
        ad1.setPrice(1L);
        ad1.setTitle("Dr");
        ad1.setUser(user3);

        AdImage adImage1 = new AdImage();
        adImage1.setAdPk(ad1);
        adImage1.setBytea("AAAAAAAA".getBytes("UTF-8"));
        adImage1.setFilePath("/directory/foo.txt");
        adImage1.setFileSize(3L);
        adImage1.setId(123L);
        adImage1.setMediaType("Metod\"AdsServiceImpl.getAds()\" was called");

        ArrayList<AdImage> adImageList = new ArrayList<>();
        adImageList.add(adImage1);
        adImageList.add(adImage);

        User user4 = new User();
        user4.setCity("Oxford");
        user4.setEmail("jane.doe@example.org");
        user4.setFirstName("Jane");
        user4.setId(123L);
        user4.setImage(new UserImage());
        user4.setLastName("Doe");
        user4.setPassword("iloveyou");
        user4.setPhone("4105551212");
        user4.setRegDate("2020-03-01");
        user4.setUsername("janedoe");

        UserImage userImage3 = new UserImage();
        userImage3.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage3.setFilePath("/directory/foo.txt");
        userImage3.setFileSize(3L);
        userImage3.setId(123L);
        userImage3.setMediaType("Media Type");
        userImage3.setUser(user4);

        User user5 = new User();
        user5.setCity("Oxford");
        user5.setEmail("jane.doe@example.org");
        user5.setFirstName("Jane");
        user5.setId(123L);
        user5.setImage(userImage3);
        user5.setLastName("Doe");
        user5.setPassword("iloveyou");
        user5.setPhone("4105551212");
        user5.setRegDate("2020-03-01");
        user5.setUsername("janedoe");

        UserImage userImage4 = new UserImage();
        userImage4.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage4.setFilePath("/directory/foo.txt");
        userImage4.setFileSize(3L);
        userImage4.setId(123L);
        userImage4.setMediaType("Media Type");
        userImage4.setUser(user5);

        User user6 = new User();
        user6.setCity("Oxford");
        user6.setEmail("jane.doe@example.org");
        user6.setFirstName("Jane");
        user6.setId(123L);
        user6.setImage(userImage4);
        user6.setLastName("Doe");
        user6.setPassword("iloveyou");
        user6.setPhone("4105551212");
        user6.setRegDate("2020-03-01");
        user6.setUsername("janedoe");
        Ad ad2 = mock(Ad.class);
        when(ad2.getPk()).thenReturn(1L);
        when(ad2.getPrice()).thenReturn(1L);
        when(ad2.getTitle()).thenReturn("Dr");
        when(ad2.getImage()).thenReturn(adImageList);
        when(ad2.getUser()).thenReturn(user6);
        doNothing().when(ad2).setDescription((String) any());
        doNothing().when(ad2).setEmail((String) any());
        doNothing().when(ad2).setImage((List<AdImage>) any());
        doNothing().when(ad2).setPhone((String) any());
        doNothing().when(ad2).setPk((Long) any());
        doNothing().when(ad2).setPrice((Long) any());
        doNothing().when(ad2).setTitle((String) any());
        doNothing().when(ad2).setUser((User) any());
        ad2.setDescription("The characteristics of someone or something");
        ad2.setEmail("jane.doe@example.org");
        ad2.setImage(new ArrayList<>());
        ad2.setPhone("4105551212");
        ad2.setPk(1L);
        ad2.setPrice(1L);
        ad2.setTitle("Dr");
        ad2.setUser(user1);

        ArrayList<Ad> adList = new ArrayList<>();
        adList.add(ad2);
        when(adRepository.findAll()).thenReturn(adList);
        ResponseWrapperAds actualAds = adsServiceImpl.getAds();
        assertEquals(1, actualAds.getCount().intValue());
        List<AdDto> results = actualAds.getResults();
        assertEquals(1, results.size());
        AdDto getResult = results.get(0);
        assertEquals(123L, getResult.getAuthor().longValue());
        assertEquals("Dr", getResult.getTitle());
        assertEquals(1L, getResult.getPrice().longValue());
        assertEquals(1L, getResult.getPk().longValue());
        assertEquals(2, getResult.getImage().size());
        verify(adRepository).findAll();
        verify(ad2).getPk();
        verify(ad2, atLeast(1)).getPrice();
        verify(ad2).getTitle();
        verify(ad2).getImage();
        verify(ad2, atLeast(1)).getUser();
        verify(ad2).setDescription((String) any());
        verify(ad2).setEmail((String) any());
        verify(ad2).setImage((List<AdImage>) any());
        verify(ad2).setPhone((String) any());
        verify(ad2).setPk((Long) any());
        verify(ad2).setPrice((Long) any());
        verify(ad2).setTitle((String) any());
        verify(ad2).setUser((User) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#createAd(CreateAdsDto, List)}
     */
    @Test
    void testCreateAd() throws UnsupportedEncodingException {
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

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ad.setImage(new ArrayList<>());
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user1);
        when(adRepository.save((Ad) any())).thenReturn(ad);

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
        when(myUser.getUser()).thenReturn(user4);

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("The characteristics of someone or something");
        createAdsDto.setPrice(1L);
        createAdsDto.setTitle("Dr");
        ArrayList<AdImage> adImageList = new ArrayList<>();
        AdDto actualCreateAdResult = adsServiceImpl.createAd(createAdsDto, adImageList);
        assertEquals(123L, actualCreateAdResult.getAuthor().longValue());
        assertEquals("Dr", actualCreateAdResult.getTitle());
        assertEquals(1L, actualCreateAdResult.getPrice().longValue());
        assertEquals(1L, actualCreateAdResult.getPk().longValue());
        assertEquals(adImageList, actualCreateAdResult.getImage());
        verify(adRepository).save((Ad) any());
        verify(myUser).getUser();
    }

    /**
     * Method under test: {@link AdsServiceImpl#createAd(CreateAdsDto, List)}
     */
    @Test
    void testCreateAd2() throws UnsupportedEncodingException {
        when(adRepository.save((Ad) any())).thenThrow(new EntityNotFoundException("An error occurred"));

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
        when(myUser.getUser()).thenReturn(user2);

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("The characteristics of someone or something");
        createAdsDto.setPrice(1L);
        createAdsDto.setTitle("Dr");
        assertThrows(EntityNotFoundException.class, () -> adsServiceImpl.createAd(createAdsDto, new ArrayList<>()));
        verify(adRepository).save((Ad) any());
        verify(myUser).getUser();
    }

    /**
     * Method under test: {@link AdsServiceImpl#createAd(CreateAdsDto, List)}
     */
    @Test
    void testCreateAd3() throws UnsupportedEncodingException {
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

        UserImage userImage2 = new UserImage();
        userImage2.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage2.setFilePath("/directory/foo.txt");
        userImage2.setFileSize(3L);
        userImage2.setId(123L);
        userImage2.setMediaType("Media Type");
        userImage2.setUser(new User());

        User user2 = new User();
        user2.setCity("Oxford");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        user2.setImage(userImage2);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhone("4105551212");
        user2.setRegDate("2020-03-01");
        user2.setUsername("janedoe");

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ad.setImage(new ArrayList<>());
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user2);

        AdImage adImage = new AdImage();
        adImage.setAdPk(ad);
        adImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        adImage.setFilePath("/directory/foo.txt");
        adImage.setFileSize(3L);
        adImage.setId(123L);
        adImage.setMediaType("Media Type");

        ArrayList<AdImage> adImageList = new ArrayList<>();
        adImageList.add(adImage);

        User user3 = new User();
        user3.setCity("Oxford");
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(123L);
        user3.setImage(new UserImage());
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

        UserImage userImage4 = new UserImage();
        userImage4.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage4.setFilePath("/directory/foo.txt");
        userImage4.setFileSize(3L);
        userImage4.setId(123L);
        userImage4.setMediaType("Media Type");
        userImage4.setUser(user4);

        User user5 = new User();
        user5.setCity("Oxford");
        user5.setEmail("jane.doe@example.org");
        user5.setFirstName("Jane");
        user5.setId(123L);
        user5.setImage(userImage4);
        user5.setLastName("Doe");
        user5.setPassword("iloveyou");
        user5.setPhone("4105551212");
        user5.setRegDate("2020-03-01");
        user5.setUsername("janedoe");
        Ad ad1 = mock(Ad.class);
        when(ad1.getPk()).thenReturn(1L);
        when(ad1.getPrice()).thenReturn(1L);
        when(ad1.getTitle()).thenReturn("Dr");
        when(ad1.getImage()).thenReturn(adImageList);
        when(ad1.getUser()).thenReturn(user5);
        doNothing().when(ad1).setDescription((String) any());
        doNothing().when(ad1).setEmail((String) any());
        doNothing().when(ad1).setImage((List<AdImage>) any());
        doNothing().when(ad1).setPhone((String) any());
        doNothing().when(ad1).setPk((Long) any());
        doNothing().when(ad1).setPrice((Long) any());
        doNothing().when(ad1).setTitle((String) any());
        doNothing().when(ad1).setUser((User) any());
        ad1.setDescription("The characteristics of someone or something");
        ad1.setEmail("jane.doe@example.org");
        ad1.setImage(new ArrayList<>());
        ad1.setPhone("4105551212");
        ad1.setPk(1L);
        ad1.setPrice(1L);
        ad1.setTitle("Dr");
        ad1.setUser(user1);
        when(adRepository.save((Ad) any())).thenReturn(ad1);

        User user6 = new User();
        user6.setCity("Oxford");
        user6.setEmail("jane.doe@example.org");
        user6.setFirstName("Jane");
        user6.setId(123L);
        user6.setImage(new UserImage());
        user6.setLastName("Doe");
        user6.setPassword("iloveyou");
        user6.setPhone("4105551212");
        user6.setRegDate("2020-03-01");
        user6.setUsername("janedoe");

        UserImage userImage5 = new UserImage();
        userImage5.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage5.setFilePath("/directory/foo.txt");
        userImage5.setFileSize(3L);
        userImage5.setId(123L);
        userImage5.setMediaType("Media Type");
        userImage5.setUser(user6);

        User user7 = new User();
        user7.setCity("Oxford");
        user7.setEmail("jane.doe@example.org");
        user7.setFirstName("Jane");
        user7.setId(123L);
        user7.setImage(userImage5);
        user7.setLastName("Doe");
        user7.setPassword("iloveyou");
        user7.setPhone("4105551212");
        user7.setRegDate("2020-03-01");
        user7.setUsername("janedoe");

        UserImage userImage6 = new UserImage();
        userImage6.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage6.setFilePath("/directory/foo.txt");
        userImage6.setFileSize(3L);
        userImage6.setId(123L);
        userImage6.setMediaType("Media Type");
        userImage6.setUser(user7);

        User user8 = new User();
        user8.setCity("Oxford");
        user8.setEmail("jane.doe@example.org");
        user8.setFirstName("Jane");
        user8.setId(123L);
        user8.setImage(userImage6);
        user8.setLastName("Doe");
        user8.setPassword("iloveyou");
        user8.setPhone("4105551212");
        user8.setRegDate("2020-03-01");
        user8.setUsername("janedoe");
        when(myUser.getUser()).thenReturn(user8);

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("The characteristics of someone or something");
        createAdsDto.setPrice(1L);
        createAdsDto.setTitle("Dr");
        AdDto actualCreateAdResult = adsServiceImpl.createAd(createAdsDto, new ArrayList<>());
        assertEquals(123L, actualCreateAdResult.getAuthor().longValue());
        assertEquals("Dr", actualCreateAdResult.getTitle());
        assertEquals(1L, actualCreateAdResult.getPrice().longValue());
        assertEquals(1L, actualCreateAdResult.getPk().longValue());
        assertEquals(1, actualCreateAdResult.getImage().size());
        verify(adRepository).save((Ad) any());
        verify(ad1).getPk();
        verify(ad1, atLeast(1)).getPrice();
        verify(ad1).getTitle();
        verify(ad1).getImage();
        verify(ad1, atLeast(1)).getUser();
        verify(ad1).setDescription((String) any());
        verify(ad1).setEmail((String) any());
        verify(ad1).setImage((List<AdImage>) any());
        verify(ad1).setPhone((String) any());
        verify(ad1).setPk((Long) any());
        verify(ad1).setPrice((Long) any());
        verify(ad1).setTitle((String) any());
        verify(ad1).setUser((User) any());
        verify(myUser).getUser();
    }

    /**
     * Method under test: {@link AdsServiceImpl#getComments(Long)}
     */
    @Test
    void testGetComments() {
        ArrayList<AdComment> adCommentList = new ArrayList<>();
        when(adCommentRepository.findAllByPk((Long) any())).thenReturn(adCommentList);
        ResponseWrapperComments actualComments = adsServiceImpl.getComments(1L);
        assertEquals(0, actualComments.getCount().intValue());
        assertEquals(adCommentList, actualComments.getResults());
        verify(adCommentRepository).findAllByPk((Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#getComments(Long)}
     */
    @Test
    void testGetComments2() {
        when(adCommentRepository.findAllByPk((Long) any())).thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(EntityNotFoundException.class, () -> adsServiceImpl.getComments(1L));
        verify(adCommentRepository).findAllByPk((Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#getComments(Long, Long)}
     */
    @Test
    void testGetComments3() throws UnsupportedEncodingException {
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

        AdComment adComment = new AdComment();
        adComment.setCreatedAt("Jan 1, 2020 8:00am GMT+0100");
        adComment.setId(123L);
        adComment.setPk(1L);
        adComment.setText("Text");
        adComment.setUser(user1);
        Optional<AdComment> ofResult = Optional.of(adComment);
        when(adCommentRepository.findByPkAndId((Long) any(), (Long) any())).thenReturn(ofResult);
        AdCommentDto actualComments = adsServiceImpl.getComments(1L, 123L);
        assertEquals(123L, actualComments.getAuthor().longValue());
        assertEquals("Text", actualComments.getText());
        assertEquals(1L, actualComments.getPk().longValue());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualComments.getCreatedAt());
        verify(adCommentRepository).findByPkAndId((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#getComments(Long, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetComments4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.orElseThrow(Optional.java:382)
        //       at ru.skypro.homework.service.impl.AdsServiceImpl.getComments(AdsServiceImpl.java:136)
        //   See https://diff.blue/R013 to resolve this issue.

        when(adCommentRepository.findByPkAndId((Long) any(), (Long) any())).thenReturn(Optional.empty());
        adsServiceImpl.getComments(1L, 123L);
    }

    /**
     * Method under test: {@link AdsServiceImpl#getComments(Long, Long)}
     */
    @Test
    void testGetComments5() {
        when(adCommentRepository.findByPkAndId((Long) any(), (Long) any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(EntityNotFoundException.class, () -> adsServiceImpl.getComments(1L, 123L));
        verify(adCommentRepository).findByPkAndId((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#addComments(Long, AdCommentDto)}
     */
    @Test
    void testAddComments() throws UnsupportedEncodingException {
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

        AdComment adComment = new AdComment();
        adComment.setCreatedAt("Jan 1, 2020 8:00am GMT+0100");
        adComment.setId(123L);
        adComment.setPk(1L);
        adComment.setText("Text");
        adComment.setUser(user1);
        when(adCommentRepository.save((AdComment) any())).thenReturn(adComment);
        when(adCommentRepository.findAllByPk((Long) any())).thenReturn(new ArrayList<>());

        AdCommentDto adCommentDto = new AdCommentDto();
        adCommentDto.setAuthor(1L);
        adCommentDto.setCreatedAt("Jan 1, 2020 8:00am GMT+0100");
        adCommentDto.setPk(1L);
        adCommentDto.setText("Text");
        AdCommentDto actualAddCommentsResult = adsServiceImpl.addComments(1L, adCommentDto);
        assertEquals(123L, actualAddCommentsResult.getAuthor().longValue());
        assertEquals("Text", actualAddCommentsResult.getText());
        assertEquals(1L, actualAddCommentsResult.getPk().longValue());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualAddCommentsResult.getCreatedAt());
        verify(adCommentRepository).save((AdComment) any());
        verify(adCommentRepository).findAllByPk((Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#getFullAd(Long)}
     */
    @Test
    void testGetFullAd() throws UnsupportedEncodingException {
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

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ArrayList<AdImage> adImageList = new ArrayList<>();
        ad.setImage(adImageList);
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user1);
        when(adRepository.findByPk((Long) any())).thenReturn(ad);
        FullAdDto actualFullAd = adsServiceImpl.getFullAd(123L);
        assertEquals("Jane", actualFullAd.getAuthorFirstName());
        assertEquals("Dr", actualFullAd.getTitle());
        assertEquals(1L, actualFullAd.getPrice().longValue());
        assertEquals(1L, actualFullAd.getPk().longValue());
        assertEquals("4105551212", actualFullAd.getPhone());
        assertEquals(adImageList, actualFullAd.getImage());
        assertEquals("jane.doe@example.org", actualFullAd.getEmail());
        assertEquals("The characteristics of someone or something", actualFullAd.getDescription());
        assertEquals("Doe", actualFullAd.getAuthorLastName());
        verify(adRepository).findByPk((Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#getFullAd(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetFullAd2() throws UnsupportedEncodingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   javax.persistence.EntityNotFoundException: An error occurred
        //       at ru.skypro.homework.mappers.ad.FullAdMapper.toDto(FullAdMapper.java:27)
        //       at ru.skypro.homework.service.impl.AdsServiceImpl.getFullAd(AdsServiceImpl.java:108)
        //   See https://diff.blue/R013 to resolve this issue.

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
        Ad ad = mock(Ad.class);
        when(ad.getPk()).thenThrow(new EntityNotFoundException("An error occurred"));
        when(ad.getPrice()).thenThrow(new EntityNotFoundException("An error occurred"));
        when(ad.getDescription()).thenThrow(new EntityNotFoundException("An error occurred"));
        when(ad.getEmail()).thenThrow(new EntityNotFoundException("An error occurred"));
        when(ad.getPhone()).thenThrow(new EntityNotFoundException("An error occurred"));
        when(ad.getTitle()).thenThrow(new EntityNotFoundException("An error occurred"));
        when(ad.getImage()).thenThrow(new EntityNotFoundException("An error occurred"));
        when(ad.getUser()).thenReturn(user4);
        doNothing().when(ad).setDescription((String) any());
        doNothing().when(ad).setEmail((String) any());
        doNothing().when(ad).setImage((List<AdImage>) any());
        doNothing().when(ad).setPhone((String) any());
        doNothing().when(ad).setPk((Long) any());
        doNothing().when(ad).setPrice((Long) any());
        doNothing().when(ad).setTitle((String) any());
        doNothing().when(ad).setUser((User) any());
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ad.setImage(new ArrayList<>());
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user1);
        when(adRepository.findByPk((Long) any())).thenReturn(ad);
        adsServiceImpl.getFullAd(123L);
    }

    /**
     * Method under test: {@link AdsServiceImpl#removeAds(Long)}
     */
    @Test
    void testRemoveAds() {
        doNothing().when(adRepository).deleteAdByPk((Long) any());
        doNothing().when(adCommentRepository).deleteAdCommentByPk((Long) any());
        doNothing().when(adImageRepository).deleteAdImageByAdPk_Pk((Long) any());
        adsServiceImpl.removeAds(1L);
        verify(adRepository).deleteAdByPk((Long) any());
        verify(adCommentRepository).deleteAdCommentByPk((Long) any());
        verify(adImageRepository).deleteAdImageByAdPk_Pk((Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#removeAds(Long)}
     */
    @Test
    void testRemoveAds2() {
        doNothing().when(adRepository).deleteAdByPk((Long) any());
        doNothing().when(adCommentRepository).deleteAdCommentByPk((Long) any());
        doThrow(new EntityNotFoundException("An error occurred")).when(adImageRepository)
                .deleteAdImageByAdPk_Pk((Long) any());
        assertThrows(EntityNotFoundException.class, () -> adsServiceImpl.removeAds(1L));
        verify(adImageRepository).deleteAdImageByAdPk_Pk((Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#removeAds(Long)}
     */
    @Test
    void testRemoveAds3() {
        doNothing().when(adRepository).deleteAdByPk((Long) any());
        doThrow(new EntityNotFoundException("An error occurred")).when(adCommentRepository)
                .deleteAdCommentByPk((Long) any());
        doNothing().when(adImageRepository).deleteAdImageByAdPk_Pk((Long) any());
        assertThrows(EntityNotFoundException.class, () -> adsServiceImpl.removeAds(1L));
        verify(adCommentRepository).deleteAdCommentByPk((Long) any());
        verify(adImageRepository).deleteAdImageByAdPk_Pk((Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#updateAds(Long, CreateAdsDto)}
     */
    @Test
    void testUpdateAds() throws UnsupportedEncodingException {
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

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ArrayList<AdImage> adImageList = new ArrayList<>();
        ad.setImage(adImageList);
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user1);

        UserImage userImage2 = new UserImage();
        userImage2.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage2.setFilePath("/directory/foo.txt");
        userImage2.setFileSize(3L);
        userImage2.setId(123L);
        userImage2.setMediaType("Media Type");
        userImage2.setUser(new User());

        User user2 = new User();
        user2.setCity("Oxford");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        user2.setImage(userImage2);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhone("4105551212");
        user2.setRegDate("2020-03-01");
        user2.setUsername("janedoe");

        UserImage userImage3 = new UserImage();
        userImage3.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage3.setFilePath("/directory/foo.txt");
        userImage3.setFileSize(3L);
        userImage3.setId(123L);
        userImage3.setMediaType("Media Type");
        userImage3.setUser(user2);

        User user3 = new User();
        user3.setCity("Oxford");
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(123L);
        user3.setImage(userImage3);
        user3.setLastName("Doe");
        user3.setPassword("iloveyou");
        user3.setPhone("4105551212");
        user3.setRegDate("2020-03-01");
        user3.setUsername("janedoe");

        Ad ad1 = new Ad();
        ad1.setDescription("The characteristics of someone or something");
        ad1.setEmail("jane.doe@example.org");
        ad1.setImage(new ArrayList<>());
        ad1.setPhone("4105551212");
        ad1.setPk(1L);
        ad1.setPrice(1L);
        ad1.setTitle("Dr");
        ad1.setUser(user3);
        when(adRepository.save((Ad) any())).thenReturn(ad1);
        when(adRepository.findByPk((Long) any())).thenReturn(ad);

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("The characteristics of someone or something");
        createAdsDto.setPrice(1L);
        createAdsDto.setTitle("Dr");
        AdDto actualUpdateAdsResult = adsServiceImpl.updateAds(123L, createAdsDto);
        assertEquals(123L, actualUpdateAdsResult.getAuthor().longValue());
        assertEquals("Dr", actualUpdateAdsResult.getTitle());
        assertEquals(1L, actualUpdateAdsResult.getPrice().longValue());
        assertEquals(1L, actualUpdateAdsResult.getPk().longValue());
        assertEquals(adImageList, actualUpdateAdsResult.getImage());
        verify(adRepository).save((Ad) any());
        verify(adRepository).findByPk((Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#updateAds(Long, CreateAdsDto)}
     */
    @Test
    void testUpdateAds2() throws UnsupportedEncodingException {
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

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ad.setImage(new ArrayList<>());
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user1);
        when(adRepository.save((Ad) any())).thenThrow(new EntityNotFoundException("An error occurred"));
        when(adRepository.findByPk((Long) any())).thenReturn(ad);

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("The characteristics of someone or something");
        createAdsDto.setPrice(1L);
        createAdsDto.setTitle("Dr");
        assertThrows(EntityNotFoundException.class, () -> adsServiceImpl.updateAds(123L, createAdsDto));
        verify(adRepository).save((Ad) any());
        verify(adRepository).findByPk((Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#updateAds(Long, CreateAdsDto)}
     */
    @Test
    void testUpdateAds3() throws UnsupportedEncodingException {
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

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ad.setImage(new ArrayList<>());
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user1);

        UserImage userImage2 = new UserImage();
        userImage2.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage2.setFilePath("/directory/foo.txt");
        userImage2.setFileSize(3L);
        userImage2.setId(123L);
        userImage2.setMediaType("Media Type");
        userImage2.setUser(new User());

        User user2 = new User();
        user2.setCity("Oxford");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        user2.setImage(userImage2);
        user2.setLastName("Doe");
        user2.setPassword("iloveyou");
        user2.setPhone("4105551212");
        user2.setRegDate("2020-03-01");
        user2.setUsername("janedoe");

        UserImage userImage3 = new UserImage();
        userImage3.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage3.setFilePath("/directory/foo.txt");
        userImage3.setFileSize(3L);
        userImage3.setId(123L);
        userImage3.setMediaType("Media Type");
        userImage3.setUser(user2);

        User user3 = new User();
        user3.setCity("Oxford");
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(123L);
        user3.setImage(userImage3);
        user3.setLastName("Doe");
        user3.setPassword("iloveyou");
        user3.setPhone("4105551212");
        user3.setRegDate("2020-03-01");
        user3.setUsername("janedoe");

        UserImage userImage4 = new UserImage();
        userImage4.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage4.setFilePath("/directory/foo.txt");
        userImage4.setFileSize(3L);
        userImage4.setId(123L);
        userImage4.setMediaType("Media Type");
        userImage4.setUser(new User());

        User user4 = new User();
        user4.setCity("Oxford");
        user4.setEmail("jane.doe@example.org");
        user4.setFirstName("Jane");
        user4.setId(123L);
        user4.setImage(userImage4);
        user4.setLastName("Doe");
        user4.setPassword("iloveyou");
        user4.setPhone("4105551212");
        user4.setRegDate("2020-03-01");
        user4.setUsername("janedoe");

        Ad ad1 = new Ad();
        ad1.setDescription("The characteristics of someone or something");
        ad1.setEmail("jane.doe@example.org");
        ad1.setImage(new ArrayList<>());
        ad1.setPhone("4105551212");
        ad1.setPk(1L);
        ad1.setPrice(1L);
        ad1.setTitle("Dr");
        ad1.setUser(user4);

        AdImage adImage = new AdImage();
        adImage.setAdPk(ad1);
        adImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        adImage.setFilePath("/directory/foo.txt");
        adImage.setFileSize(3L);
        adImage.setId(123L);
        adImage.setMediaType("Media Type");

        ArrayList<AdImage> adImageList = new ArrayList<>();
        adImageList.add(adImage);

        User user5 = new User();
        user5.setCity("Oxford");
        user5.setEmail("jane.doe@example.org");
        user5.setFirstName("Jane");
        user5.setId(123L);
        user5.setImage(new UserImage());
        user5.setLastName("Doe");
        user5.setPassword("iloveyou");
        user5.setPhone("4105551212");
        user5.setRegDate("2020-03-01");
        user5.setUsername("janedoe");

        UserImage userImage5 = new UserImage();
        userImage5.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage5.setFilePath("/directory/foo.txt");
        userImage5.setFileSize(3L);
        userImage5.setId(123L);
        userImage5.setMediaType("Media Type");
        userImage5.setUser(user5);

        User user6 = new User();
        user6.setCity("Oxford");
        user6.setEmail("jane.doe@example.org");
        user6.setFirstName("Jane");
        user6.setId(123L);
        user6.setImage(userImage5);
        user6.setLastName("Doe");
        user6.setPassword("iloveyou");
        user6.setPhone("4105551212");
        user6.setRegDate("2020-03-01");
        user6.setUsername("janedoe");

        UserImage userImage6 = new UserImage();
        userImage6.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage6.setFilePath("/directory/foo.txt");
        userImage6.setFileSize(3L);
        userImage6.setId(123L);
        userImage6.setMediaType("Media Type");
        userImage6.setUser(user6);

        User user7 = new User();
        user7.setCity("Oxford");
        user7.setEmail("jane.doe@example.org");
        user7.setFirstName("Jane");
        user7.setId(123L);
        user7.setImage(userImage6);
        user7.setLastName("Doe");
        user7.setPassword("iloveyou");
        user7.setPhone("4105551212");
        user7.setRegDate("2020-03-01");
        user7.setUsername("janedoe");
        Ad ad2 = mock(Ad.class);
        when(ad2.getPk()).thenReturn(1L);
        when(ad2.getPrice()).thenReturn(1L);
        when(ad2.getTitle()).thenReturn("Dr");
        when(ad2.getImage()).thenReturn(adImageList);
        when(ad2.getUser()).thenReturn(user7);
        doNothing().when(ad2).setDescription((String) any());
        doNothing().when(ad2).setEmail((String) any());
        doNothing().when(ad2).setImage((List<AdImage>) any());
        doNothing().when(ad2).setPhone((String) any());
        doNothing().when(ad2).setPk((Long) any());
        doNothing().when(ad2).setPrice((Long) any());
        doNothing().when(ad2).setTitle((String) any());
        doNothing().when(ad2).setUser((User) any());
        ad2.setDescription("The characteristics of someone or something");
        ad2.setEmail("jane.doe@example.org");
        ad2.setImage(new ArrayList<>());
        ad2.setPhone("4105551212");
        ad2.setPk(1L);
        ad2.setPrice(1L);
        ad2.setTitle("Dr");
        ad2.setUser(user3);
        when(adRepository.save((Ad) any())).thenReturn(ad2);
        when(adRepository.findByPk((Long) any())).thenReturn(ad);

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("The characteristics of someone or something");
        createAdsDto.setPrice(1L);
        createAdsDto.setTitle("Dr");
        AdDto actualUpdateAdsResult = adsServiceImpl.updateAds(123L, createAdsDto);
        assertEquals(123L, actualUpdateAdsResult.getAuthor().longValue());
        assertEquals("Dr", actualUpdateAdsResult.getTitle());
        assertEquals(1L, actualUpdateAdsResult.getPrice().longValue());
        assertEquals(1L, actualUpdateAdsResult.getPk().longValue());
        assertEquals(1, actualUpdateAdsResult.getImage().size());
        verify(adRepository).save((Ad) any());
        verify(adRepository).findByPk((Long) any());
        verify(ad2).getPk();
        verify(ad2, atLeast(1)).getPrice();
        verify(ad2).getTitle();
        verify(ad2).getImage();
        verify(ad2, atLeast(1)).getUser();
        verify(ad2).setDescription((String) any());
        verify(ad2).setEmail((String) any());
        verify(ad2).setImage((List<AdImage>) any());
        verify(ad2).setPhone((String) any());
        verify(ad2).setPk((Long) any());
        verify(ad2).setPrice((Long) any());
        verify(ad2).setTitle((String) any());
        verify(ad2).setUser((User) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#deleteComments(Long, Long)}
     */
    @Test
    void testDeleteComments() {
        doNothing().when(adCommentRepository).deleteByIdAndPk((Long) any(), (Long) any());
        adsServiceImpl.deleteComments(123L, 1L);
        verify(adCommentRepository).deleteByIdAndPk((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#deleteComments(Long, Long)}
     */
    @Test
    void testDeleteComments2() {
        doThrow(new EntityNotFoundException("An error occurred")).when(adCommentRepository)
                .deleteByIdAndPk((Long) any(), (Long) any());
        assertThrows(EntityNotFoundException.class, () -> adsServiceImpl.deleteComments(123L, 1L));
        verify(adCommentRepository).deleteByIdAndPk((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#updateComments(Long, Long, AdCommentDto)}
     */
    @Test
    void testUpdateComments() throws UnsupportedEncodingException {
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

        AdComment adComment = new AdComment();
        adComment.setCreatedAt("Jan 1, 2020 8:00am GMT+0100");
        adComment.setId(123L);
        adComment.setPk(1L);
        adComment.setText("Text");
        adComment.setUser(user1);
        Optional<AdComment> ofResult = Optional.of(adComment);

        UserImage userImage1 = new UserImage();
        userImage1.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage1.setFilePath("/directory/foo.txt");
        userImage1.setFileSize(3L);
        userImage1.setId(123L);
        userImage1.setMediaType("Media Type");
        userImage1.setUser(new User());

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

        AdComment adComment1 = new AdComment();
        adComment1.setCreatedAt("Jan 1, 2020 8:00am GMT+0100");
        adComment1.setId(123L);
        adComment1.setPk(1L);
        adComment1.setText("Text");
        adComment1.setUser(user3);
        when(adCommentRepository.save((AdComment) any())).thenReturn(adComment1);
        when(adCommentRepository.findByPkAndId((Long) any(), (Long) any())).thenReturn(ofResult);

        AdCommentDto adCommentDto = new AdCommentDto();
        adCommentDto.setAuthor(1L);
        adCommentDto.setCreatedAt("Jan 1, 2020 8:00am GMT+0100");
        adCommentDto.setPk(1L);
        adCommentDto.setText("Text");
        AdCommentDto actualUpdateCommentsResult = adsServiceImpl.updateComments(123L, 1L, adCommentDto);
        assertEquals(123L, actualUpdateCommentsResult.getAuthor().longValue());
        assertEquals("Text", actualUpdateCommentsResult.getText());
        assertEquals(1L, actualUpdateCommentsResult.getPk().longValue());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualUpdateCommentsResult.getCreatedAt());
        verify(adCommentRepository).save((AdComment) any());
        verify(adCommentRepository).findByPkAndId((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#updateComments(Long, Long, AdCommentDto)}
     */
    @Test
    void testUpdateComments2() throws UnsupportedEncodingException {
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

        AdComment adComment = new AdComment();
        adComment.setCreatedAt("Jan 1, 2020 8:00am GMT+0100");
        adComment.setId(123L);
        adComment.setPk(1L);
        adComment.setText("Text");
        adComment.setUser(user1);
        Optional<AdComment> ofResult = Optional.of(adComment);
        when(adCommentRepository.save((AdComment) any())).thenThrow(new EntityNotFoundException("An error occurred"));
        when(adCommentRepository.findByPkAndId((Long) any(), (Long) any())).thenReturn(ofResult);

        AdCommentDto adCommentDto = new AdCommentDto();
        adCommentDto.setAuthor(1L);
        adCommentDto.setCreatedAt("Jan 1, 2020 8:00am GMT+0100");
        adCommentDto.setPk(1L);
        adCommentDto.setText("Text");
        assertThrows(EntityNotFoundException.class, () -> adsServiceImpl.updateComments(123L, 1L, adCommentDto));
        verify(adCommentRepository).save((AdComment) any());
        verify(adCommentRepository).findByPkAndId((Long) any(), (Long) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#getAdsMe(String)}
     */
    @Test
    void testGetAdsMe() throws UnsupportedEncodingException {
        ArrayList<Ad> adList = new ArrayList<>();
        when(adRepository.findAllByUser_Id((Long) any())).thenReturn(adList);

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
        ResponseWrapperAds actualAdsMe = adsServiceImpl.getAdsMe("janedoe");
        assertEquals(0, actualAdsMe.getCount().intValue());
        assertEquals(adList, actualAdsMe.getResults());
        verify(adRepository).findAllByUser_Id((Long) any());
        verify(userRepository).findUserByUsername((String) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#getAdsMe(String)}
     */
    @Test
    void testGetAdsMe2() throws UnsupportedEncodingException {
        when(adRepository.findAllByUser_Id((Long) any())).thenThrow(new EntityNotFoundException("An error occurred"));

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
        assertThrows(EntityNotFoundException.class, () -> adsServiceImpl.getAdsMe("janedoe"));
        verify(adRepository).findAllByUser_Id((Long) any());
        verify(userRepository).findUserByUsername((String) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#getAdsMe(String)}
     */
    @Test
    void testGetAdsMe3() throws UnsupportedEncodingException {
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
        userImage.setMediaType("Metod\"AdsServiceImpl.getAdsMe()\" was called");
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

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ArrayList<AdImage> adImageList = new ArrayList<>();
        ad.setImage(adImageList);
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user1);

        ArrayList<Ad> adList = new ArrayList<>();
        adList.add(ad);
        when(adRepository.findAllByUser_Id((Long) any())).thenReturn(adList);

        UserImage userImage1 = new UserImage();
        userImage1.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage1.setFilePath("/directory/foo.txt");
        userImage1.setFileSize(3L);
        userImage1.setId(123L);
        userImage1.setMediaType("Media Type");
        userImage1.setUser(new User());

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
        Optional<User> ofResult = Optional.of(user3);
        when(userRepository.findUserByUsername((String) any())).thenReturn(ofResult);
        ResponseWrapperAds actualAdsMe = adsServiceImpl.getAdsMe("janedoe");
        assertEquals(1, actualAdsMe.getCount().intValue());
        List<AdDto> results = actualAdsMe.getResults();
        assertEquals(1, results.size());
        AdDto getResult = results.get(0);
        assertEquals(123L, getResult.getAuthor().longValue());
        assertEquals("Dr", getResult.getTitle());
        assertEquals(1L, getResult.getPrice().longValue());
        assertEquals(1L, getResult.getPk().longValue());
        assertEquals(adImageList, getResult.getImage());
        verify(adRepository).findAllByUser_Id((Long) any());
        verify(userRepository).findUserByUsername((String) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#getAdsMe(String)}
     */
    @Test
    void testGetAdsMe4() throws UnsupportedEncodingException {
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
        userImage.setMediaType("Metod\"AdsServiceImpl.getAdsMe()\" was called");
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

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ArrayList<AdImage> adImageList = new ArrayList<>();
        ad.setImage(adImageList);
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user1);

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

        UserImage userImage1 = new UserImage();
        userImage1.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage1.setFilePath("/directory/foo.txt");
        userImage1.setFileSize(3L);
        userImage1.setId(123L);
        userImage1.setMediaType("Metod\"AdsServiceImpl.getAdsMe()\" was called");
        userImage1.setUser(user2);

        User user3 = new User();
        user3.setCity("Oxford");
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(123L);
        user3.setImage(userImage1);
        user3.setLastName("Doe");
        user3.setPassword("iloveyou");
        user3.setPhone("4105551212");
        user3.setRegDate("2020-03-01");
        user3.setUsername("janedoe");

        Ad ad1 = new Ad();
        ad1.setDescription("The characteristics of someone or something");
        ad1.setEmail("jane.doe@example.org");
        ad1.setImage(new ArrayList<>());
        ad1.setPhone("4105551212");
        ad1.setPk(1L);
        ad1.setPrice(1L);
        ad1.setTitle("Dr");
        ad1.setUser(user3);

        ArrayList<Ad> adList = new ArrayList<>();
        adList.add(ad1);
        adList.add(ad);
        when(adRepository.findAllByUser_Id((Long) any())).thenReturn(adList);

        UserImage userImage2 = new UserImage();
        userImage2.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage2.setFilePath("/directory/foo.txt");
        userImage2.setFileSize(3L);
        userImage2.setId(123L);
        userImage2.setMediaType("Media Type");
        userImage2.setUser(new User());

        User user4 = new User();
        user4.setCity("Oxford");
        user4.setEmail("jane.doe@example.org");
        user4.setFirstName("Jane");
        user4.setId(123L);
        user4.setImage(userImage2);
        user4.setLastName("Doe");
        user4.setPassword("iloveyou");
        user4.setPhone("4105551212");
        user4.setRegDate("2020-03-01");
        user4.setUsername("janedoe");

        UserImage userImage3 = new UserImage();
        userImage3.setBytea("AAAAAAAA".getBytes("UTF-8"));
        userImage3.setFilePath("/directory/foo.txt");
        userImage3.setFileSize(3L);
        userImage3.setId(123L);
        userImage3.setMediaType("Media Type");
        userImage3.setUser(user4);

        User user5 = new User();
        user5.setCity("Oxford");
        user5.setEmail("jane.doe@example.org");
        user5.setFirstName("Jane");
        user5.setId(123L);
        user5.setImage(userImage3);
        user5.setLastName("Doe");
        user5.setPassword("iloveyou");
        user5.setPhone("4105551212");
        user5.setRegDate("2020-03-01");
        user5.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user5);
        when(userRepository.findUserByUsername((String) any())).thenReturn(ofResult);
        ResponseWrapperAds actualAdsMe = adsServiceImpl.getAdsMe("janedoe");
        assertEquals(2, actualAdsMe.getCount().intValue());
        List<AdDto> results = actualAdsMe.getResults();
        assertEquals(2, results.size());
        AdDto getResult = results.get(0);
        assertEquals("Dr", getResult.getTitle());
        AdDto getResult1 = results.get(1);
        assertEquals("Dr", getResult1.getTitle());
        assertEquals(1L, getResult1.getPrice().longValue());
        assertEquals(1L, getResult1.getPk().longValue());
        assertEquals(adImageList, getResult1.getImage());
        assertEquals(123L, getResult1.getAuthor().longValue());
        assertEquals(1L, getResult.getPrice().longValue());
        assertEquals(1L, getResult.getPk().longValue());
        assertEquals(adImageList, getResult.getImage());
        assertEquals(123L, getResult.getAuthor().longValue());
        verify(adRepository).findAllByUser_Id((Long) any());
        verify(userRepository).findUserByUsername((String) any());
    }
}

