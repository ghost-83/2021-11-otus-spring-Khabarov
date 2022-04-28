package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ghost.entity.mongo.Genre;
import ru.ghost.entity.relationalstorage.RS_Genre;
import ru.ghost.repository.relationalstorage.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    final GenreRepository genreRepository;

    @Override
    public RS_Genre convert(final Genre genre) {
        final RS_Genre rs_genre = RS_Genre.builder().name(genre.getName()).mongoId(genre.getId()).build();

        return genreRepository.save(rs_genre);
    }

    @Override
    public RS_Genre findByMongoId(final String id) {
        return genreRepository.findByMongoId(id);
    }
}
