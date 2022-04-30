package ru.ghost.repository.relationalstorage;

import org.springframework.data.repository.CrudRepository;
import ru.ghost.entity.relationalstorage.RS_Book;

import java.util.List;

public interface BookRepository extends CrudRepository<RS_Book, Long> {

    List<RS_Book> findAll();
}
