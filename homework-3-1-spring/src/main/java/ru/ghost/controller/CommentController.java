package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ghost.model.Comment;
import ru.ghost.service.CommentService;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService service;

    @PostMapping("/comments/save")
    public String save(Comment comment) {
        service.save(comment);
        return "redirect:/books/edit?id=" + comment.getBook().getId();
    }

    @PostMapping("/comments/delete")
    public String delete(@RequestParam("id") Long id, @RequestParam("bookId") Long bookId) {
        service.delete(id);
        return "redirect:/books/edit?id=" + bookId;
    }
}
