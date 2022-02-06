package ru.ghost.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.ghost.model.Genre;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {
}
