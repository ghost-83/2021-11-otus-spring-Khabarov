package ru.ghost.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ghost.entity.mongo.Author;

public interface AuthorMongoRepository extends MongoRepository<Author, String> {

}
