package ru.ghost.blog.service;

import ru.ghost.blog.model.Author;
import ru.ghost.blog.model.Genre;
import ru.ghost.blog.model.Post;
import ru.ghost.blog.model.ReferenceBook;

public interface CachedDataService {

    Author getAuthor();

    Post getPost();

    Genre getGenre();

    ReferenceBook getReferenceBook();
}
