package ru.ghost.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    public Comment(String id, String text, Book book) {
        this.id = id;
        this.text = text;
        this.book = book;
    }

    @Id
    private String id;

    private String text;

    private LocalDateTime time = now();

    private Book book;
}