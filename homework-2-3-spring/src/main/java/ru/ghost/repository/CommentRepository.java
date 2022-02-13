package ru.ghost.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ghost.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Override
    List<Comment> findAll();

    @EntityGraph(value = "Comment.Book.Author.Genre")
    @Override
    Optional<Comment> findById(Long id);
}
