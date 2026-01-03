package xyz.seotorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.seotorage.domain.Content;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, String> {

    Optional<Content> findByIdAndRemovedFalse(String id);
    List<Content> findByUserBook_IdOrderByPageNumberDescCreateDateDesc(String userBookId);
}
