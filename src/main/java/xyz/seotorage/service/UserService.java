package xyz.seotorage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.seotorage.domain.User;
import xyz.seotorage.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findUser(String userId) {
        //
        return this.getUser(userId);
    }

    public void switchTheme(String userId) {
        //
        User user = this.getUser(userId);
        userRepository.save(user.changeTheme());
    }

    public void switchMode(String userId) {
        //
        User user = this.getUser(userId);
        userRepository.save(user.changeMode());
    }

    public void remove(String userId) {
        //
        User user = this.getUser(userId);
        userRepository.save(user.removeUser());
    }

    private User getUser(String userId) {
        //
        Optional<User> optUser = userRepository.findByIdAndRemovedFalse(userId);
        return optUser.orElseThrow(() -> new NoSuchElementException("User is not found."));
    }

}
