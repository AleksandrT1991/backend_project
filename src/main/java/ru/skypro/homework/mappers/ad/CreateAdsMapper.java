package ru.skypro.homework.mappers.ad;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.CreateAdsDto;
import ru.skypro.homework.entity.Ad;

/**
 * The interface Create ads mapper.
 */
@Mapper
public interface CreateAdsMapper {

    /**
     * The constant INSTANCE.
     */
    CreateAdsMapper INSTANCE = Mappers.getMapper( CreateAdsMapper.class );

    /**
     * To dto create ads dto.
     *
     * @param ad the ad
     * @return the create ads dto
     */
    CreateAdsDto toDto(Ad ad);

    /**
     * To entity ad.
     *
     * @param createAdDto the create ad dto
     * @return the ad
     */
    Ad toEntity(CreateAdsDto createAdDto);
}
