package xyz.seotorage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.seotorage.domain.Book;
import xyz.seotorage.domain.dto.aladin.AladinSearchRequest;
import xyz.seotorage.service.AladinApiService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/aladin")
public class AladinController {

    private final AladinApiService aladinApiService;

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestBody AladinSearchRequest req) {
        return aladinApiService.searchBooks(req);
    }

}
