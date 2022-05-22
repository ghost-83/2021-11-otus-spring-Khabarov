package ru.ghost.blog.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ghost.blog.model.Genre;
import ru.ghost.blog.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @NonNull
    @Override
    @EntityGraph(value = "Post.Author.Genre")
    List<Post> findAll();

    @EntityGraph(value = "Post.Author.Genre")
    List<Post> findAllByTitleContainingIgnoreCase(String search);

    @EntityGraph(value = "Post.Author.Genre")
    List<Post> findAllByGenre(Genre genre);
}
