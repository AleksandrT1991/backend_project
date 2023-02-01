package ru.skypro.homework.dto.ad;

import lombok.Data;

@Data
public class FullAdDto {

    String authorFirstName;
    String authorLastName;
    String description;
    String email;
    String image;
    String phone;
    Long pk;
    Long price;
    String title;
}
