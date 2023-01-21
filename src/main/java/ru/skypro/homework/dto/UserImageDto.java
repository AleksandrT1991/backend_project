package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserImageDto {
    private Integer id;
    private Integer userId;
    private String filePath;
    private Long fileSize;
    private String mediaType;
}
