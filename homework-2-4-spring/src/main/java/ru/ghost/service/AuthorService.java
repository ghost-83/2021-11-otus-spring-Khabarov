package ru.ghost.service;

import ru.ghost.model.Author;

import java.util.List;

public interface AuthorService {

    Long count();

    List<Author> findAll();

    Author findById(String id);

    Author create(String firstName, String lastName);

    Author update(String id, String firstName, String lastName);

    void delete(String id);
}