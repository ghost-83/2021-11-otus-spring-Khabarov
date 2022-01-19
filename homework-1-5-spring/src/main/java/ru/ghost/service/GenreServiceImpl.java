package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ghost.dao.GenreDao;
import ru.ghost.domain.Genre;
import ru.ghost.exception.LibraryException;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao dao;

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public List<Genre> findAll() {
        return dao.findAll();
    }

    @Override
    public Genre findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Long create(String name) {
        Genre genre = new Genre(null, name);
        validate(genre);
        return dao.create(genre);
    }

    @Override
    public int update(Long id, String name) {
        Genre genre = new Genre(id, name);
        ofNullable(id).orElseThrow(() -> new LibraryException("id is null."));
        validate(genre);
        return dao.update(genre);
    }

    @Override
    public int delete(Long id) {
        return dao.delete(id);
    }

    private void validate(Genre genre) {
        if (isEmpty(genre.getName())) {
            throw new LibraryException("name is null or empty.");
        }
    }
}