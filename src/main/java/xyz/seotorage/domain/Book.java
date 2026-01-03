package xyz.seotorage.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import xyz.seotorage.domain.dto.aladin.AladinBookItem;
import xyz.seotorage.domain.dto.AladinSearchResponse;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Book {

  @Id
  private String id; // ISBN

  private String title;
  private String author;
  private String publisher;
  private long pubDate;
  private String description;
  private String coverImage;
  private String category;

  public Book(AladinBookItem book) {
    //
    this.id = book.getIsbn() != null ? book.getIsbn() : book.getIsbn13();
    this.title = book.getTitle();
    this.author = book.getAuthor();
    this.publisher = book.getPublisher();

    this.pubDate = LocalDate.parse(book.getPubDate())
      .atStartOfDay(ZoneId.systemDefault())
      .toInstant()
      .toEpochMilli();

    this.description = book.getDescription();
    this.coverImage = book.getCover();
    this.category = book.getCategoryName();
  }

  public static List<Book> getBooks(AladinSearchResponse resp) {
    //
    List<Book> books = new ArrayList<>();
    resp.getItem().forEach(item -> {
      books.add(new Book(item));
    });
    return books;
  }

}
