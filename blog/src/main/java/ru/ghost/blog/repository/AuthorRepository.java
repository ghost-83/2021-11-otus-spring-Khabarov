package ru.ghost.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ghost.blog.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
