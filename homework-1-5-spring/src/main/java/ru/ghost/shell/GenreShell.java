package ru.ghost.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.ghost.domain.Genre;
import ru.ghost.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

    private final GenreService service;

    @ShellMethod(value = "Get Genres count", key = {"gcnt", "genre-count"})
    public int count() {
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
        Long id = service.create(name);
        return id > 0 ? "Record successfully added" : "Failed to add entry";
    }

    @ShellMethod(value = "Update Genre", key = {"gupd", "genre-update"})
    public String update(@ShellOption Long id, @ShellOption String name) {
        int result = service.update(id, name);
        return result == 1 ? "Record changed successfully" : "Failed to change record";
    }

    @ShellMethod(value = "Delete Genre by id", key = {"gdel", "genre-delete"})
    public String delete(@ShellOption Long id) {
        int result = service.delete(id);
        return result == 1 ? "The record was successfully deleted" : "Failed to delete record";
    }
}