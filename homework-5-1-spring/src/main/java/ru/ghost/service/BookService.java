package ru.ghost.service;

import ru.ghost.dto.BookDto;
import ru.ghost.model.Book;

import java.util.List;

public interface BookService {

    Long count();

    List<Book> findAll();

    Book findById(Long id);

    Book save(BookDto bookDto);

    void delete(Long id);
}