package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.ghost.model.Author;
import ru.ghost.service.AuthorService;

import java.util.List;
import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

    private final AuthorService service;

    @ShellMethod(value = "Get Authors count", key = {"acnt", "author-count"})
    public Long count() {
        return service.count();
    }

    @ShellMethod(value = "Get all Authors", key = {"aall", "author-all"})
    public List<Author> findAll() {
        return service.findAll();
    }

    @ShellMethod(value = "Get Author by id", key = {"aid", "author-id"})
    public Author findById(@ShellOption String id) {
        return service.findById(id);
    }

    @ShellMethod(value = "Insert Author", key = {"ains", "author-insert"})
    public String create(@ShellOption String firstName, @ShellOption String lastName) {
        Author author = service.create(firstName, lastName);
        return author.getId() != null ? "Record successfully added" : "Failed to add entry";
    }

    @ShellMethod(value = "Update Author", key = {"aupd", "author-update"})
    public String update(@ShellOption String id, @ShellOption String firstName, @ShellOption String lastName) {
        Author author = service.update(id, firstName, lastName);
        return Objects.equals(author.getId(), id) ? "Record changed successfully" : "Failed to change record";
    }

    @ShellMethod(value = "Delete Author by id", key = {"adel", "author-delete"})
    public String delete(@ShellOption String id) {
        service.delete(id);
        return "The record was successfully deleted";
    }
}