package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.exception.LibraryException;
import ru.ghost.model.Author;
import ru.ghost.model.Book;
import ru.ghost.model.Comment;
import ru.ghost.model.Genre;
import ru.ghost.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

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
    public List<Book> findAllByAuthor(Author author) {
        return bookRepository.findAllByAuthor(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAllByGenre(Genre genre) {
        return bookRepository.findAllByGenre(genre);
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
    public void updateAll(List<Book> books) {
        bookRepository.saveAll(books);
    }

    @Override
    @Transactional
    public void delete(String id) {
        List<Comment> comments = commentService.findAllByBookId(id);
        if (comments.size() > 0) {
            commentService.deleteAll(comments
                    .stream()
                    .map(Comment::getId)
                    .collect(Collectors.toList()));
        }
        bookRepository.deleteById(id);
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