package xyz.seotorage.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AladinSearchRequest {

    private Integer offset;
    private Integer limit;
    private String searchWord;

}
