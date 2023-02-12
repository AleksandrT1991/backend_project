package ru.skypro.homework.dto.ad;

import lombok.Data;
import ru.skypro.homework.entity.AdImage;
import ru.skypro.homework.entity.User;

import java.util.List;

@Data
public class AdDto {

    private Long author;
    private String image;
    private Long pk;
    private Integer price;
    private String title;
}
