package ru.ghost.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.ghost.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

}
