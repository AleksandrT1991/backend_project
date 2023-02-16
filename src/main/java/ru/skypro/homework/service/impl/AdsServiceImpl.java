package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
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
import ru.skypro.homework.mappers.ad.AdCommentMapper;
import ru.skypro.homework.mappers.ad.AdsMapper;
import ru.skypro.homework.mappers.ad.CreateAdsMapper;
import ru.skypro.homework.mappers.ad.FullAdMapper;
import ru.skypro.homework.repository.AdCommentRepository;
import ru.skypro.homework.repository.AdImageRepository;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUser;
import ru.skypro.homework.service.AdsService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdsServiceImpl implements AdsService {
    private final AdRepository adRepository;
    private final AdCommentRepository adCommentRepository;
    private final AdImageRepository adImageRepository;

    private final MyUser myUser;


    public AdsServiceImpl(AdRepository adRepository, AdCommentRepository adCommentRepository, AdImageRepository adImageRepository,
                          MyUser myUser, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.adCommentRepository = adCommentRepository;
        this.adImageRepository = adImageRepository;
        this.myUser = myUser;
        this.userRepository = userRepository;
    }
    /**
     * event recording process
     */
    private final Logger logger = LoggerFactory.getLogger(AdsServiceImpl.class);
    private final UserRepository userRepository;

    @Override
    public ResponseWrapperAds getAds() {
        logger.info("Metod\"AdsServiceImpl.getAds()\" was called");
        List<AdDto> ads = adRepository.findAll().stream().map(a->AdsMapper.INSTANCE.toDto(a)).collect(Collectors.toList());
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(ads.size());
        responseWrapperAds.setResults(ads);
        return responseWrapperAds;
    }

    @Override
    public AdDto createAd(CreateAdsDto createAdsDto, List<AdImage> imageSaved) {
        logger.info("Metod\"AdsServiceImpl.createAd()\" was called");
        Ad ad = CreateAdsMapper.INSTANCE.toEntity(createAdsDto);
        ad.setImage(imageSaved);
        User user = new User();
        user.setId(myUser.getUser().getId());
        ad.setUser(user);
        Ad save = adRepository.save(ad);
        return AdsMapper.INSTANCE.toDto(save);
    }

    @Override
    public ResponseWrapperComments getComments(Long adPk) {
        logger.info("Metod\"AdsServiceImpl.getComments()\" was called");
        List<AdCommentDto> comments = adCommentRepository.findAllByPk(adPk).stream().map(AdCommentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
        ResponseWrapperComments responseWrapperComments = new ResponseWrapperComments();
        responseWrapperComments.setCount(comments.size());
        responseWrapperComments.setResults(comments);
        return responseWrapperComments;
    }

    @Override
    public AdCommentDto addComments(Long adPk, AdCommentDto adCommentDto) {
        logger.info("Metod\"AdsServiceImpl.addComments()\" was called");
        List<AdComment> adComments = adCommentRepository.findAllByPk(adPk);
        User user = new User();
        user.setId(adCommentDto.getAuthor());

        AdComment byPk = new AdComment();
        byPk.setId(adComments.size()-1L);
        byPk.setText(adCommentDto.getText());
        byPk.setUser(user);
        byPk.setPk(adPk);
        byPk.setCreatedAt(adCommentDto.getCreatedAt());
        AdComment save = adCommentRepository.save(byPk);
        return AdCommentMapper.INSTANCE.toDto(save);
    }

    @Override
    public FullAdDto getFullAd(Long id) {
        logger.info("Metod\"AdsServiceImpl.getFullAd()\" was called");
        return FullAdMapper.INSTANCE.toDto(
                adRepository.findByPk(id));
    }

    @Override
    public void removeAds(Long pk) {
        logger.info("Metod\"AdsServiceImpl.removeAds()\" was called");
        adImageRepository.deleteAdImageByAdPk_Pk(pk);
        adCommentRepository.deleteAdCommentByPk(pk);
        adRepository.deleteAdByPk(pk);
    }

    @Override
    public AdDto updateAds(Long id, CreateAdsDto createAdsDto) {
        logger.info("Metod\"AdsServiceImpl.updateAds()\" was called");
        Ad byPk = adRepository.findByPk(id);
        byPk.setDescription(createAdsDto.getDescription());
        byPk.setPrice(createAdsDto.getPrice());
        byPk.setTitle(createAdsDto.getTitle());
        return AdsMapper.INSTANCE.toDto(adRepository.save(byPk));
    }

    @Override
    public AdCommentDto getComments(Long pk, Long id) {
        logger.info("Metod\"AdsServiceImpl.getComments()\" was called");
        Optional<AdComment> adComment = adCommentRepository.findByPkAndId(pk, id);
        /** тут почему то падает ошибка фронта потому что он запрашивает два раза пк объявления
         */
        AdCommentDto adCommentDto = AdCommentMapper.INSTANCE.toDto(adComment.orElseThrow());
        return adCommentDto;
    }

    @Override
    public void deleteComments(Long id, Long adPk) {
        logger.info("Metod\"AdsServiceImpl.deleteComments()\" was called");
        adCommentRepository.deleteByIdAndPk(id, adPk);
    }

    @Override
    public AdCommentDto updateComments(Long id, Long adPk, AdCommentDto adCommentDto) {
        logger.info("Metod\"AdsServiceImpl.updateComments()\" was called");
        Optional<AdComment> adComment = adCommentRepository.findByPkAndId(id, adPk);
        if (adComment.isPresent()) {
            User user = new User();
            user.setId(adCommentDto.getAuthor());

            adComment.get().setUser(user);
            adComment.get().setText(adCommentDto.getText());
            adComment.get().setCreatedAt(adCommentDto.getCreatedAt());
            adComment.get().setPk(adPk);

            return AdCommentMapper.INSTANCE.toDto(adCommentRepository.save(adComment.get()));
        } else
            return null;
    }

    @Override
    public ResponseWrapperAds getAdsMe(String username) {
        logger.info("Metod\"AdsServiceImpl.getAdsMe()\" was called");
        Optional<User> userByUsername = userRepository.findUserByUsername(username);
        List<AdDto> ads = adRepository.findAllByUser_Id(userByUsername.orElseThrow(EntityNotFoundException::new)
                                            .getId()).stream()
                                            .map(a-> AdsMapper.INSTANCE.toDto(a)).collect(Collectors.toList());
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(ads.size());
        responseWrapperAds.setResults(ads);
        return responseWrapperAds;
    }
}
