package ru.ghost.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ghost.model.Author;
import ru.ghost.model.Book;
import ru.ghost.model.Comment;
import ru.ghost.model.Genre;
import ru.ghost.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Class CommentServiceImpl")
@SpringBootTest(classes = CommentServiceImpl.class)
class CommentServiceImplTest {

    private final Author AUTHOR = new Author(1L, "Ivan", "Pupcin");
    private final Genre GENRE = new Genre(1L, "Educational");
    private final Book BOOK = new Book(1L, "Learning java", AUTHOR, GENRE);
    private final Comment EXPECTED_COMMENT = new Comment(1L, "Good!", BOOK);

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private BookServiceImpl bookService;

    @Autowired
    private CommentServiceImpl commentService;

    @Test
    @DisplayName("return the expected number of comments in the database")
    void shouldReturnExpectedCommentCount() {
        Long expectedCount = 3L;

        given(commentRepository.count()).willReturn(expectedCount);
        Long actualCount = commentService.count();

        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("return the expected list of comments")
    void shouldReturnExpectedCommentsList() {
        given(commentRepository.findAll()).willReturn(List.of(EXPECTED_COMMENT));

        List<Comment> actualList = commentService.findAll();
        Assertions.assertThat(actualList).usingRecursiveFieldByFieldElementComparator().containsExactly(EXPECTED_COMMENT);
    }

    @Test
    @DisplayName("return the expected comment by its id")
    void shouldReturnExpectedCommentById() {
        given(commentRepository.findById(EXPECTED_COMMENT.getId())).willReturn(Optional.of(EXPECTED_COMMENT));
        Comment actualComment = commentService.findById(EXPECTED_COMMENT.getId());

        assertThat(actualComment).usingRecursiveComparison().isEqualTo(EXPECTED_COMMENT);
    }

    @Test
    @DisplayName("add a comment")
    void shouldCreateComment() {
        given(commentRepository.save(any(Comment.class))).willReturn(EXPECTED_COMMENT);
        given(bookService.findById(BOOK.getId())).willReturn(BOOK);
        Comment actualBook = commentService.create(EXPECTED_COMMENT.getText(), 1L);

        assertThat(actualBook).isEqualTo(EXPECTED_COMMENT);
    }

    @Test
    @DisplayName("edit a comment")
    void shouldUpdateComment() {
        given(commentRepository.save(any(Comment.class))).willReturn(EXPECTED_COMMENT);
        given(bookService.findById(BOOK.getId())).willReturn(BOOK);
        given(commentRepository.findById(EXPECTED_COMMENT.getId())).willReturn(Optional.of(EXPECTED_COMMENT));
        Comment actualBook = commentService.update(EXPECTED_COMMENT.getId(), "text", 1L);

        assertThat(actualBook).isEqualTo(EXPECTED_COMMENT);
    }

    @Test
    @DisplayName("delete a given comment by its id")
    void shouldCorrectlyDeleteComment() {

        given(commentRepository.findById(EXPECTED_COMMENT.getId())).willReturn(Optional.of(EXPECTED_COMMENT));

        commentService.delete(1L);

        verify(commentRepository, times(1)).delete(EXPECTED_COMMENT);
    }
}