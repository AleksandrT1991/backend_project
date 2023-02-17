package ru.skypro.homework.mappers.ad;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.ad.FullAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdImage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The type Full ad mapper.
 */
@Mapper
public abstract class FullAdMapper {

    /**
     * The constant INSTANCE.
     */
    public static FullAdMapper INSTANCE = Mappers.getMapper( FullAdMapper.class );

    /**
     * To dto full ad dto.
     *
     * @param ad the ad
     * @return the full ad dto
     */
    public FullAdDto toDto(Ad ad) {
        if ( ad == null ) {
            return null;
        }

        FullAdDto fullAdDto = new FullAdDto();
        fullAdDto.setAuthorFirstName(ad.getUser().getFirstName());
        fullAdDto.setAuthorLastName(ad.getUser().getLastName());
        fullAdDto.setDescription( ad.getDescription() );
        fullAdDto.setEmail( ad.getEmail() );
        fullAdDto.setImage( ad.getImage().stream().map(x->"/image/"+x.getId()).collect(Collectors.toList()) );
        fullAdDto.setPhone( ad.getPhone() );
        fullAdDto.setPk( ad.getPk() );
        if ( ad.getPrice() != null ) {
            fullAdDto.setPrice( ad.getPrice().longValue() );
        }
        fullAdDto.setTitle( ad.getTitle() );

        return fullAdDto;
    }

    /**
     * To entity ad.
     *
     * @param fullAdDto the full ad dto
     * @return the ad
     */
    public Ad toEntity(FullAdDto fullAdDto) {
        if ( fullAdDto == null ) {
            return null;
        }

        Ad ad = new Ad();

        ad.setPk( fullAdDto.getPk() );
        if ( fullAdDto.getPrice() != null ) {
            ad.setPrice( fullAdDto.getPrice() );
        }
        ad.setTitle( fullAdDto.getTitle() );
        List<String> paths = fullAdDto.getImage();

        List<AdImage> images = paths.stream()
                .map(path -> {
                    Long id = null;
                    Pattern pattern = Pattern.compile("[\\w\\W]+\\/(\\d+)");
                    Matcher matcher = pattern.matcher(path);
                    if (matcher.find()) {
                        id = Long.valueOf(matcher.group(1));
                    }
                    AdImage image = new AdImage();
                    if (id!=null) {
                        image.setId(id);
                    }
                    return image;
                })
                .collect(Collectors.toList());
        ad.setImage(images);

        return ad;
    }
}
