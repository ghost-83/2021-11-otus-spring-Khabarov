package ru.ghost.service;

import ru.ghost.entity.mongo.Book;
import ru.ghost.entity.relationalstorage.RS_Book;

public interface BookService {

    RS_Book convert(Book book);
}
