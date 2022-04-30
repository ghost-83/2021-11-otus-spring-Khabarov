package ru.ghost.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.ghost.entity.mongo.Book;

import java.util.List;

public interface BookMongoRepository extends MongoRepository<Book, String> {

    List<Book> findAll();

}
