package ru.ghost.service;

import ru.ghost.model.Genre;

import java.util.List;

public interface GenreService {

    Long count();

    List<Genre> findAll();

    Genre findById(Long id);

    Genre create(String name);

    Genre update(Long id, String name);

    int delete(Long id);
}