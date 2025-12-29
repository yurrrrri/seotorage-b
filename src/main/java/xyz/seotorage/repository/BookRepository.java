package xyz.seotorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.seotorage.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
