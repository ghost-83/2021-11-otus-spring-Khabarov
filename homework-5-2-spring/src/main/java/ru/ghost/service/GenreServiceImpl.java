package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.exception.LibraryException;
import ru.ghost.model.Genre;
import ru.ghost.repository.GenreRepository;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

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
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(() -> new LibraryException("genre not found."));
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        validate(genre);
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(Long id) {

        Genre genre = findById(id);
        genreRepository.delete(genre);
    }

    private void validate(Genre genre) {
        if (isEmpty(genre.getName())) {
            throw new LibraryException("name is null or empty.");
        }
    }
}