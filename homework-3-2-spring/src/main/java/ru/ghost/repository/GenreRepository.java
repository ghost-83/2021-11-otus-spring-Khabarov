package ru.ghost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ghost.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
