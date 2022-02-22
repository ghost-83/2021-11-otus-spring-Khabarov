package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ghost.dto.BookDto;
import ru.ghost.model.Book;
import ru.ghost.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
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

    @PostMapping("/book")
    public Book create(@RequestBody BookDto bookDto) {
        return service.create(bookDto);
    }

    @PutMapping("/book")
    public Book update(@RequestBody BookDto bookDto) {
        return service.update(bookDto);
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
