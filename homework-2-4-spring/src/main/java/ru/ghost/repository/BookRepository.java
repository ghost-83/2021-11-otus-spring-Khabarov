package ru.ghost.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.ghost.model.Author;
import ru.ghost.model.Book;
import ru.ghost.model.Genre;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByAuthor(Author author);

    List<Book> findAllByGenre(Genre genre);

}
