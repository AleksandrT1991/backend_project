package ru.skypro.homework.dto.wrappers;

import lombok.Data;
import ru.skypro.homework.dto.ad.AdCommentDto;
import java.util.List;

@Data
public class ResponseWrapperComments {

    private Integer count;
    private List<AdCommentDto> results;
}
