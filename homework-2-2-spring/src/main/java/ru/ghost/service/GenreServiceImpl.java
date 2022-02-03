package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.dao.GenreDao;
import ru.ghost.model.Genre;
import ru.ghost.exception.LibraryException;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao dao;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return dao.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre create(String name) {
        Genre genre = new Genre(null, name);
        validate(genre);
        return dao.save(genre);
    }

    @Override
    @Transactional
    public Genre update(Long id, String name) {
        Genre genre = new Genre(id, name);
        ofNullable(id).orElseThrow(() -> new LibraryException("id is null."));
        validate(genre);
        return dao.save(genre);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return dao.delete(id);
    }

    private void validate(Genre genre) {
        if (isEmpty(genre.getName())) {
            throw new LibraryException("name is null or empty.");
        }
    }
}