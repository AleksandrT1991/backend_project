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
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mappers.ad.AdCommentMapper;
import ru.skypro.homework.mappers.ad.AdsMapper;
import ru.skypro.homework.mappers.ad.CreateAdsMapper;
import ru.skypro.homework.mappers.ad.FullAdMapper;
import ru.skypro.homework.repository.AdCommentRepository;
import ru.skypro.homework.repository.AdImageRepository;
import ru.skypro.homework.repository.AdRepository;
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


    public AdsServiceImpl(AdRepository adRepository, AdCommentRepository adCommentRepository, AdImageRepository adImageRepository) {
        this.adRepository = adRepository;
        this.adCommentRepository = adCommentRepository;
        this.adImageRepository = adImageRepository;
    }
    /**
     * event recording process
     */
    private final Logger logger = LoggerFactory.getLogger(AdsServiceImpl.class);

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
    public AdDto createAd(CreateAdsDto createAdsDto) {
        logger.info("Metod\"AdsServiceImpl.createAd()\" was called");
        return AdsMapper.INSTANCE.toDto(
                adRepository.save(
                        CreateAdsMapper.INSTANCE.toEntity(createAdsDto)));
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

        byPk.setText(adCommentDto.getText());
        byPk.setUser(user);
        byPk.setPk(adPk);
        byPk.setCreatedAt(adCommentDto.getCreatedAt());
        return AdCommentMapper.INSTANCE.toDto(adCommentRepository.save(byPk));
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
    public AdCommentDto getComments(Long id, Long adPk) {
        logger.info("Metod\"AdsServiceImpl.getComments()\" was called");
        return AdCommentMapper.INSTANCE.toDto(adCommentRepository.findByIdAndPk(id, adPk).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void deleteComments(Long id, Long adPk) {
        logger.info("Metod\"AdsServiceImpl.deleteComments()\" was called");
        adCommentRepository.deleteByIdAndPk(id, adPk);
    }

    @Override
    public AdCommentDto updateComments(Long id, Long adPk, AdCommentDto adCommentDto) {
        logger.info("Metod\"AdsServiceImpl.updateComments()\" was called");
        Optional<AdComment> byIdAndPk = Optional.ofNullable(adCommentRepository.findByIdAndPk(id, adPk)).orElseThrow(EntityNotFoundException::new);
        if (byIdAndPk.isPresent()) {
            User user = new User();
            user.setId(adCommentDto.getAuthor());

            byIdAndPk.get().setUser(user);
            byIdAndPk.get().setText(adCommentDto.getText());
            byIdAndPk.get().setCreatedAt(adCommentDto.getCreatedAt());
            byIdAndPk.get().setPk(adPk);

            return AdCommentMapper.INSTANCE.toDto(adCommentRepository.save(byIdAndPk.get()));
        } else
            return null;
    }

    @Override
    public ResponseWrapperAds getAdsMe() {
        logger.info("Metod\"AdsServiceImpl.getAdsMe()\" was called");
        Long userId = 1L;
        List<AdDto> ads = adRepository.findAllByUser_Id(userId).stream().map(a-> AdsMapper.INSTANCE.toDto(a)).collect(Collectors.toList());
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(ads.size());
        responseWrapperAds.setResults(ads);
        return responseWrapperAds;
    }
}
