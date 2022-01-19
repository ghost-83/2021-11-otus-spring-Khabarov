package ru.ghost.service;

import ru.ghost.domain.Genre;

import java.util.List;

public interface GenreService {
    int count();

    List<Genre> findAll();

    Genre findById(Long id);

    Long create(String name);

    int update(Long id, String name);

    int delete(Long id);
}