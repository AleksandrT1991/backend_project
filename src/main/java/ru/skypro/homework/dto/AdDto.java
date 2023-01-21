package ru.skypro.homework.dto;

import lombok.Data;

import java.io.File;

@Data
public class AdDto {

    private Integer id;
    private File image;
    private Integer pk;
    private Integer price;
    private String title;
}
