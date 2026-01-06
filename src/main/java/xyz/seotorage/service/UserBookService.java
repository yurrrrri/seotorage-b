package xyz.seotorage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.seotorage.domain.Content;
import xyz.seotorage.domain.UserBook;
import xyz.seotorage.repository.UserBookRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserBookService {

    private final UserBookRepository userBookRepository;
    private final ContentService contentService;

    public UserBook findById(String userBookId) {
        //
        return this.getUserBook(userBookId);
    }

    public List<UserBook> findByUserId(String userId) {
        // TODO: unlimited scroll
        return userBookRepository.findByUserId(userId);
    }

    public void remove(String userBookId) {
        //
        UserBook userBook = this.getUserBook(userBookId);

        List<Content> contents = contentService.findByUserBookId(userBookId);
        if (!contents.isEmpty()) {
            contents.forEach(item -> contentService.remove(item.getId()));
        }

        userBookRepository.delete(userBook);
    }

    private UserBook getUserBook(String id) {
        //
        Optional<UserBook> optUserBook = userBookRepository.findById(id);
        return optUserBook.orElseThrow();
    }

}
