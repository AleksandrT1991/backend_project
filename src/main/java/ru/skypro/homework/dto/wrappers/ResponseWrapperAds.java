package ru.skypro.homework.dto.wrappers;

import lombok.Data;
import ru.skypro.homework.dto.ad.AdDto;
import ru.skypro.homework.entity.Ad;

import java.util.List;

@Data
public class ResponseWrapperAds {

    private Integer count;
    private List<AdDto> results;
}
