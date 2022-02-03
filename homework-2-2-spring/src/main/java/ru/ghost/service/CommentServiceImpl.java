package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.dao.CommentDao;
import ru.ghost.model.Book;
import ru.ghost.model.Comment;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final BookService bookService;
    private final CommentDao commentDao;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return commentDao.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment create(String text, Long idBook) {
        Book book = bookService.findById(idBook);
        Comment comment = new Comment(null, text, book);
        return commentDao.save(comment);
    }

    @Override
    @Transactional
    public Comment update(Long id, String text, Long idBook) {
        Book book = bookService.findById(idBook);
        Comment comment = findById(id);
        comment.setBook(book);
        comment.setText(text);
        return commentDao.save(comment);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return commentDao.delete(id);
    }
}
