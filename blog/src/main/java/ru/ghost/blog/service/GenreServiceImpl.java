package ru.ghost.blog.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.blog.exception.LibraryException;
import ru.ghost.blog.model.Genre;
import ru.ghost.blog.repository.GenreRepository;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final CachedDataService cachedDataService;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return genreRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand(groupKey = "GenreService", commandKey = "findAllGenre", fallbackMethod = "getGenres")
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(() -> new LibraryException("genre not found."));
    }

    @Override
    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        validate(genre);
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {

        Genre genre = findById(id);
        genreRepository.delete(genre);
    }

    private List<Genre> getGenres() {
        return List.of(cachedDataService.getGenre());
    }

    private void validate(Genre genre) {
        if (isEmpty(genre.getName())) {
            throw new LibraryException("name is null or empty.");
        }
    }
}