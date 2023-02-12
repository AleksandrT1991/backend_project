package ru.skypro.homework.mappers.ad;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.entity.AdComment;

@Mapper
public interface AdCommentMapper {

    AdCommentMapper INSTANCE = Mappers.getMapper(AdCommentMapper.class);
    @Mapping(target = "author", source = "user.id")
    AdCommentDto toDto(AdComment adComment);

    @Mapping(target = "user.id", source = "author")
    AdComment toEntity(AdCommentDto adCommentDto);
}
