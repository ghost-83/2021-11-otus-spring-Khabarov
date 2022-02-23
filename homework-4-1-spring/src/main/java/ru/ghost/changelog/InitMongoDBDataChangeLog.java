package ru.ghost.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import ru.ghost.model.Author;
import ru.ghost.model.Book;
import ru.ghost.model.Comment;
import ru.ghost.model.Genre;
import ru.ghost.repository.AuthorRepository;
import ru.ghost.repository.BookRepository;
import ru.ghost.repository.CommentRepository;
import ru.ghost.repository.GenreRepository;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private final Author author1 = new Author(ObjectId.get().toString(), "Andrzej", "Sapkowski");
    private final Author author2 = new Author(ObjectId.get().toString(), "Paul", "Berry");
    private final Genre genre1 = new Genre(ObjectId.get().toString(), "Fantasy");
    private final Genre genre2 = new Genre(ObjectId.get().toString(), "Educational");
    private Book book1;
    private Book book2;

    @ChangeSet(order = "000", id = "dropDB", author = "pk", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "pk", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        repository.save(author1).block();
        repository.save(author2).block();
    }

    @ChangeSet(order = "002", id = "initGenre", author = "pk", runAlways = true)
    public void initGenres(GenreRepository repository) {
        repository.save(genre1).block();
        repository.save(genre2).block();
    }

    @ChangeSet(order = "003", id = "initBooks", author = "pk", runAlways = true)
    public void initBooks(BookRepository repository) {
        book1 = repository.save(new Book(null, "The Witcher Saga", author1, genre1)).block();
        book2 = repository.save(new Book(null, "Learning Python programming", author2, genre2)).block();
    }

    @ChangeSet(order = "004", id = "initComments", author = "pk", runAlways = true)
    public void initComments(CommentRepository repository) {
        repository.save(new Comment(null, "Good!", book1.getId())).block();
        repository.save(new Comment(null, "Very good!", book2.getId())).block();
    }
}
