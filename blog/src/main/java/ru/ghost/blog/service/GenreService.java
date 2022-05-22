package ru.ghost.blog.service;

import ru.ghost.blog.model.Genre;

import java.util.List;

public interface GenreService {

    Long count();

    List<Genre> findAll();

    Genre findById(Long id);

    Genre findByName(String name);

    Genre save(Genre genre);

    void delete(Long id);
}