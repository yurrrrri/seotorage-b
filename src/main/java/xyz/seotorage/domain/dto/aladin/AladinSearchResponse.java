package xyz.seotorage.domain.dto.aladin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AladinSearchResponse {

    private Integer totalResults;
    private Integer startIndex;
    private Integer itemsPerPage;
    private List<AladinBookItem> item;

}
