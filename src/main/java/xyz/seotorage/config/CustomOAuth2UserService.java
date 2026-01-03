package xyz.seotorage.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import xyz.seotorage.domain.User;
import xyz.seotorage.domain.vo.Mode;
import xyz.seotorage.domain.vo.Theme;
import xyz.seotorage.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //
        OAuth2User oauth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        final String email = this.getEmail(registrationId, oauth2User);
        final String name = this.getName(registrationId, oauth2User);

        upsertUser(name, email); // insert user when null

        return oauth2User;
    }

    private void upsertUser(String name, String email) {
        //
        userRepository.findByEmailAndRemovedFalse(email).orElseGet(() -> {
            User newUser = User.builder()
                    .name(name)
                    .email(email)
                    .createDate(System.currentTimeMillis())
                    .theme(Theme.LIGHT)
                    .mode(Mode.ALBUM)
                    .removed(false)
                    .build();
            return userRepository.save(newUser);
        });
    }

    private String getEmail(String registrationId, OAuth2User oauth2User) {
        //
        if ("kakao".equals(registrationId)) {
            return oauth2User.getAttribute("account_email");
        }
        return oauth2User.getAttribute("email");
    }

    private String getName(String registrationId, OAuth2User oauth2User) {
        //
        if ("kakao".equals(registrationId)) {
            return oauth2User.getAttribute("profile_nickname");
        }
        return oauth2User.getAttribute("name");
    }
}
