package ru.skypro.homework.dto.ad;

import lombok.Data;

@Data
public class AdDto {

    private Long author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}
