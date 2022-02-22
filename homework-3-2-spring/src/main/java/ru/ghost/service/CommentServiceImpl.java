package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.dto.CommentDto;
import ru.ghost.exception.LibraryException;
import ru.ghost.model.Book;
import ru.ghost.model.Comment;
import ru.ghost.repository.CommentRepository;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

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
    public List<Comment> findAllByBookId(Long id) {
        return commentRepository.findByBookId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new LibraryException("comment not found."));
    }

    @Override
    @Transactional
    public Comment create(CommentDto commentDto) {
        Book book = bookService.findById(commentDto.getBookId());
        Comment comment = new Comment(null, commentDto.getText(), book);
        validate(comment);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment update(CommentDto commentDto) {
        Comment comment = this.findById(commentDto.getId());
        Book book = bookService.findById(commentDto.getBookId());

        comment.setText(commentDto.getText());
        comment.setBook(book);

        validate(comment);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Comment comment = findById(id);
        commentRepository.delete(comment);
    }

    private void validate(Comment comment) {
        if (isEmpty(comment.getText())) {
            throw new LibraryException("Comment text is null or empty!");
        }
    }
}
