package ru.ghost.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.ghost.domain.Author;
import ru.ghost.domain.Book;
import ru.ghost.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@Import(BookDaoJdbc.class)
@DisplayName("The Dao for working with books must")
class BookDaoJdbcTest {

    public static final Long EXISTING_AUTHOR_ID = 1L;
    public static final String EXISTING_AUTHOR_FIRST_NAME = "Ivan";
    public static final String EXISTING_AUTHOR_LAST_NAME = "Pupcin";
    public static final Author EXISTING_AUTHOR = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME);

    public static final Long EXISTING_GENRE_ID = 1L;
    public static final String EXISTING_GENRE_NAME = "Educational";
    public static final Genre EXISTING_GENRE = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);

    public static final Long EXISTING_BOOK_ID = 1L;
    public static final String EXISTING_BOOK_NAME = "Learning java";
    public static final Book EXISTING_BOOK = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, EXISTING_AUTHOR, EXISTING_GENRE);

    @Autowired
    private BookDaoJdbc dao;

    @Test
    @DisplayName("return the expected number of books in the database")
    void shouldReturnExpectedBookCount() {
        int expectedCount = 1;

        int actualCount = dao.count();

        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("return the expected list of books")
    void shouldReturnExpectedBooksList() {
        List<Book> actualBookList = dao.findAll();

        Assertions.assertThat(actualBookList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(EXISTING_BOOK);
    }

    @Test
    @DisplayName("return the expected book by its id")
    void shouldReturnExpectedBookById() {
        Book actualBook = dao.findById(EXISTING_BOOK.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(EXISTING_BOOK);
    }

    @Test
    @DisplayName("add a book to the database")
    void shouldCreateBook() {
        Book book = new Book(null, "new book", EXISTING_AUTHOR, EXISTING_GENRE);

        Long id = dao.create(book);
        Book actualBook = dao.findById(id);

        assertThat(actualBook).extracting("name", "author.id", "genre.id")
                .doesNotContainNull()
                .containsExactly("new book", 1L, 1L);
    }

    @Test
    @DisplayName("edit a book in the database")
    void shouldUpdateBook() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, "text", EXISTING_AUTHOR, EXISTING_GENRE);

        dao.update(expectedBook);
        Book actualBook = dao.findById(expectedBook.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("delete a given book by its id")
    void shouldCorrectlyDeleteBook() {
        assertThatCode(() -> dao.findById(EXISTING_BOOK_ID)).doesNotThrowAnyException();

        dao.delete(EXISTING_BOOK_ID);

        assertThatThrownBy(() -> dao.findById(EXISTING_BOOK_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }
}