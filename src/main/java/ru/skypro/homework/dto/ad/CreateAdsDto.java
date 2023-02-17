package ru.skypro.homework.dto.ad;

import lombok.Data;

/**
 * The type Create ads dto.
 */
@Data
public class CreateAdsDto {

    private String description;
    private Long price;
    private String title;
}
