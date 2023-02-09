package ru.skypro.homework.dto.ad;

import lombok.Data;
import ru.skypro.homework.entity.AdImage;

import java.util.List;

@Data
public class FullAdDto {

    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private AdImage image;
    private String phone;
    private Long pk;
    private Long price;
    private String title;
}
