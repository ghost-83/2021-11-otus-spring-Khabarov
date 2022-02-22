package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ghost.dto.CommentDto;
import ru.ghost.model.Comment;
import ru.ghost.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentService service;

    @GetMapping("/book/{bookId}/comments")
    public List<Comment> findByBookId(@PathVariable("bookId") Long bookId) {
        return service.findAllByBookId(bookId);
    }

    @PostMapping("/comment")
    public Comment create(@RequestBody CommentDto commentDto) {
        return service.create(commentDto);
    }

    @DeleteMapping("/comment/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
