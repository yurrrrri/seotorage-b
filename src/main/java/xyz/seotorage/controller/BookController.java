package xyz.seotorage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.seotorage.domain.Book;
import xyz.seotorage.domain.dto.BookSearchQdo;
import xyz.seotorage.service.AladinApiService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/book")
public class BookController {

    private final AladinApiService aladinApiService;

    @PostMapping("/search")
    public List<Book> searchBooks(@RequestBody BookSearchQdo qdo) {
        return aladinApiService.searchBooks(qdo);
    }

}
