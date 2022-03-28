package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.ghost.model.Author;
import ru.ghost.repository.AuthorRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthorController {

    private final AuthorRepository repository;

    @GetMapping("/author")
    public Flux<Author> finAll() {
        return repository.findAll();
    }
}
