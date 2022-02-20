package ru.ghost.service;

import ru.ghost.dto.CommentDto;
import ru.ghost.model.Comment;

import java.util.List;

public interface CommentService {

    Long count();

    List<Comment> findAll();

    List<Comment> findAllByBookId(Long id);

    Comment findById(Long id);

    Comment create(CommentDto commentDto);

    Comment update(CommentDto commentDto);

    void delete(Long id);
}
