package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ghost.entity.mongo.Book;
import ru.ghost.entity.relationalstorage.RS_Author;
import ru.ghost.entity.relationalstorage.RS_Book;
import ru.ghost.entity.relationalstorage.RS_Genre;
import ru.ghost.repository.relationalstorage.BookRepository;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public RS_Book convert(final Book book) {
        final RS_Author rs_author = authorService.findByMongoId(book.getAuthor().getId());
        final RS_Genre rs_genre = genreService.findByMongoId(book.getGenre().getId());

        final RS_Book rs_book = RS_Book.builder()
                .name(book.getName())
                .genre(rs_genre)
                .author(rs_author)
                .notes(new ArrayList<>())
                .build();

        return bookRepository.save(rs_book);
    }
}
