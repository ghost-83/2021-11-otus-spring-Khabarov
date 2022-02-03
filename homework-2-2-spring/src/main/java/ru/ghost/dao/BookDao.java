package ru.ghost.dao;

import ru.ghost.model.Book;

import java.util.List;

public interface BookDao {

    Long count();

    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    int delete(Long id);
}