package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.dto.ad.CreateAdDto;
import ru.skypro.homework.dto.ad.FullAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdComment;
import ru.skypro.homework.mappers.ad.AdCommentMapper;
import ru.skypro.homework.mappers.ad.AdsMapper;
import ru.skypro.homework.mappers.ad.CreateAdsMapper;
import ru.skypro.homework.mappers.ad.FullAdMapper;
import ru.skypro.homework.repository.ad.AdCommentRepository;
import ru.skypro.homework.repository.ad.AdRepository;
import ru.skypro.homework.service.AdsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdsServiceImpl implements AdsService {

    private final AdRepository adRepository;
    private final AdCommentRepository adCommentRepository;
    private final AdsMapper adsMapper;

    public AdsServiceImpl(AdRepository adRepository, AdCommentRepository adCommentRepository,
                          AdsMapper adsMapper) {
        this.adRepository = adRepository;
        this.adCommentRepository = adCommentRepository;
        this.adsMapper = adsMapper;
    }

    @Override
    public List<AdDto> getAds() {
        return adRepository.findAll().stream().map(adsMapper::toDto).collect(Collectors.toList());
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
    public List<AdCommentDto> getComments(Long adPk) {
        return adCommentRepository.findAllByPk(adPk).stream().map(AdCommentMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    @Override
    public AdCommentDto addComments(Long adPk, AdCommentDto adCommentDto) {
        AdComment byPk = new AdComment();
        byPk.setPk(adCommentDto.getPk());
        byPk.setText(adCommentDto.getText());
        byPk.setAuthor(adCommentDto.getAuthor());
        byPk.setCreatedAd(adCommentDto.getCreatedAt());
        return AdCommentMapper.INSTANCE.toDto(adCommentRepository.save(byPk));
    }

//    public List<AdComment> getComments(String adPk) {
//        Ad ad = adRepository.findByPk(adPk);
//    }

    public void addComments(String adPk) {
        
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
        Optional<AdComment> byIdAndPk = adCommentRepository.findByIdAndPk(id, adPk);
        byIdAndPk.get().setAuthor(adCommentDto.getAuthor());
        byIdAndPk.get().setText(adCommentDto.getText());
        byIdAndPk.get().setCreatedAd(adCommentDto.getCreatedAt());
        byIdAndPk.get().setPk(adCommentDto.getPk());
        return AdCommentMapper.INSTANCE.toDto(adCommentRepository.save(byIdAndPk.get()));
    }

    @Override
    public Optional<AdCommentDto> getComments(Long id, Long adPk) {
        return Optional.ofNullable(AdCommentMapper.INSTANCE.toDto(adCommentRepository.findByIdAndPk(id, adPk).get()));
    }

    @Override
    public List<AdDto> getAdsMe() {
        Long userId = 1L;
        return adRepository.findAllByAuthor(userId).stream().map(AdsMapper.INSTANCE::toDto).collect(Collectors.toList());
    }

    public void getAdsMe(AdDto ad) {
        
    }
}
