package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.exception.LibraryException;
import ru.ghost.model.Book;
import ru.ghost.model.Comment;
import ru.ghost.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final BookService bookService;
    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return commentRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new LibraryException("comment not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public Comment create(String text, Long idBook) {
        Book book = bookService.findById(idBook);
        Comment comment = new Comment(null, text, book);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment update(Long id, String text, Long idBook) {
        Book book = bookService.findById(idBook);
        Comment comment = findById(id);
        comment.setBook(book);
        comment.setText(text);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Comment comment = findById(id);
        commentRepository.delete(comment);
    }
}
