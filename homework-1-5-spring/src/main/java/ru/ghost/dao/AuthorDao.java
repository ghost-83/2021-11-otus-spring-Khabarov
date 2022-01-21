package ru.ghost.dao;

import ru.ghost.domain.Author;

import java.util.List;

public interface AuthorDao {

    int count();

    List<Author> findAll();

    Author findById(Long id);

    Long create(Author author);

    int update(Author author);

    int delete(Long id);
}