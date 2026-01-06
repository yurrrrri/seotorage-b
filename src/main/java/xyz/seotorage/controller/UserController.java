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

    @GetMapping("/success")
    public ResponseEntity<?> success(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(403).build();
    }

    @GetMapping("/restricted")
    public ResponseEntity<?> restricted() {
        return ResponseEntity.status(401).build();
    }

    @GetMapping("/me")
    public User getUser(@AuthenticationPrincipal OAuth2User principal) {
        return userService.findUser(this.getUserId(principal));
    }

    @PostMapping("/find")
    public User findByEmail(@RequestBody String email) {
        return userService.findUserByEmail(email);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> leave(@AuthenticationPrincipal OAuth2User principal) {
        userService.remove(this.getUserId(principal));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/switch-mode")
    public ResponseEntity<?> switchMode(@AuthenticationPrincipal OAuth2User principal) {
        userService.switchMode(this.getUserId(principal));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/switch-theme")
    public ResponseEntity<?> switchTheme(@AuthenticationPrincipal OAuth2User principal) {
        userService.switchTheme(this.getUserId(principal));
        return ResponseEntity.ok().build();
    }

    private String getUserId(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttribute("id");
    }

}
