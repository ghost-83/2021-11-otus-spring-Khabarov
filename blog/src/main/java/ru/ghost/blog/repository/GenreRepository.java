package ru.ghost.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ghost.blog.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);
}
