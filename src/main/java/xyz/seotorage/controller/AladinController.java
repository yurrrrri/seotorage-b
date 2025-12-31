package xyz.seotorage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.seotorage.dto.aladin.AladinSearchResponse;
import xyz.seotorage.service.AladinApiService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/aladin")
public class AladinController {

    private final AladinApiService aladinApiService;

    @GetMapping("/search")
    public AladinSearchResponse searchBooks(@RequestParam String query) {
        return aladinApiService.searchBooks(query);
    }

}
