package ru.ghost.dao;

import ru.ghost.model.Comment;

import java.util.List;

public interface CommentDao {

    Long count();

    List<Comment> findAll();

    List<Comment> findAllByBookId(Long bookId);

    Comment findById(Long id);

    Comment save(Comment comment);

    int delete(Long id);
}
