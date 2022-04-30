package ru.ghost.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ghost.entity.mongo.Genre;

public interface GenreMongoRepository extends MongoRepository<Genre, String> {

}
