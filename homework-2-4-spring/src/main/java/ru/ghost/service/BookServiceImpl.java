package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.exception.LibraryException;
import ru.ghost.model.Author;
import ru.ghost.model.Book;
import ru.ghost.model.Genre;
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
    public Book findById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new LibraryException("book not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public Book create(String name, String authorId, String genreId) {
        Author author = authorService.findById(authorId);
        Genre genre = genreService.findById(genreId);
        Book book = new Book(null, name, author, genre);

        validate(book);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book update(String id, String name, String authorId, String genreId) {
        Author author = authorService.findById(authorId);
        Genre genre = genreService.findById(genreId);
        Book book = findById(id);

        book.setName(name);
        book.setAuthor(author);
        book.setGenre(genre);

        validate(book);
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(String id) {
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