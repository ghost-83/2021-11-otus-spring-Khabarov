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
import ru.ghost.model.Author;
import ru.ghost.model.Book;
import ru.ghost.model.Genre;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("Class BookServiceImpl")
@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceImplTest {

    private final Author AUTHOR = new Author(1L, "testFirstName", "testLastName");
    private final Genre GENRE = new Genre(1L, "testGenre");
    private final Book EXPECTED_BOOK = new Book(1L, "testBook", AUTHOR, GENRE);

    @MockBean
    private BookDao dao;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Autowired
    private BookServiceImpl service;

    @Test
    @DisplayName("return the expected number of books")
    void shouldReturnExpectedBookCount() {
        Long expectedCount = 3L;

        given(dao.count()).willReturn(expectedCount);

        Long actualCount = service.count();
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("return the expected list of books")
    void shouldReturnExpectedBooksList() {
        given(dao.findAll()).willReturn(List.of(EXPECTED_BOOK));

        List<Book> actualList = service.findAll();
        Assertions.assertThat(actualList).usingRecursiveFieldByFieldElementComparator().containsExactly(EXPECTED_BOOK);
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
        given(dao.save(any(Book.class))).willReturn(EXPECTED_BOOK);
        given(authorService.findById(AUTHOR.getId())).willReturn(AUTHOR);
        given(genreService.findById(GENRE.getId())).willReturn(GENRE);
        Book actualBook = service.create(EXPECTED_BOOK.getName(), EXPECTED_BOOK.getAuthor().getId(), EXPECTED_BOOK.getGenre().getId());

        assertThat(actualBook).isEqualTo(EXPECTED_BOOK);
    }

    @Test
    @DisplayName("edit a book")
    void shouldUpdateBook() {
        given(dao.save(any(Book.class))).willReturn(EXPECTED_BOOK);
        given(authorService.findById(AUTHOR.getId())).willReturn(AUTHOR);
        given(genreService.findById(GENRE.getId())).willReturn(GENRE);
        given(dao.findById(GENRE.getId())).willReturn(EXPECTED_BOOK);
        Book book = service.update(EXPECTED_BOOK.getId(), "test", AUTHOR.getId(), GENRE.getId());

        assertThat(book).isEqualTo(EXPECTED_BOOK);
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