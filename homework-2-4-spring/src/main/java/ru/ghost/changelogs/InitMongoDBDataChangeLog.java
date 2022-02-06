package ru.ghost.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import ru.ghost.model.Author;
import ru.ghost.model.Book;
import ru.ghost.model.Comment;
import ru.ghost.model.Genre;
import ru.ghost.repository.BookRepository;
import ru.ghost.repository.CommentRepository;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private final Author author1 = new Author(ObjectId.get().toString(), "AuthorF1", "AuthorL1");
    private final Author author2 = new Author(ObjectId.get().toString(), "AuthorF2", "AuthorL2");
    private final Genre genre1 = new Genre(ObjectId.get().toString(), "Genre1");
    private final Genre genre2 = new Genre(ObjectId.get().toString(), "Genre2");
    private Book book1;
    private Book book2;

    @ChangeSet(order = "000", id = "dropDB", author = "pk", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initBooks", author = "pk", runAlways = true)
    public void initBooks(BookRepository repository) {
        book1 = repository.save(new Book(null, "Book1", author1, genre1));
        book2 = repository.save(new Book(null, "Book2", author2, genre2));
        Book book3 = repository.save(new Book(null, "Book3", author1, genre1));
    }

    @ChangeSet(order = "002", id = "initComments", author = "pk", runAlways = true)
    public void initComments(CommentRepository repository) {
        repository.save(new Comment(null, "Comment1", book1));
        repository.save(new Comment(null, "Comment2", book2));
        repository.save(new Comment(null, "Comment3", book1));
    }
}
