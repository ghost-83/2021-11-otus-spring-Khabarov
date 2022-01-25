package ru.ghost.dao;

import ru.ghost.model.Author;

import java.util.List;

public interface AuthorDao {

    Long count();

    List<Author> findAll();

    Author findById(Long id);

    Author save(Author author);

    int delete(Long id);
}