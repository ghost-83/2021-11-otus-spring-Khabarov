package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ghost.dto.BookDto;
import ru.ghost.model.Book;
import ru.ghost.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService service;

    @GetMapping({"/book"})
    public List<Book> findAll() {
        return service.findAll();
    }

    @GetMapping({"/book/{id}"})
    public Book findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping(value = "/book")
    public Book save(@RequestBody BookDto bookDto) {
        return service.save(bookDto);
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
