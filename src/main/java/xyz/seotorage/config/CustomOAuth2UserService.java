package xyz.seotorage.config;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import xyz.seotorage.domain.User;
import xyz.seotorage.domain.Theme;
import xyz.seotorage.domain.Mode;
import xyz.seotorage.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        final String email;
        final String name;

        if ("google".equals(registrationId)) {
            email = oauth2User.getAttribute("email");
            name = oauth2User.getAttribute("name");
        } else if ("naver".equals(registrationId)) {
            Map<String, Object> response = oauth2User.getAttribute("response");
            if (response != null) {
                email = (String) response.get("email");
                name = (String) response.get("name");
            } else {
                email = null;
                name = null;
            }
        } else if ("kakao".equals(registrationId)) {
            email = oauth2User.getAttribute("account_email");
            name = oauth2User.getAttribute("profile_nickname");
        } else if ("apple".equals(registrationId)) {
            email = oauth2User.getAttribute("email");
            name = oauth2User.getAttribute("name"); // Apple's name attribute might be complex, requiring more parsing
        } else {
            email = null;
            name = null;
        }

        // Check if user exists, if not, create new user
        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setCreateDate(LocalDateTime.now());
            newUser.setTheme(Theme.LIGHT); // Default theme
            newUser.setMode(Mode.LIST); // Default mode
            newUser.setRemoved(false);
            return userRepository.save(newUser);
        });

        // For now, returning the default OAuth2User.
        // In a real application, you might want to wrap this in a custom UserDetails
        // to hold more application-specific user data.
        return oauth2User;
    }
}
