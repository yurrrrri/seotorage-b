package xyz.seotorage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.seotorage.domain.UserBook;
import xyz.seotorage.service.UserBookService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-book")
public class UserBookController {

    private final UserBookService userBookService;

    @GetMapping("/{id}")
    public UserBook getUserBook(@RequestParam String id) {
        return userBookService.getUserBook(id);
    }

    @GetMapping("/")
    public List<UserBook> getUserBooks(@RequestBody String userId) {
        // FIXME: admin
        return userBookService.findByUserId("admin");
    }

}
