package xyz.seotorage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.seotorage.domain.dto.aladin.AladinSearchResponse;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class AladinApiService {

    @Value("${aladin.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public AladinSearchResponse searchBooks(String query) {
        //
        URI uri = UriComponentsBuilder
                .fromUriString("http://www.aladin.co.kr/ttb/api/")
                .path("ItemSearch.aspx")
                .queryParam("TTBKey", apiKey)
                .queryParam("Query", query)
                .queryParam("QueryType", "Title")
                .queryParam("MaxResults", 10)
                .queryParam("start", 1)
                .queryParam("SearchTarget", "Book")
                .queryParam("output", "json")
                .queryParam("Version", 20131101)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, AladinSearchResponse.class);
    }

}
