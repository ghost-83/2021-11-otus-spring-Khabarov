package ru.ghost.service;

import ru.ghost.model.Author;

import java.util.List;

public interface AuthorService {

    Long count();

    List<Author> findAll();

    Author findById(Long id);

    Author create(String firstName, String lastName);

    Author update(Long id, String firstName, String lastName);

    int delete(Long id);
}