package ru.ghost.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    private Genre genre;

    private Author author;

    private List<Note> notes;

    @Override
    public String toString() {
        return "\n" +
                id + ". " + name +
                "\nAuthor: " + author.getName() +
                " Genre: " + genre.getName() +
                "\n";
    }
}
