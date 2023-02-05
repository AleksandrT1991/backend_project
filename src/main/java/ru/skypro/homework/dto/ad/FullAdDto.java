package ru.skypro.homework.dto.ad;

import lombok.Data;

import java.util.List;

@Data
public class FullAdDto {

    String authorFirstName;
    String authorLastName;
    String description;
    String email;
    List<String> image;
    String phone;
    Long pk;
    Long price;
    String title;
}
