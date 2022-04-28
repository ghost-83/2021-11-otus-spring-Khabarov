package ru.ghost.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.ghost.entity.mongo.Author;
import ru.ghost.entity.mongo.Book;
import ru.ghost.entity.mongo.Genre;
import ru.ghost.repository.mongo.AuthorMongoRepository;
import ru.ghost.repository.mongo.BookMongoRepository;
import ru.ghost.repository.mongo.GenreMongoRepository;

import java.util.ArrayList;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "KhabarovVV", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "add-authors", author = "KhabarovVV")
    public void addAuthor(AuthorMongoRepository authorRepository) {
        authorRepository.deleteAll();
        authorRepository.save(new Author("1", "Andrzej Sapkowski"));
        authorRepository.save(new Author("2", "Paul Berry"));
    }

    @ChangeSet(order = "003", id = "add-genres", author = "KhabarovVV")
    public void addGenre(GenreMongoRepository genreRepository) {
        genreRepository.deleteAll();
        genreRepository.save(new Genre("1", "Fantasy"));
        genreRepository.save(new Genre("2", "Educational"));
    }

    @ChangeSet(order = "004", id = "add-books", author = "KhabarovVV")
    public void addBook(BookMongoRepository bookRepository) {
        bookRepository.deleteAll();
        bookRepository.save(new Book("1", "The Witcher Saga", new Genre("1", "Fantasy"), new Author("1", "Andrzej Sapkowski"), new ArrayList<>()));
        bookRepository.save(new Book("2", "Learning Python programming", new Genre("2", "Educational"), new Author("2", "Paul Berry"), new ArrayList<>()));
    }

}
