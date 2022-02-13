package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.exception.LibraryException;
import ru.ghost.model.Book;
import ru.ghost.model.Genre;
import ru.ghost.repository.GenreRepository;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final BookService bookService;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return genreRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findById(String id) {
        return genreRepository.findById(id).orElseThrow(() -> new LibraryException("genre not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public Genre create(String name) {
        Genre genre = new Genre(null, name);
        validate(genre);
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    public Genre update(String id, String name) {
        ofNullable(id).orElseThrow(() -> new LibraryException("id is null."));
        Genre genre = this.findById(id);

        List<Book> books = bookService.findAllByGenre(genre);

        genre.setName(name);
        validate(genre);

        if (books.size() > 0) {
            books.forEach(book -> book.setGenre(genre));
            bookService.updateAll(books);
        }
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    public void delete(String id) {
        genreRepository.deleteById(id);
    }

    private void validate(Genre genre) {
        if (isEmpty(genre.getName())) {
            throw new LibraryException("name is null or empty.");
        }
    }
}