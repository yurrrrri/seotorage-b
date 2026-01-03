package xyz.seotorage.domain.sdo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContentCdo {

    @NotBlank
    private String userBookId;
    private String image;
    private int pageNumber;
    @Max(500)
    private String sentence;
    @Max(200)
    private String memo;

}
