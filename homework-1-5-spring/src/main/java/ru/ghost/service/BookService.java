package ru.ghost.service;

import ru.ghost.domain.Book;

import java.util.List;

public interface BookService {

    int count();

    List<Book> findAll();

    Book findById(Long id);

    Long create(String name, Long authorId, Long genreId);

    int update(Long id, String name, Long authorId, Long genreId);

    int delete(Long id);
}