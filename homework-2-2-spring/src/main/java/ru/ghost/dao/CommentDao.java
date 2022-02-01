package ru.ghost.dao;

import ru.ghost.model.Comment;

import java.util.List;

public interface CommentDao {

    Long count();

    List<Comment> findAll();

    Comment findById(Long id);

    Comment save(Comment comment);

    int delete(Long id);
}
