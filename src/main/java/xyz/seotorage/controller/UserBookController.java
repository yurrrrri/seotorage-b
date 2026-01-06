package xyz.seotorage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
        return userBookService.findById(id);
    }

    @GetMapping("/user")
    public List<UserBook> getUserBooks(@AuthenticationPrincipal OAuth2User principal) {
        String userId = principal.getAttribute("id");
        return userBookService.findByUserId(userId);
    }

    @PostMapping("/")
    public void remove(@RequestBody String id) {
        userBookService.remove(id);
    }

}
