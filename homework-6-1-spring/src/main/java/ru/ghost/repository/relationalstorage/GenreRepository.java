package ru.ghost.repository.relationalstorage;

import org.springframework.data.repository.CrudRepository;
import ru.ghost.entity.relationalstorage.RS_Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<RS_Genre, Long> {

    RS_Genre findByMongoId(String id);

    @Override
    List<RS_Genre> findAll();
}
