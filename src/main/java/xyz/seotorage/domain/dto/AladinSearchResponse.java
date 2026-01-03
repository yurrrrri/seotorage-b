package xyz.seotorage.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.seotorage.domain.dto.aladin.AladinBookItem;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AladinSearchResponse {

    private Integer totalResults;
    private Integer startIndex;
    private Integer itemsPerPage; // limit
    private List<AladinBookItem> item;

}
