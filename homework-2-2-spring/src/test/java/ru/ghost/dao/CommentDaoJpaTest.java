package ru.ghost.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.ghost.model.Author;
import ru.ghost.model.Book;
import ru.ghost.model.Comment;
import ru.ghost.model.Genre;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(CommentDaoJpa.class)
@DisplayName("The Dao for working with comments must")
class CommentDaoJpaTest {

    private final Author AUTHOR = new Author(1L, "Ivan", "Pupcin");
    private final Genre GENRE = new Genre(1L, "Educational");
    private final Book BOOK = new Book(1L, "Learning java", AUTHOR, GENRE);
    private final Comment EXPECTED_COMMENT = new Comment(1L, "Good!", BOOK);

    @Autowired
    private CommentDaoJpa dao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("return the expected number of comments in the database")
    void shouldReturnExpectedCommentCount() {
        Long actualCount = dao.count();

        assertThat(actualCount).isEqualTo(1L);
    }

    @Test
    @DisplayName("return the expected list of comments")
    void shouldReturnExpectedCommentsList() {
        List<Comment> comments = dao.findAll();

        assertEquals(1, comments.size());
    }

    @Test
    @DisplayName("return the expected comment by its id")
    void shouldReturnExpectedCommentById() {
        Comment actualComment = dao.findById(EXPECTED_COMMENT.getId());

        assertEquals(1L, actualComment.getId());
        assertEquals(EXPECTED_COMMENT.getText(), actualComment.getText());
        assertEquals(EXPECTED_COMMENT.getBook().getId(), actualComment.getBook().getId());
    }

    @Test
    @DisplayName("add a comment to the database")
    void shouldCreateComment() {
        Comment comment = new Comment(null, "new book", BOOK);

        Comment commentCreate = dao.save(comment);
        Comment actualComment = dao.findById(commentCreate.getId());

        assertEquals(comment.getText(), actualComment.getText());
        assertEquals(BOOK.getId(), actualComment.getBook().getId());
    }

    @Test
    @DisplayName("edit a comment in the database")
    void shouldUpdateComment() {
        Comment expectedComment = dao.findById(EXPECTED_COMMENT.getId());
        expectedComment.setText("text");

        dao.save(expectedComment);
        Comment actualComment = dao.findById(expectedComment.getId());

        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    @DisplayName("delete a given comment by its id")
    void shouldCorrectlyDeleteComment() {
        Comment comment = em.find(Comment.class, EXPECTED_COMMENT.getId());
        assertThat(comment).isNotNull();

        dao.delete(EXPECTED_COMMENT.getId());
        em.detach(comment);

        comment = em.find(Comment.class, EXPECTED_COMMENT.getId());
        assertThat(comment).isNull();
    }
}