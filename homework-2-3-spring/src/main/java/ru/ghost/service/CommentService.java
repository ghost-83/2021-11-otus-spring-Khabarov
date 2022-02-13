package ru.ghost.service;

import ru.ghost.model.Comment;

import java.util.List;

public interface CommentService {

    Long count();

    List<Comment> findAll();

    Comment findById(Long id);

    Comment create(String text, Long idBook);

    Comment update(Long id, String text, Long idBook);

    void delete(Long id);
}
