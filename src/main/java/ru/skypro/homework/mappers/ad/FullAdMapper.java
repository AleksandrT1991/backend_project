package ru.skypro.homework.mappers.ad;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.FullAdDto;
import ru.skypro.homework.entity.Ad;

@Mapper
public interface FullAdMapper {

    FullAdMapper INSTANCE = Mappers.getMapper( FullAdMapper.class );
    FullAdDto toDto(Ad ad);

    Ad toEntity(FullAdDto fullAdDto);
}
