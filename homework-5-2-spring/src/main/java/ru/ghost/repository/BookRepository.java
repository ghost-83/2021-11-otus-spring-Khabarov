package ru.ghost.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ghost.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "Book.Author.Genre")
    @Override
    List<Book> findAll();

    @EntityGraph(value = "Book.Author.Genre")
    @Override
    Optional<Book> findById(Long id);

    @EntityGraph(value = "Book.Author.Genre")
    List<Book> findByAuthorId(Long authorId);

    @EntityGraph(value = "Book.Author.Genre")
    List<Book> findByGenreId(Long genreId);
}
