package ru.skypro.homework.dto.ad;

import lombok.Data;

@Data
public class AdImageDto {

    private Integer id;
    private Integer adId;
    private String filePath;
    private Long fileSize;
    private String mediaType;
}
