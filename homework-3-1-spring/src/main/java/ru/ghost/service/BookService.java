package ru.ghost.service;

import ru.ghost.model.Book;

import java.util.List;

public interface BookService {

    Long count();

    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    void delete(Long id);
}