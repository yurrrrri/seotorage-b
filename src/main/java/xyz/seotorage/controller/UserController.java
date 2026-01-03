package xyz.seotorage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.seotorage.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            return "Welcome, " + principal.getAttribute("name") + "! You are logged in.";
        }
        return "Welcome to Seotorage! Please log in.";
    }

    @GetMapping("/loginSuccess")
    public ResponseEntity<?> loginSuccess(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(403).build();
    }

    @GetMapping("/restricted")
    public ResponseEntity<?> restricted() {
        return ResponseEntity.status(401).build();
    }

    @GetMapping("/user/me")
    public OAuth2User user(@AuthenticationPrincipal OAuth2User principal) {
        return principal;
    }

    @GetMapping("/switch-mode")
    public ResponseEntity<?> switchMode() {
        // FIXME: admin
        userService.switchMode("admin");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/switch-theme")
    public ResponseEntity<?> switchTheme() {
        // FIXME: admin
        userService.switchTheme("admin");
        return ResponseEntity.ok().build();
    }

}
