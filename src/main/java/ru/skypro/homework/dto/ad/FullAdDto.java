package ru.skypro.homework.dto.ad;

import lombok.Data;
import org.mapstruct.Mapper;

@Data
public class FullAdDto {

    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private Long pk;
    private Long price;
    private String title;
}
