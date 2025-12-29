package xyz.seotorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.seotorage.domain.UserBook;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, String> {
}
