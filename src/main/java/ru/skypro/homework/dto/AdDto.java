package ru.skypro.homework.dto;

import lombok.Data;

import java.io.File;

/**
 * The type Ad dto.
 */
@Data
public class AdDto {

    /**
     * The Author id.
     */
    Integer authorId;
    /**
     * The Image.
     */
    File image;
    /**
     * The Pk.
     */
    Integer pk;
    /**
     * The Price.
     */
    Integer price;
    /**
     * The Title.
     */
    String title;
}
