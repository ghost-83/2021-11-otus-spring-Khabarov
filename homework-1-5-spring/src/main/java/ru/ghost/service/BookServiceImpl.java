package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ghost.dao.BookDao;
import ru.ghost.domain.Author;
import ru.ghost.domain.Book;
import ru.ghost.domain.Genre;
import ru.ghost.exception.LibraryException;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookDao dao;

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public List<Book> findAll() {
        return dao.findAll();
    }

    @Override
    public Book findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Long create(String name, Long authorId, Long genreId) {
        Book book = new Book(null, name, new Author(authorId, null, null), new Genre(genreId, null));
        validate(book);
        return dao.create(book);
    }

    @Override
    public int update(Long id, String name, Long authorId, Long genreId) {
        ofNullable(id).orElseThrow(() -> new LibraryException("id is null."));
        Book book = new Book(id, name, new Author(authorId, null, null), new Genre(genreId, null));
        validate(book);
        return dao.update(book);
    }

    @Override
    public int delete(Long id) {
        return dao.delete(id);
    }

    private void validate(Book book) {
        if (isEmpty(book.getName())) {
            throw new LibraryException("name is null or empty.");
        }
    }
}