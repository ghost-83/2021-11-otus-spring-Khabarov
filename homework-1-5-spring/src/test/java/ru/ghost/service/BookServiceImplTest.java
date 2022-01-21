package ru.ghost.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ghost.dao.AuthorDao;
import ru.ghost.dao.BookDao;
import ru.ghost.dao.GenreDao;
import ru.ghost.domain.Author;
import ru.ghost.domain.Book;
import ru.ghost.domain.Genre;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("Class BookServiceImpl")
@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceImplTest {

    @MockBean
    private BookDao dao;

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private GenreDao genreDao;

    @Autowired
    private BookServiceImpl service;

    @Test
    @DisplayName("return the expected list of books")
    void shouldReturnExpectedBooksList() {
        Book book = new Book(1L, "book",
                new Author(2L, null, null),
                new Genre(2L, null));

        given(dao.findAll()).willReturn(List.of(book));

        List<Book> actualList = service.findAll();
        Assertions.assertThat(actualList).usingRecursiveFieldByFieldElementComparator().containsExactly(book);
    }

    @Test
    @DisplayName("return the expected number of books")
    void shouldReturnExpectedBookCount() {
        int expectedCount = 3;

        given(dao.count()).willReturn(expectedCount);

        int actualCount = service.count();
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("return the expected book by its id")
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(1L, "book",
                new Author(2L, null, null),
                new Genre(2L, null));

        given(dao.findById(expectedBook.getId())).willReturn(expectedBook);

        Book actualBook = service.findById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("add a book")
    void shouldCreateBook() {
        Long id = 1L;
        String name = "new book";
        Long authorId = 1L;
        Long genreId = 1L;

        given(dao.create(any(Book.class))).willReturn(id);

        Long actualId = service.create(name, authorId, genreId);
        assertThat(actualId).isEqualTo(id);
    }

    @Test
    @DisplayName("edit a book")
    void shouldUpdateBook() {
        Long id = 1L;
        String name = "updated book";
        Long authorId = 1L;
        Long genreId = 1L;

        int expectedUpdatedCount = 1;
        given(dao.update(any(Book.class))).willReturn(expectedUpdatedCount);

        int actualUpdatedCount = service.update(id, name, authorId, genreId);
        assertThat(actualUpdatedCount).isEqualTo(expectedUpdatedCount);
    }

    @Test
    @DisplayName("delete a given book by its id")
    void shouldCorrectlyDeleteBook() {
        Long id = 1L;
        int expectedDeletedCount = 1;

        given(dao.delete(id)).willReturn(expectedDeletedCount);

        int actualDeletedCount = service.delete(id);
        assertThat(actualDeletedCount).isEqualTo(expectedDeletedCount);
    }
}