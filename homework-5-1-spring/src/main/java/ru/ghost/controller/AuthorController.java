package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ghost.model.Author;
import ru.ghost.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthorController {

    private final AuthorService service;

    @GetMapping("/author")
    public List<Author> finAll() {
        return service.findAll();
    }
}
