package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.ghost.model.Genre;
import ru.ghost.service.GenreService;

import java.util.List;
import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

    private final GenreService service;

    @ShellMethod(value = "Get Genres count", key = {"gcnt", "genre-count"})
    public Long count() {
        return service.count();
    }

    @ShellMethod(value = "Get all Genres", key = {"gall", "genre-all"})
    public List<Genre> findAll() {
        return service.findAll();
    }

    @ShellMethod(value = "Get Genre by id", key = {"gid", "genre-id"})
    public Genre findById(@ShellOption Long id) {
        return service.findById(id);
    }

    @ShellMethod(value = "Insert Genre", key = {"gins", "genre-insert"})
    public String create(@ShellOption String name) {
        Genre genre = service.create(name);
        return genre.getId() != null ? "Record successfully added" : "Failed to add entry";
    }

    @ShellMethod(value = "Update Genre", key = {"gupd", "genre-update"})
    public String update(@ShellOption Long id, @ShellOption String name) {
        Genre genre = service.update(id, name);
        return Objects.equals(genre.getId(), id) ? "Record changed successfully" : "Failed to change record";
    }

    @ShellMethod(value = "Delete Genre by id", key = {"gdel", "genre-delete"})
    public String delete(@ShellOption Long id) {
        service.delete(id);
        return "The record was successfully deleted";
    }
}