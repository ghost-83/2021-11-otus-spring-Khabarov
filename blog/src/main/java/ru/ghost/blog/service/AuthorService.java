package ru.ghost.blog.service;

import ru.ghost.blog.model.Author;

import java.util.List;

public interface AuthorService {

    Long count();

    List<Author> findAll();

    Author findById(Long id);

    Author save(Author author);

    void delete(Long id);
}