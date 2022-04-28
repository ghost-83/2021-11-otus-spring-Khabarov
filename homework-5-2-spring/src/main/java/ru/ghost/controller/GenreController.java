package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ghost.model.Genre;
import ru.ghost.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreController {

    private final GenreService service;

    @GetMapping("/genre")
    public List<Genre> finAll() {
        return service.findAll();
    }
}
