package xyz.seotorage.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.seotorage.domain.Content;
import xyz.seotorage.domain.sdo.ContentCdo;
import xyz.seotorage.domain.sdo.ContentUdo;
import xyz.seotorage.service.ContentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/{id}")
    public Content getContent(@RequestParam String id) {
        return contentService.findById(id);
    }

    @GetMapping("/user-book/{userBookId}")
    public List<Content> getContentsByUserBookId(@RequestParam String userBookId) {
        return contentService.findByUserBookId(userBookId);
    }

    @PostMapping("/write")
    public String write(@RequestBody @Valid ContentCdo cdo) {
        return contentService.write(cdo);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid ContentUdo udo) {
        contentService.update(udo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> remove(@RequestBody String id) {
        contentService.remove(id);
        return ResponseEntity.ok().build();
    }

}
