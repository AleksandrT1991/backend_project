package ru.skypro.homework.dto.ad;

import lombok.Data;

@Data
public class CreateAdDto {

    private String description;
    private Integer price;
    private String title;

}
