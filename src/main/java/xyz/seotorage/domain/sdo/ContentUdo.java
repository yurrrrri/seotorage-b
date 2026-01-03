package xyz.seotorage.domain.sdo;

import jakarta.validation.constraints.Max;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ContentUdo {

    private String image;
    private int pageNumber;
    @Max(500)
    private String sentence;
    @Max(200)
    private String memo;

    @Builder
    public ContentUdo(String image, int pageNumber, String sentence, String memo) {
        //
        if ((image == null || image.isBlank()) &&
                (sentence == null || sentence.isBlank()) &&
                (memo == null || memo.isBlank())) {
            throw new IllegalArgumentException("적어도 하나의 컨텐츠(이미지, 문장, 메모)는 입력해야 합니다.");
        }
        this.image = image;
        this.pageNumber = pageNumber;
        this.sentence = sentence;
        this.memo = memo;
    }

}
