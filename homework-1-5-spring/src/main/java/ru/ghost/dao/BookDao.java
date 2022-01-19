package ru.ghost.dao;

import ru.ghost.domain.Book;

import java.util.List;

public interface BookDao {

    int count();

    List<Book> findAll();

    Book findById(Long id);

    Long create(Book book);

    int update(Book book);

    int delete(Long id);
}