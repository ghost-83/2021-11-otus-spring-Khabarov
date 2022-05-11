package ru.ghost.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ghost.blog.model.Post;
import ru.ghost.blog.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private final PostService service;

    @GetMapping({"/post"})
    public List<Post> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/post/genre/{genre}")
    public List<Post> findAllByGenre(@PathVariable String genre) {
        return service.findAllByGenre(genre);
    }

    @GetMapping(value = "/post/search/{search}")
    public List<Post> search(@PathVariable String search) {
        return service.search(search);
    }

}
