package ru.ghost.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.ghost.model.Comment;
import ru.ghost.service.CommentService;

import java.util.List;
import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class CommentShell {

    private final CommentService service;

    @ShellMethod(value = "Get Comments count", key = {"ccnt", "comment-count"})
    public Long count() {
        return service.count();
    }

    @ShellMethod(value = "Find all Comments", key = {"call", "comment-all"})
    public List<Comment> findAll() {
        return service.findAll();
    }

    @ShellMethod(value = "Find Comment by id", key = {"cid", "comment-id"})
    public Comment findById(@ShellOption Long id) {
        return service.findById(id);
    }

    @ShellMethod(value = "Add Comment", key = {"cadd", "comment-add"})
    public String create(@ShellOption String text, @ShellOption Long bookId) {
        Comment comment = service.create(text, bookId);
        return comment.getId() != null ? "Record successfully added" : "Failed to add entry";
    }

    @ShellMethod(value = "Edit Comment", key = {"cedit", "comment-edit"})
    public String update(@ShellOption Long id, @ShellOption String text, @ShellOption Long bookId) {
        Comment comment = service.update(id, text, bookId);
        return Objects.equals(comment.getId(), id) ? "Record changed successfully" : "Failed to change record";
    }

    @ShellMethod(value = "Delete Comment by id", key = {"cdel", "comment-delete"})
    public String delete(@ShellOption Long id) {
        int result = service.delete(id);
        return result == 1 ? "The record was successfully deleted" : "Failed to delete record";
    }
}
