package xyz.seotorage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.seotorage.domain.Book;
import xyz.seotorage.domain.dto.AladinSearchResponse;
import xyz.seotorage.domain.dto.BookSearchQdo;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AladinApiService {

    @Value("${aladin.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public List<Book> searchBooks(BookSearchQdo qdo) {
        //
        URI uri = getUri(qdo);

        AladinSearchResponse response = restTemplate.getForObject(uri, AladinSearchResponse.class);
        if (response == null) return Collections.emptyList();

        return Book.getBooks(response);
    }

    private URI getUri(BookSearchQdo qdo) {
        //
        return UriComponentsBuilder
          .fromUriString("http://www.aladin.co.kr/ttb/api/")
          .path("ItemSearch.aspx")
          .queryParam("TTBKey", apiKey)
          .queryParam("Query", qdo.getSearchWord())
          .queryParam("QueryType", "Title")
          .queryParam("MaxResults", qdo.getLimit())
          .queryParam("start", qdo.getOffset())
          .queryParam("SearchTarget", "Book")
          .queryParam("output", "xml")
          .queryParam("Version", 20131101)
          .build()
          .toUri();
    }

}
