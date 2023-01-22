package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.AdImageDto;
import ru.skypro.homework.entity.Ad;

@Mapper
public interface AdImageMapper {

    AdImageMapper INSTANCE = Mappers.getMapper(AdImageMapper.class);

    AdImageDto toDto(Ad ad);
}
