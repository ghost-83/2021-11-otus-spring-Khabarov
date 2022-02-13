package ru.ghost.service;

import ru.ghost.model.Author;

import java.util.List;

public interface AuthorService {

    Long count();

    List<Author> findAll();

    Author findById(Long id);

    Author save(Author author);

    void delete(Long id);
}