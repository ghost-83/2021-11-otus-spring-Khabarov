package ru.ghost.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ghost.model.Author;
import ru.ghost.model.Book;
import ru.ghost.model.Genre;
import ru.ghost.repository.BookRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Class BookServiceImpl")
@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceImplTest {

    private final Author AUTHOR = new Author("1L", "testFirstName", "testLastName");
    private final Genre GENRE = new Genre("1L", "testGenre");
    private final Book EXPECTED_BOOK = new Book("1L", "testBook", AUTHOR, GENRE);

    @MockBean
    private BookRepository bookRepository;

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

        given(bookRepository.count()).willReturn(expectedCount);

        Long actualCount = service.count();
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("return the expected list of books")
    void shouldReturnExpectedBooksList() {
        given(bookRepository.findAll()).willReturn(List.of(EXPECTED_BOOK));

        List<Book> actualList = service.findAll();
        Assertions.assertThat(actualList).usingRecursiveFieldByFieldElementComparator().containsExactly(EXPECTED_BOOK);
    }

    @Test
    @DisplayName("return the expected book by its id")
    void shouldReturnExpectedBookById() {

        given(bookRepository.findById(EXPECTED_BOOK.getId())).willReturn(Optional.of(EXPECTED_BOOK));
        Book actualBook = service.findById(EXPECTED_BOOK.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(EXPECTED_BOOK);
    }

    @Test
    @DisplayName("add a book")
    void shouldCreateBook() {
        given(bookRepository.save(any(Book.class))).willReturn(EXPECTED_BOOK);
        given(authorService.findById(AUTHOR.getId())).willReturn(AUTHOR);
        given(genreService.findById(GENRE.getId())).willReturn(GENRE);
        Book actualBook = service.create(EXPECTED_BOOK.getName(), EXPECTED_BOOK.getAuthor().getId(), EXPECTED_BOOK.getGenre().getId());

        assertThat(actualBook).isEqualTo(EXPECTED_BOOK);
    }

    @Test
    @DisplayName("edit a book")
    void shouldUpdateBook() {
        given(bookRepository.save(any(Book.class))).willReturn(EXPECTED_BOOK);
        given(authorService.findById(AUTHOR.getId())).willReturn(AUTHOR);
        given(genreService.findById(GENRE.getId())).willReturn(GENRE);
        given(bookRepository.findById(GENRE.getId())).willReturn(Optional.of(EXPECTED_BOOK));
        Book book = service.update(EXPECTED_BOOK.getId(), "test", AUTHOR.getId(), GENRE.getId());

        assertThat(book).isEqualTo(EXPECTED_BOOK);
    }

    @Test
    @DisplayName("delete a given book by its id")
    void shouldCorrectlyDeleteBook() {

        given(bookRepository.findById(EXPECTED_BOOK.getId())).willReturn(Optional.of(EXPECTED_BOOK));

        service.delete("1L");

        verify(bookRepository, times(1)).delete(EXPECTED_BOOK);
    }
}