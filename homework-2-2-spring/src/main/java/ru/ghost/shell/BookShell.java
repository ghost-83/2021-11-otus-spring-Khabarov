package ru.ghost.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.ghost.model.Book;
import ru.ghost.service.BookService;

import java.util.List;
import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    private final BookService service;

    @ShellMethod(value = "Get Books count", key = {"bcnt", "book-count"})
    public Long count() {
        return service.count();
    }

    @ShellMethod(value = "Get all Books", key = {"ball", "book-all"})
    public List<Book> findAll() {
        return service.findAll();
    }

    @ShellMethod(value = "Get Book by id", key = {"bid", "book-id"})
    public Book findById(@ShellOption Long id) {
        return service.findById(id);
    }

    @ShellMethod(value = "Insert Book", key = {"bins", "book-insert"})
    public String create(@ShellOption String name, @ShellOption Long authorId, @ShellOption Long genreId) {
        Book book = service.create(name, authorId, genreId);
        return book.getId() != null ? "Record successfully added" : "Failed to add entry";
    }

    @ShellMethod(value = "Update Book", key = {"bupd", "book-update"})
    public String update(@ShellOption Long id, @ShellOption String name,
                         @ShellOption Long authorId, @ShellOption Long genreId) {
        Book book = service.update(id, name, authorId, genreId);
        return Objects.equals(book.getId(), id) ? "Record changed successfully" : "Failed to change record";
    }

    @ShellMethod(value = "Delete Book by id", key = {"bdel", "book-delete"})
    public String delete(@ShellOption Long id) {
        int result = service.delete(id);
        return result == 1 ? "The record was successfully deleted" : "Failed to delete record";
    }
}