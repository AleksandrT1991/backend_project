package ru.skypro.homework.mappers.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.UserImage;

@Mapper
public abstract class UserMapper {
    public static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        } else {
            UserDto userDto = new UserDto();
            Long id = this.userImageId(user);
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
            userDto.setImage("/image/"+user.getImage().getId());
            return userDto;
        }
    }

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

    private Long userImageId(User user) {
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
