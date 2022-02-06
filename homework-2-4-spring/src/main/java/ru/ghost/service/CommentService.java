package ru.ghost.service;

import ru.ghost.model.Comment;

import java.util.List;

public interface CommentService {

    Long count();

    List<Comment> findAll();

    Comment findById(String id);

    Comment create(String text, String idBook);

    Comment update(String id, String text, String idBook);

    void delete(String id);
}
