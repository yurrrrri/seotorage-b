package xyz.seotorage.domain.dto.aladin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AladinBookItem {

    private String title;
    private String link;
    private String author;
    private String pubDate; // 0000-00-00 format
    private String description;
    private String isbn;
    private String isbn13;
    private String cover; // Image URL
    private String publisher;
    private String categoryName;

}
