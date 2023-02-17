package ru.skypro.homework.dto.ad;

import lombok.Data;

import java.util.List;

/**
 * The type Ad dto.
 */
@Data
public class AdDto {

    private Long author;
    private List<String> image;
    private Long pk;
    private Long price;
    private String title;
}
