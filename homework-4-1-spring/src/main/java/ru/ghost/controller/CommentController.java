package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.ghost.model.Comment;
import ru.ghost.repository.CommentRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentRepository repository;

    @GetMapping("/book/{bookId}")
    public Flux<Comment> findByBookId(@PathVariable("bookId") String bookId) {
        return repository.findAllByBookId(bookId);
    }

    @PostMapping("/")
    public Mono<Comment> save(@RequestBody Comment comment) {
        return repository.save(comment);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return repository.deleteById(id);
    }
}
