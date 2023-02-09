package ru.skypro.homework.dto.wrappers;

import lombok.Data;
import ru.skypro.homework.dto.ad.AdCommentDto;
import ru.skypro.homework.dto.ad.AdDto;

import java.util.List;

@Data
public class ResponseWrapperComment {

    Integer count;
    List<AdCommentDto> results;
}
