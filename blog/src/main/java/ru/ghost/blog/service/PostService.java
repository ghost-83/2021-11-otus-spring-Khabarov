package ru.ghost.blog.service;

import lombok.NonNull;
import ru.ghost.blog.model.Post;

import java.util.List;

public interface PostService {

    Long count();

    List<Post> findAll();

    List<Post> findAllByGenre(@NonNull String genre);

    List<Post> search(@NonNull String search);
}