package ru.ghost.service;

import ru.ghost.model.Book;

import java.util.List;

public interface BookService {

    Long count();

    List<Book> findAll();

    Book findById(Long id);

    Book create(String name, Long authorId, Long genreId);

    Book update(Long id, String name, Long authorId, Long genreId);

    void delete(Long id);
}