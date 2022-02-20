package ru.ghost.service;

import ru.ghost.dto.BookDto;
import ru.ghost.model.Book;

import java.util.List;

public interface BookService {

    Long count();

    List<Book> findAll();

    Book findById(Long id);

    Book create(BookDto bookDto);

    Book update(BookDto bookDto);

    void delete(Long id);
}