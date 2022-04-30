package ru.ghost.repository.relationalstorage;

import org.springframework.data.repository.CrudRepository;
import ru.ghost.entity.relationalstorage.RS_Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<RS_Author, Long> {

    RS_Author findByMongoId(String mongoId);

    List<RS_Author> findAll();
}
