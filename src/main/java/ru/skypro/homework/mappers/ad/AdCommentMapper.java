package ru.skypro.homework.mappers.ad;

import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.entity.AdComment;

@Mapper
public interface AdCommentMapper {

    AdCommentMapper INSTANCE = Mappers.getMapper(AdCommentMapper.class);
    AdCommentDto toDto(AdComment adComment);

    AdComment toEntity(AdCommentDto adCommentDto);
}
