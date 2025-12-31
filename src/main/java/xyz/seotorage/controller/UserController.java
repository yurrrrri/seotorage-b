package xyz.seotorage.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            return "Welcome, " + principal.getAttribute("name") + "! You are logged in.";
        }
        return "Welcome to Seotorage! Please log in.";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            return "Login successful! Welcome, " + principal.getAttribute("name");
        }
        return "Login successful, but no principal found.";
    }

    @GetMapping("/restricted")
    public String restricted() {
        return "Login failed!";
    }

    @GetMapping("/user/me")
    public OAuth2User user(@AuthenticationPrincipal OAuth2User principal) {
        return principal;
    }

}
