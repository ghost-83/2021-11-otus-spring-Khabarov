package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.ghost.model.Genre;
import ru.ghost.repository.GenreRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GenreController {

    private final GenreRepository repository;

    @GetMapping("/genre")
    public Flux<Genre> findAll() {
        return repository.findAll();
    }
}
