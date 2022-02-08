package ru.ghost.service;

import ru.ghost.model.Author;
import ru.ghost.model.Book;
import ru.ghost.model.Genre;

import java.util.List;

public interface BookService {

    Long count();

    List<Book> findAll();

    Book findById(String id);

    List<Book> findAllByAuthor(Author author);

    List<Book> findAllByGenre(Genre genre);

    Book create(String name, String authorId, String genreId);

    Book update(String id, String name, String authorId, String genreId);

    void updateAll(List<Book> books);

    void delete(String id);
}