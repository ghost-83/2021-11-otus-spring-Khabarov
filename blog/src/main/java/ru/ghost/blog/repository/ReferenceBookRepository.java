package ru.ghost.blog.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ghost.blog.model.Genre;
import ru.ghost.blog.model.Post;
import ru.ghost.blog.model.ReferenceBook;

import java.util.List;
import java.util.Optional;

public interface ReferenceBookRepository extends JpaRepository<ReferenceBook, Long> {

    @NonNull
    @Override
    @EntityGraph(value = "ReferenceBook.Author.Genre")
    List<ReferenceBook> findAll();

    @NonNull
    @Override
    @EntityGraph(value = "ReferenceBook.Author.Genre")
    Optional<ReferenceBook> findById(@NonNull Long id);

    @EntityGraph(value = "ReferenceBook.Author.Genre")
    List<ReferenceBook> findAllByTitleContainingIgnoreCase(String search);

    @EntityGraph(value = "ReferenceBook.Author.Genre")
    List<ReferenceBook> findAllByGenre(Genre genre);

    @EntityGraph(value = "ReferenceBook.Author.Genre")
    List<ReferenceBook> findByGenreId(Long genreId);
}
