package ru.ghost.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.ghost.model.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
}
