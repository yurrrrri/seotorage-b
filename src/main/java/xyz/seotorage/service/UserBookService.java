package xyz.seotorage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.seotorage.domain.UserBook;
import xyz.seotorage.repository.UserBookRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserBookService {

    private final UserBookRepository userBookRepository;

    public UserBook findById(String id) {
        Optional<UserBook> optUserBook = userBookRepository.findById(id);
        return optUserBook.orElseThrow();
    }
}
