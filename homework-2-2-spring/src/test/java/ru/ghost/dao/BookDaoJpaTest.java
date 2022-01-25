package ru.ghost.dao;

import org.assertj.core.api.Assertions;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.ghost.model.Author;
import ru.ghost.model.Book;
import ru.ghost.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import(BookDaoJpa.class)
@DisplayName("The Dao for working with books must")
class BookDaoJpaTest {

    private final Author AUTHOR = new Author(1L, "Ivan", "Pupcin");
    private final Genre GENRE = new Genre(1L, "Educational");
    private final Book EXPECTED_BOOK = new Book(1L, "Learning java", AUTHOR, GENRE);

    @Autowired
    private BookDaoJpa dao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("return the expected number of books in the database")
    void shouldReturnExpectedBookCount() {
        Long actualCount = dao.count();

        assertThat(actualCount).isEqualTo(1L);
    }

    @Test
    @DisplayName("return the expected list of books")
    void shouldReturnExpectedBooksList() {
        List<Book> books = dao.findAll();

        Assertions.assertThat(books)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(EXPECTED_BOOK);
    }

    @Test
    @DisplayName("return the expected book by its id")
    void shouldReturnExpectedBookById() {
        Book actualBook = dao.findById(EXPECTED_BOOK.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(EXPECTED_BOOK);
    }

    @Test
    @DisplayName("add a book to the database")
    void shouldCreateBook() {
        Book book = new Book(null, "new book", AUTHOR, GENRE);

        Book bookCreate = dao.save(book);
        Book actualBook = dao.findById(bookCreate.getId());

        assertThat(actualBook).extracting("name", "author.id", "genre.id")
                .doesNotContainNull()
                .containsExactly("new book", 1L, 1L);
    }

    @Test
    @DisplayName("edit a book in the database")
    void shouldUpdateBook() {
        Book expectedBook = dao.findById(EXPECTED_BOOK.getId());
        expectedBook.setName("text");

        dao.save(expectedBook);
        Book actualBook = dao.findById(expectedBook.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("delete a given book by its id")
    void shouldCorrectlyDeleteBook() {
        Book book = em.find(Book.class, EXPECTED_BOOK.getId());
        assertThat(book).isNotNull();

        dao.delete(EXPECTED_BOOK.getId());
        em.detach(book);

        book = em.find(Book.class, EXPECTED_BOOK.getId());
        assertThat(book).isNull();
    }
}