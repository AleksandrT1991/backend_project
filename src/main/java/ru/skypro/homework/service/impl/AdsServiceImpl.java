package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateAdDto;
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
import ru.skypro.homework.repository.ad.AdCommentRepository;
import ru.skypro.homework.repository.ad.AdRepository;
import ru.skypro.homework.repository.user.UserRepository;
import ru.skypro.homework.service.AdsService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdsServiceImpl implements AdsService {

    private final AdRepository adRepository;
    private final AdCommentRepository adCommentRepository;
    private final AdsMapper adsMapper;
    private final UserRepository userRepository;

    public AdsServiceImpl(AdRepository adRepository, AdCommentRepository adCommentRepository,
                          AdsMapper adsMapper,
                          UserRepository userRepository) {
        this.adRepository = adRepository;
        this.adCommentRepository = adCommentRepository;
        this.adsMapper = adsMapper;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseWrapperAds getAds() {
        List<AdDto> ads = adRepository.findAll().stream().map(adsMapper::toDto).collect(Collectors.toList());
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(ads.size());
        responseWrapperAds.setResults(ads);
        return responseWrapperAds;
    }

    public void addAd(AdDto ad) {
        adRepository.save(AdsMapper.INSTANCE.toEntity(ad));
    }

    @Override
    public AdDto createAdd(CreateAdDto createAdDto) {
        return AdsMapper.INSTANCE.toDto(
                adRepository.save(
                        CreateAdsMapper.INSTANCE.toEntity(createAdDto)));
    }

    @Override
    public ResponseWrapperComments getComments(Long adPk) {
        List<AdCommentDto> comments = adCommentRepository.findAllByPk(adPk).stream().map(AdCommentMapper.INSTANCE::toDto).collect(Collectors.toList());
        ResponseWrapperComments responseWrapperComments = new ResponseWrapperComments();
        responseWrapperComments.setCount(comments.size());
        responseWrapperComments.setResults(comments);
        return responseWrapperComments;
    }

    @Override
    public AdCommentDto addComments(Long adPk, AdCommentDto adCommentDto) {
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

    public FullAdDto getFullAd(Long id) {
        return FullAdMapper.INSTANCE.toDto(
                adRepository.findByPk(id));
    }

    public void removeAds(Long id) {
        adRepository.deleteAdByPk(id);
    }

    public AdDto updateAds(Long id, CreateAdDto createAdDto) {
        Ad byPk = adRepository.findByPk(id);
        byPk.setDescription(createAdDto.getDescription());
        byPk.setPrice(createAdDto.getPrice());
        byPk.setTitle(createAdDto.getTitle());
        return AdsMapper.INSTANCE.toDto(adRepository.save(byPk));
    }

    @Override
    public void deleteComments(Long id, Long adPk) {
        adCommentRepository.deleteByIdAndPk(id, adPk);
    }

    @Override
    public AdCommentDto updateComments(Long id, Long adPk, AdCommentDto adCommentDto) {
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
    public Optional<AdCommentDto> getComments(Long id, Long adPk) {
        return Optional.ofNullable(AdCommentMapper.INSTANCE.toDto(adCommentRepository.findByIdAndPk(id, adPk).get()));
    }

    @Override
    public ResponseWrapperAds getAdsMe() {
        Long userId = 1L;
        List<AdDto> ads = adRepository.findAllByUser_Id(userId).stream().map(AdsMapper.INSTANCE::toDto).collect(Collectors.toList());
        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(ads.size());
        responseWrapperAds.setResults(ads);
        return responseWrapperAds;
    }
}
