package ru.ghost.dao;

import ru.ghost.domain.Genre;

import java.util.List;

public interface GenreDao {

    int count();

    List<Genre> findAll();

    Genre findById(Long id);

    Long create(Genre genre);

    int update(Genre genre);

    int delete(Long id);
}