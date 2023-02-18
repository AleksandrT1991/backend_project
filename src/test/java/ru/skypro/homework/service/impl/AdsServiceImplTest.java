package ru.skypro.homework.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;
import ru.skypro.homework.mappers.ad.AdsMapper;
import ru.skypro.homework.mappers.ad.FullAdMapper;
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

    @MockBean
    private AdsMapper adsMapper;

    @Autowired
    private AdsServiceImpl adsServiceImpl;

    @MockBean
    private FullAdMapper fullAdMapper;

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
        ad.setImage(new ArrayList<>());
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user1);

        ArrayList<Ad> adList = new ArrayList<>();
        adList.add(ad);
        when(adRepository.findAll()).thenReturn(adList);

        AdDto adDto = new AdDto();
        adDto.setAuthor(1L);
        adDto.setImage(new ArrayList<>());
        adDto.setPk(1L);
        adDto.setPrice(1L);
        adDto.setTitle("Dr");
        when(adsMapper.toDto((Ad) any())).thenReturn(adDto);
        ResponseWrapperAds actualAds = adsServiceImpl.getAds();
        assertEquals(1, actualAds.getCount().intValue());
        assertEquals(1, actualAds.getResults().size());
        verify(adRepository).findAll();
        verify(adsMapper).toDto((Ad) any());
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
        ad.setImage(new ArrayList<>());
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

        AdDto adDto = new AdDto();
        adDto.setAuthor(1L);
        adDto.setImage(new ArrayList<>());
        adDto.setPk(1L);
        adDto.setPrice(1L);
        adDto.setTitle("Dr");
        when(adsMapper.toDto((Ad) any())).thenReturn(adDto);
        ResponseWrapperAds actualAds = adsServiceImpl.getAds();
        assertEquals(2, actualAds.getCount().intValue());
        assertEquals(2, actualAds.getResults().size());
        verify(adRepository).findAll();
        verify(adsMapper, atLeast(1)).toDto((Ad) any());
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

        AdDto adDto = new AdDto();
        adDto.setAuthor(1L);
        adDto.setImage(new ArrayList<>());
        adDto.setPk(1L);
        adDto.setPrice(1L);
        adDto.setTitle("Dr");
        when(adsMapper.toDto((Ad) any())).thenReturn(adDto);

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("The characteristics of someone or something");
        createAdsDto.setPrice(1L);
        createAdsDto.setTitle("Dr");
        assertSame(adDto, adsServiceImpl.createAd(createAdsDto, new ArrayList<>()));
        verify(adRepository).save((Ad) any());
        verify(myUser).getUser();
        verify(adsMapper).toDto((Ad) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#createAd(CreateAdsDto, List)}
     */
    @Test
    void testCreateAd2() throws UnsupportedEncodingException {
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
        when(adsMapper.toDto((Ad) any())).thenThrow(new EntityNotFoundException("An error occurred"));

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("The characteristics of someone or something");
        createAdsDto.setPrice(1L);
        createAdsDto.setTitle("Dr");
        assertThrows(EntityNotFoundException.class, () -> adsServiceImpl.createAd(createAdsDto, new ArrayList<>()));
        verify(adRepository).save((Ad) any());
        verify(myUser).getUser();
        verify(adsMapper).toDto((Ad) any());
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
        //       at ru.skypro.homework.service.impl.AdsServiceImpl.getComments(AdsServiceImpl.java:157)
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
        ad.setImage(new ArrayList<>());
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user1);
        when(adRepository.findByPk((Long) any())).thenReturn(ad);

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
        when(fullAdMapper.toDto((Ad) any())).thenReturn(fullAdDto);
        assertSame(fullAdDto, adsServiceImpl.getFullAd(123L));
        verify(adRepository).findByPk((Long) any());
        verify(fullAdMapper).toDto((Ad) any());
    }

    /**
     * Method under test: {@link AdsServiceImpl#getFullAd(Long)}
     */
    @Test
    void testGetFullAd2() throws UnsupportedEncodingException {
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
        when(adRepository.findByPk((Long) any())).thenReturn(ad);
        when(fullAdMapper.toDto((Ad) any())).thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(EntityNotFoundException.class, () -> adsServiceImpl.getFullAd(123L));
        verify(adRepository).findByPk((Long) any());
        verify(fullAdMapper).toDto((Ad) any());
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

        AdDto adDto = new AdDto();
        adDto.setAuthor(1L);
        adDto.setImage(new ArrayList<>());
        adDto.setPk(1L);
        adDto.setPrice(1L);
        adDto.setTitle("Dr");
        when(adsMapper.toDto((Ad) any())).thenReturn(adDto);

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("The characteristics of someone or something");
        createAdsDto.setPrice(1L);
        createAdsDto.setTitle("Dr");
        assertSame(adDto, adsServiceImpl.updateAds(123L, createAdsDto));
        verify(adRepository).save((Ad) any());
        verify(adRepository).findByPk((Long) any());
        verify(adsMapper).toDto((Ad) any());
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
        when(adsMapper.toDto((Ad) any())).thenThrow(new EntityNotFoundException("An error occurred"));

        CreateAdsDto createAdsDto = new CreateAdsDto();
        createAdsDto.setDescription("The characteristics of someone or something");
        createAdsDto.setPrice(1L);
        createAdsDto.setTitle("Dr");
        assertThrows(EntityNotFoundException.class, () -> adsServiceImpl.updateAds(123L, createAdsDto));
        verify(adRepository).save((Ad) any());
        verify(adRepository).findByPk((Long) any());
        verify(adsMapper).toDto((Ad) any());
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
        ad.setImage(new ArrayList<>());
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user1);

        ArrayList<Ad> adList = new ArrayList<>();
        adList.add(ad);
        when(adRepository.findAllByUser_Id((Long) any())).thenReturn(adList);

        AdDto adDto = new AdDto();
        adDto.setAuthor(1L);
        adDto.setImage(new ArrayList<>());
        adDto.setPk(1L);
        adDto.setPrice(1L);
        adDto.setTitle("Dr");
        when(adsMapper.toDto((Ad) any())).thenReturn(adDto);

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
        assertEquals(1, actualAdsMe.getResults().size());
        verify(adRepository).findAllByUser_Id((Long) any());
        verify(adsMapper).toDto((Ad) any());
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
        ad.setImage(new ArrayList<>());
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

        AdDto adDto = new AdDto();
        adDto.setAuthor(1L);
        adDto.setImage(new ArrayList<>());
        adDto.setPk(1L);
        adDto.setPrice(1L);
        adDto.setTitle("Dr");
        when(adsMapper.toDto((Ad) any())).thenReturn(adDto);

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
        assertEquals(2, actualAdsMe.getResults().size());
        verify(adRepository).findAllByUser_Id((Long) any());
        verify(adsMapper, atLeast(1)).toDto((Ad) any());
        verify(userRepository).findUserByUsername((String) any());
    }
}

