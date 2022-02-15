package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.exception.LibraryException;
import ru.ghost.model.Book;
import ru.ghost.repository.BookRepository;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return bookRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new LibraryException("book not found."));
    }

    @Override
    @Transactional
    public Book save(Book book) {
        validate(book);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Book book = findById(id);
        bookRepository.delete(book);
    }

    private void validate(Book book) {
        if (isEmpty(book.getName())) {
            throw new LibraryException("name is null or empty.");
        }

        if (book.getAuthor() == null) {
            throw new LibraryException("author is null or empty.");
        }

        if (book.getGenre() == null) {
            throw new LibraryException("genre is null or empty.");
        }
    }
}