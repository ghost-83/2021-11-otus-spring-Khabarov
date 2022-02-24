package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.ghost.model.Book;
import ru.ghost.repository.BookRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookController {

    private final BookRepository repository;

    @GetMapping({"/book"})
    public Flux<Book> findAll() {
        return repository.findAll();
    }

    @GetMapping({"/book/{id}"})
    public Mono<Book> findById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping("/book")
    public Mono<Book> save(@RequestBody Book book) {
        return repository.save(book);
    }

    @DeleteMapping("/book/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return repository.deleteById(id).flatMap(result -> Mono.empty());
    }
}
