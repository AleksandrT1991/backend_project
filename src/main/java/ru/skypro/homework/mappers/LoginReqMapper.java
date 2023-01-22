package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.user.LoginReq;
import ru.skypro.homework.entity.User;

@Mapper
public interface LoginReqMapper {

    LoginReqMapper INSTANCE = Mappers.getMapper(LoginReqMapper.class);

    LoginReq toDto(User user);
}
