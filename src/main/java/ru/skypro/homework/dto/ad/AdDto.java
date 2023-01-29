package ru.skypro.homework.dto.ad;

import lombok.Data;

import java.io.File;

@Data
public class AdDto {

    private Long id;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}
