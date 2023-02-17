package ru.skypro.homework.mappers.user;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;

/**
 * The type User mapper.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class UserMapper {
    /**
     * The constant INSTANCE.
     */
    public static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    /**
     * To dto user dto.
     *
     * @param user the user
     * @return the user dto
     */
    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        } else {
            UserDto userDto = new UserDto();
            Long id = userImageId(user);
            if (id != null) {
                userDto.setImage(String.valueOf(id));
            }

            userDto.setEmail(user.getEmail());
            userDto.setFirstName(user.getFirstName());
            userDto.setId(user.getId());
            userDto.setLastName(user.getLastName());
            userDto.setPhone(user.getPhone());
            userDto.setRegDate(user.getRegDate());
            userDto.setCity(user.getCity());
            userDto.setImage("/image/"+user.getImage());
            return userDto;
        }
    }

    /**
     * To entity user.
     *
     * @param userDto the user dto
     * @return the user
     */
    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        } else {
            User user = new User();
            user.setImage(this.userDtoToUserImage(userDto));
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setId(userDto.getId());
            user.setLastName(userDto.getLastName());
            user.setPhone(userDto.getPhone());
            user.setRegDate(userDto.getRegDate());
            user.setCity(userDto.getCity());
            return user;
        }
    }

    private static Long userImageId(User user) {
        if (user == null) {
            return null;
        } else {
            UserImage image = user.getImage();
            if (image == null) {
                return null;
            } else {
                Long id = image.getId();
                return id == null ? null : id;
            }
        }
    }

    /**
     * User dto to user image user image.
     *
     * @param userDto the user dto
     * @return the user image
     */
    protected UserImage userDtoToUserImage(UserDto userDto) {
        if (userDto == null) {
            return null;
        } else {
            UserImage userImage = new UserImage();
            if (userDto.getImage() != null) {
                userImage.setId(Long.parseLong(userDto.getImage()));
            }

            return userImage;
        }
    }
}
