package ru.ghost.service;

import ru.ghost.domain.Author;

import java.util.List;

public interface AuthorService {

    int count();

    List<Author> findAll();

    Author findById(Long id);

    Long create(String firstName, String lastName);

    int update(Long id, String firstName, String lastName);

    int delete(Long id);
}