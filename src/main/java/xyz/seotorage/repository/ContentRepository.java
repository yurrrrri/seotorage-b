package xyz.seotorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.seotorage.domain.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, String> {
}
