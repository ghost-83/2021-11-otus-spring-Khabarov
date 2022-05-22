package ru.ghost.blog.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.blog.dto.ReferenceBookDto;
import ru.ghost.blog.exception.LibraryException;
import ru.ghost.blog.model.Author;
import ru.ghost.blog.model.Genre;
import ru.ghost.blog.model.ReferenceBook;
import ru.ghost.blog.repository.ReferenceBookRepository;

import java.util.List;
import java.util.Objects;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
@RequiredArgsConstructor
public class ReferenceBookServiceImpl implements ReferenceBookService {

    private final ReferenceBookRepository repository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return repository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReferenceBook> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ReferenceBook> findAllByGenre(@NonNull String genre) {
        Genre genreDao = genreService.findByName(genre);
        if (Objects.isNull(genreDao))
            throw new LibraryException("Genre not found.");
        return repository.findAllByGenre(genreDao);
    }

    @Override
    public List<ReferenceBook> search(@NonNull String search) {
        return repository.findAllByTitleContainingIgnoreCase(search);
    }

    @Override
    @Transactional(readOnly = true)
    public ReferenceBook findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new LibraryException("book not found."));
    }

    @Override
    @Transactional
    public ReferenceBook save(ReferenceBookDto referenceBookDto) {
        Author author = authorService.findById(referenceBookDto.getAuthorId());
        Genre genre = genreService.findById(referenceBookDto.getGenreId());
        ReferenceBook referenceBook = ReferenceBook
                .builder()
                .id(referenceBookDto.getId())
                .title(referenceBookDto.getTitle())
                .text(referenceBookDto.getText())
                .author(author)
                .genre(genre)
                .build();
        validate(referenceBook);
        return repository.save(referenceBook);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        ReferenceBook referenceBook = findById(id);
        repository.delete(referenceBook);
    }

    private void validate(ReferenceBook referenceBook) {
        if (isEmpty(referenceBook.getTitle())) {
            throw new LibraryException("title is null or empty.");
        }

        if (isEmpty(referenceBook.getText())) {
            throw new LibraryException("text is null or empty.");
        }

        if (referenceBook.getAuthor() == null) {
            throw new LibraryException("author is null or empty.");
        }

        if (referenceBook.getGenre() == null) {
            throw new LibraryException("genre is null or empty.");
        }
    }
}