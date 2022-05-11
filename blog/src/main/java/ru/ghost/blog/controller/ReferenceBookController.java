package ru.ghost.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ghost.blog.dto.ReferenceBookDto;
import ru.ghost.blog.model.ReferenceBook;
import ru.ghost.blog.service.ReferenceBookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReferenceBookController {

    private final ReferenceBookService service;

    @GetMapping({"/book"})
    public List<ReferenceBook> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/book/genre/{genre}")
    public List<ReferenceBook> findAllByGenre(@PathVariable String genre) {
        return service.findAllByGenre(genre);
    }

    @GetMapping(value = "/book/search/{search}")
    public List<ReferenceBook> search(@PathVariable String search) {
        return service.search(search);
    }

    @PostMapping(value = "/book")
    public ReferenceBook save(@RequestBody ReferenceBookDto referenceBookDto) {
        return service.save(referenceBookDto);
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
