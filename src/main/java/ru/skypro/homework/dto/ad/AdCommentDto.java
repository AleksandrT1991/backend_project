package ru.skypro.homework.dto.ad;

import lombok.Data;

@Data
public class AdCommentDto {

    private Long author;
    private String createdAt;
    private Long pk;
    private String text;
}
