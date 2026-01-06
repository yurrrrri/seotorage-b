package xyz.seotorage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.seotorage.domain.Content;
import xyz.seotorage.domain.UserBook;
import xyz.seotorage.domain.sdo.ContentCdo;
import xyz.seotorage.domain.sdo.ContentUdo;
import xyz.seotorage.repository.ContentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final UserBookService userBookService;

    public Content findById(String contentId) {
        //
        return this.getContent(contentId);
    }

    public List<Content> findByUserBookId(String userBookId) {
        // TODO: unlimited scroll
        return contentRepository.findByUserBookIdOrderByPageNumberDescCreateDateDesc(userBookId);
    }

    public String write(ContentCdo cdo) {
        //
        UserBook userBook = userBookService.findById(cdo.getUserBookId());

        Content content = Content.builder()
                .id(UUID.randomUUID().toString())
                .userBook(userBook)
                .image(cdo.getImage())
                .pageNumber(cdo.getPageNumber())
                .sentence(cdo.getSentence())
                .memo(cdo.getMemo())
                .removed(false)
                .build();
        return contentRepository.save(content).getId();
    }

    public void update(ContentUdo udo) {
        //
        Content content = this.getContent(udo.getId());
        contentRepository.save(content.updateContent(
                udo.getImage(),
                udo.getPageNumber(),
                udo.getSentence(),
                udo.getMemo()
        ));
    }

    public void remove(String contentId) {
        //
        Content content = this.getContent(contentId);
        contentRepository.save(content.remove());
    }

    private Content getContent(String contentId) {
        //
        Optional<Content> optContent = contentRepository.findByIdAndRemovedFalse(contentId);
        return optContent.orElseThrow(() -> new NoSuchElementException("Content is not found."));
    }

}
