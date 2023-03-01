package ru.skypro.homework.mappers.ad;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.entity.AdComment;

/**
 * The interface Ad comment mapper.
 */
@Mapper
public interface AdCommentMapper {

    /**
     * The constant INSTANCE.
     */
    AdCommentMapper INSTANCE = Mappers.getMapper(AdCommentMapper.class);

    /**
     * To dto ad comment dto.
     *
     * @param adComment the ad comment
     * @return the ad comment dto
     */
    @Mapping(target = "author", source = "user.id")
    AdCommentDto toDto(AdComment adComment);

    /**
     * To entity ad comment.
     *
     * @param adCommentDto the ad comment dto
     * @return the ad comment
     */
    @Mapping(target = "user.id", source = "author")
    AdComment toEntity(AdCommentDto adCommentDto);
}
