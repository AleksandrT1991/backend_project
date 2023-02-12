package ru.skypro.homework.mappers.ad;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.FullAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdImage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper
public abstract class FullAdMapper {

    public static FullAdMapper INSTANCE = Mappers.getMapper( FullAdMapper.class );

    public FullAdDto toDto(Ad ad) {
        if ( ad == null ) {
            return null;
        }

        FullAdDto fullAdDto = new FullAdDto();

        fullAdDto.setDescription( ad.getDescription() );
        fullAdDto.setEmail( ad.getEmail() );
        fullAdDto.setImage( ad.getImage().stream().map(AdImage::getFilePath).collect(Collectors.joining(",")) );
        fullAdDto.setPhone( ad.getPhone() );
        fullAdDto.setPk( ad.getPk() );
        if ( ad.getPrice() != null ) {
            fullAdDto.setPrice( ad.getPrice().longValue() );
        }
        fullAdDto.setTitle( ad.getTitle() );

        return fullAdDto;
    }

    public Ad toEntity(FullAdDto fullAdDto) {
        if ( fullAdDto == null ) {
            return null;
        }

        Ad ad = new Ad();

        ad.setPk( fullAdDto.getPk() );
        if ( fullAdDto.getPrice() != null ) {
            ad.setPrice( fullAdDto.getPrice().intValue() );
        }
        ad.setTitle( fullAdDto.getTitle() );
        ad.setDescription( fullAdDto.getDescription() );
        ad.setPhone( fullAdDto.getPhone() );
        ad.setEmail( fullAdDto.getEmail() );
        List<String> paths = Stream.of(fullAdDto.getImage().split(",", -1))
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
