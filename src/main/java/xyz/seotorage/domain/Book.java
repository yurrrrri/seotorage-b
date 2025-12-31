package xyz.seotorage.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @GeneratedValue(strategy = UUID)
    @Id
    private String id; // ISBN

    private String title;
    private String author;
    private String publisher;
    private String pubDate; // YYYY-MM-DD format
    private String description;
    private String coverImage;
    private String category;

}
