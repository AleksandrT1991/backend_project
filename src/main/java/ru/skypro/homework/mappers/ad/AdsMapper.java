package ru.skypro.homework.mappers.ad;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.entity.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The type Ads mapper.
 */
@Service
public class AdsMapper {

    /**
     * The constant INSTANCE.
     */
    public static AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    /**
     * To dto ad dto.
     *
     * @param ad the ad
     * @return the ad dto
     */
    public AdDto toDto(Ad ad) {
        if ( ad == null ) {
            return null;
        }

        AdDto adDto = new AdDto();
        if (ad.getUser()!=null) {
            adDto.setAuthor(ad.getUser().getId());
        } else {
            adDto.setAuthor(new User().getId());
        }
        adDto.setImage( ad.getImage().stream().map(x->"/image/"+x.getId()).collect(Collectors.toList()) );
        adDto.setPk( ad.getPk() );
        if ( ad.getPrice() != null ) {
            adDto.setPrice( ad.getPrice() );
        }
        adDto.setTitle( ad.getTitle() );

        return adDto;
    }

    /**
     * To entity ad.
     *
     * @param adDto the ad dto
     * @return the ad
     */
    public Ad toEntity(AdDto adDto) {
        if ( adDto == null ) {
            return null;
        }

        Ad ad = new Ad();

        ad.setPk( adDto.getPk() );
        if ( adDto.getPrice() != null ) {
            ad.setPrice( adDto.getPrice() );
        }
        User user = new User();
        user.setId(adDto.getAuthor());
        ad.setUser( user

        );
        ad.setTitle( adDto.getTitle() );
        List<String> paths = adDto.getImage();

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
