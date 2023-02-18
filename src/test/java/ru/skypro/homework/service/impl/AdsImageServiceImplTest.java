package ru.skypro.homework.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;
import ru.skypro.homework.repository.AdImageRepository;
import ru.skypro.homework.repository.AdRepository;

@ContextConfiguration(classes = {AdsImageServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdsImageServiceImplTest {
    @MockBean
    private AdImageRepository adImageRepository;

    @MockBean
    private AdRepository adRepository;

    @Autowired
    private AdsImageServiceImpl adsImageServiceImpl;

    /**
     * Method under test: {@link AdsImageServiceImpl#createImage(MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateImage() throws IOException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files outside the temporary directory (file '${ad.image.dir.path}', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        adsImageServiceImpl
                .createImage(new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link AdsImageServiceImpl#createImage(MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateImage2() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.nio.file.InvalidPathException: Illegal char <"> at index 26: ${ad.image.dir.path}\Metod"AdsImageServiceImpl.createImage()" was called.
        //       at sun.nio.fs.WindowsPathParser.normalize(WindowsPathParser.java:182)
        //       at sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:153)
        //       at sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:77)
        //       at sun.nio.fs.WindowsPath.parse(WindowsPath.java:92)
        //       at sun.nio.fs.WindowsFileSystem.getPath(WindowsFileSystem.java:229)
        //       at java.nio.file.Path.of(Path.java:147)
        //       at ru.skypro.homework.service.impl.AdsImageServiceImpl.createImage(AdsImageServiceImpl.java:55)
        //   See https://diff.blue/R013 to resolve this issue.

        adsImageServiceImpl.createImage(new MockMultipartFile("Metod\"AdsImageServiceImpl.createImage()\" was called",
                new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link AdsImageServiceImpl#createImage(MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateImage3() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at ru.skypro.homework.service.impl.AdsImageServiceImpl.createImage(AdsImageServiceImpl.java:55)
        //   See https://diff.blue/R013 to resolve this issue.

        adsImageServiceImpl.createImage(null);
    }

    /**
     * Method under test: {@link AdsImageServiceImpl#createImage(MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateImage4() throws IOException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files outside the temporary directory (file '${ad.image.dir.path}', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getName()).thenReturn("Name");
        when(multipartFile.getOriginalFilename()).thenReturn("foo.txt");
        adsImageServiceImpl.createImage(multipartFile);
    }

    /**
     * Method under test: {@link AdsImageServiceImpl#updateAdsImage(Long, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateAdsImage() throws IOException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files outside the temporary directory (file '${ad.image.dir.path}', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        adsImageServiceImpl.updateAdsImage(123L,
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link AdsImageServiceImpl#updateAdsImage(Long, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateAdsImage2() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.nio.file.InvalidPathException: Illegal char <"> at index 26: ${ad.image.dir.path}\Metod"AdsImageServiceImpl.updateAdsImage()" was called.
        //       at sun.nio.fs.WindowsPathParser.normalize(WindowsPathParser.java:182)
        //       at sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:153)
        //       at sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:77)
        //       at sun.nio.fs.WindowsPath.parse(WindowsPath.java:92)
        //       at sun.nio.fs.WindowsFileSystem.getPath(WindowsFileSystem.java:229)
        //       at java.nio.file.Path.of(Path.java:147)
        //       at ru.skypro.homework.service.impl.AdsImageServiceImpl.updateAdsImage(AdsImageServiceImpl.java:78)
        //   See https://diff.blue/R013 to resolve this issue.

        adsImageServiceImpl.updateAdsImage(123L,
                new MockMultipartFile("Metod\"AdsImageServiceImpl.updateAdsImage()\" was called",
                        new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link AdsImageServiceImpl#updateAdsImage(Long, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateAdsImage3() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at ru.skypro.homework.service.impl.AdsImageServiceImpl.updateAdsImage(AdsImageServiceImpl.java:78)
        //   See https://diff.blue/R013 to resolve this issue.

        adsImageServiceImpl.updateAdsImage(123L, null);
    }

    /**
     * Method under test: {@link AdsImageServiceImpl#updateAdsImage(Long, MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateAdsImage4() throws IOException {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files outside the temporary directory (file '${ad.image.dir.path}', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getName()).thenReturn("Name");
        when(multipartFile.getOriginalFilename()).thenReturn("foo.txt");
        adsImageServiceImpl.updateAdsImage(123L, multipartFile);
    }

    /**
     * Method under test: {@link AdsImageServiceImpl#getAdsImage(Long)}
     */
    @Test
    void testGetAdsImage() throws UnsupportedEncodingException {
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

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ad.setImage(new ArrayList<>());
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user);

        AdImage adImage = new AdImage();
        adImage.setAdPk(ad);
        adImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        adImage.setFilePath("/directory/foo.txt");
        adImage.setFileSize(3L);
        adImage.setId(123L);
        adImage.setMediaType("Media Type");
        Optional<AdImage> ofResult = Optional.of(adImage);
        when(adImageRepository.findById((Long) any())).thenReturn(ofResult);
        byte[] actualAdsImage = adsImageServiceImpl.getAdsImage(123L);
        assertEquals(8, actualAdsImage.length);
        assertEquals('A', actualAdsImage[0]);
        assertEquals('A', actualAdsImage[1]);
        assertEquals('A', actualAdsImage[2]);
        assertEquals('A', actualAdsImage[3]);
        assertEquals('A', actualAdsImage[4]);
        assertEquals('A', actualAdsImage[5]);
        assertEquals('A', actualAdsImage[6]);
        assertEquals('A', actualAdsImage[7]);
        verify(adImageRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AdsImageServiceImpl#findAdImage(Long)}
     */
    @Test
    void testFindAdImage() throws UnsupportedEncodingException {
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

        Ad ad = new Ad();
        ad.setDescription("The characteristics of someone or something");
        ad.setEmail("jane.doe@example.org");
        ad.setImage(new ArrayList<>());
        ad.setPhone("4105551212");
        ad.setPk(1L);
        ad.setPrice(1L);
        ad.setTitle("Dr");
        ad.setUser(user);

        AdImage adImage = new AdImage();
        adImage.setAdPk(ad);
        adImage.setBytea("AAAAAAAA".getBytes("UTF-8"));
        adImage.setFilePath("/directory/foo.txt");
        adImage.setFileSize(3L);
        adImage.setId(123L);
        adImage.setMediaType("Media Type");
        Optional<AdImage> ofResult = Optional.of(adImage);
        when(adImageRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(adImage, adsImageServiceImpl.findAdImage(123L));
        verify(adImageRepository).findById((Long) any());
    }
}

