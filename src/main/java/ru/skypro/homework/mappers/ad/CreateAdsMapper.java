package ru.skypro.homework.mappers.ad;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.CreateAdDto;
import ru.skypro.homework.entity.Ad;

@Mapper
public interface CreateAdsMapper {

    CreateAdsMapper INSTANCE = Mappers.getMapper( CreateAdsMapper.class );


    CreateAdDto toDto(Ad ad);

    Ad toEntity(CreateAdDto createAdDto);
}
