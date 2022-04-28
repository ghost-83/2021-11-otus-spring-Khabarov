package ru.ghost.service;

import ru.ghost.entity.mongo.Author;
import ru.ghost.entity.relationalstorage.RS_Author;

public interface AuthorService {

    RS_Author convert(Author author);

    RS_Author findByMongoId(String mongoId);
}
