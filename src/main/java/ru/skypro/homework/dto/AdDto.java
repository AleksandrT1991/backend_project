package ru.skypro.homework.dto;

import lombok.Data;

import java.io.File;

@Data
public class AdDto {

    Integer authorId;
    File image;
    Integer pk;
    Integer price;
    String title;
}
