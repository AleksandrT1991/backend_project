package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.entity.User;

@Mapper
public interface RegisterReqMapper {

    RegisterReqMapper INSTANCE = Mappers.getMapper(RegisterReqMapper.class);

    RegisterReq toDto(User user);
}