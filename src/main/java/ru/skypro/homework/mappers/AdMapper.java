package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.entity.Ad;

@Mapper
public interface AdMapper {

    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "file", target = "image")
    AdDto toDto(Ad ad);

    @Mapping(source = "image", target = "file")
    Ad toEntity(AdDto adDto);
}
