package ru.skypro.homework.mappers.user;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.user.PasswordDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;

@Mapper
public interface PasswordMapper {
    PasswordMapper INSTANCE = Mappers.getMapper(PasswordMapper.class);

//    PasswordDto toDto(User user);

    User passwordDtoToUser(PasswordDto passwordDto);

    PasswordDto userToPasswordDto(User user);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    User updateUserFromPasswordDto(PasswordDto passwordDto, @MappingTarget User user);
}
