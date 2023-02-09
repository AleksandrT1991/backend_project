package ru.skypro.homework.mappers.ad;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.entity.Ad;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface AdsMapper {


    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    @Mapping(target = "author", source = "user")
    AdDto toDto(Ad ad);

    @Mapping(target = "user", source = "author")
    Ad toEntity(AdDto adDto);
}
