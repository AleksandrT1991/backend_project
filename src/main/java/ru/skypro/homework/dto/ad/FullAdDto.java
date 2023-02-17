package ru.skypro.homework.dto.ad;

import lombok.Data;

import java.util.List;

/**
 * The type Full ad dto.
 */
@Data
public class FullAdDto {

    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private List<String> image;
    private String phone;
    private Long pk;
    private Long price;
    private String title;
}
