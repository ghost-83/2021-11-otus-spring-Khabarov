package ru.ghost.dao;

import ru.ghost.model.Genre;

import java.util.List;

public interface GenreDao {

    Long count();

    List<Genre> findAll();

    Genre findById(Long id);

    Genre save(Genre genre);

    int delete(Long id);
}