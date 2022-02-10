package ru.ghost.service;

import ru.ghost.model.Genre;

import java.util.List;

public interface GenreService {

    Long count();

    List<Genre> findAll();

    Genre findById(String id);

    Genre create(String name);

    Genre update(String id, String name);

    void delete(String id);
}