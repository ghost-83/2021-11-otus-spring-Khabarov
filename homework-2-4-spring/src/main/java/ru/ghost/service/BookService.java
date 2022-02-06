package ru.ghost.service;

import ru.ghost.model.Book;

import java.util.List;

public interface BookService {

    Long count();

    List<Book> findAll();

    Book findById(String id);

    Book create(String name, String authorId, String genreId);

    Book update(String id, String name, String authorId, String genreId);

    void delete(String id);
}