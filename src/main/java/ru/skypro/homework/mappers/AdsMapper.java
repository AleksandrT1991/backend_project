package ru.skypro.homework.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.entity.Ad;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper( AdsMapper.class );

    @Mapping(source = "file", target = "image")
    AdDto toDto(Ad ad);

    @Mapping(source = "image", target = "file")
    Ad toEntity(AdDto adDto);
}
