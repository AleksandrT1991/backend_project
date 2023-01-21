package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.UserImageDto;
import ru.skypro.homework.entity.UserImage;

@Mapper
public interface UserImageMapper {

    UserImageMapper INSTANCE = Mappers.getMapper(UserImageMapper.class);

    UserImageDto toDto(UserImage userImage);
}