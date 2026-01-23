package xyz.seotorage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import xyz.seotorage.domain.User;
import xyz.seotorage.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public User home(@AuthenticationPrincipal OAuth2User principal) {
        String userId = this.getUserId(principal);
        return userService.findUser(userId);
    }

    @GetMapping("/me")
    public User getUser(@AuthenticationPrincipal OAuth2User principal) {
        return userService.findUser(this.getUserId(principal));
    }

    @PostMapping("/find")
    public User findByEmail(@RequestBody String email) {
        return userService.findUserByEmail(email);
    }

    @PostMapping("/leave")
    public ResponseEntity<?> leave(@AuthenticationPrincipal OAuth2User principal) {
        userService.remove(this.getUserId(principal));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/switch-mode")
    public ResponseEntity<?> switchMode(@AuthenticationPrincipal OAuth2User principal) {
        userService.switchMode(this.getUserId(principal));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/switch-theme")
    public ResponseEntity<?> switchTheme(@AuthenticationPrincipal OAuth2User principal) {
        userService.switchTheme(this.getUserId(principal));
        return ResponseEntity.ok().build();
    }

    private String getUserId(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttribute("id");
    }

}
