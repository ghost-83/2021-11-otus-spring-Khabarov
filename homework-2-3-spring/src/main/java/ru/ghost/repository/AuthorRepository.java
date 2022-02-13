package ru.ghost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ghost.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
