package ru.skypro.homework.mappers.ad;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.entity.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper
public abstract class AdsMapper {

    public static AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    public AdDto toDto(Ad ad) {
        if ( ad == null ) {
            return null;
        }

        AdDto adDto = new AdDto();
        adDto.setAuthor(ad.getUser().getId());
        adDto.setImage( ad.getImage().stream().map(AdImage::getFilePath).collect(Collectors.joining(",")) );
        adDto.setPk( ad.getPk() );
        if ( ad.getPrice() != null ) {
            adDto.setPrice( ad.getPrice() );
        }
        adDto.setTitle( ad.getTitle() );

        return adDto;
    }

    public Ad toEntity(AdDto adDto) {
        if ( adDto == null ) {
            return null;
        }

        Ad ad = new Ad();

        ad.setPk( adDto.getPk() );
        if ( adDto.getPrice() != null ) {
            ad.setPrice( adDto.getPrice().intValue() );
        }
        User user = new User();
        user.setId(adDto.getAuthor());
        ad.setUser( user

        );
        ad.setTitle( adDto.getTitle() );
        List<String> paths = Stream.of(adDto.getImage().split(",", -1))
                .collect(Collectors.toList());

        List<AdImage> images = paths.stream()
                .map(path -> {
                    AdImage image = new AdImage();
                    image.setFilePath(path);
                    return image;
                })
                .collect(Collectors.toList());
        ad.setImage(images);

        return ad;
    }
}
