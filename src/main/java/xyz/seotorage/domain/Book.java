package xyz.seotorage.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String id; // This could be ISBN or another unique identifier from Aladin API

    private String title;
    private String author;
    private String publisher;
    private String pubDate; // YYYY-MM-DD format
    private String description;
    private String coverImage;
    private String category;
}
