package ru.ghost.blog.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ghost.blog.dto.ReferenceBookDto;
import ru.ghost.blog.model.Author;
import ru.ghost.blog.model.Genre;
import ru.ghost.blog.model.ReferenceBook;
import ru.ghost.blog.repository.ReferenceBookRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Class BookServiceImpl")
@SpringBootTest(classes = ReferenceBookServiceImpl.class)
class ReferenceBookServiceImplTest {

    private final Author AUTHOR = new Author(1L, "testFirstName", "testLastName");
    private final Genre GENRE = new Genre(1L, "testGenre");
    private final ReferenceBook EXPECTED_BOOK = new ReferenceBook(1L, "testReferenceBook", "text", AUTHOR, GENRE);

    @MockBean
    private ReferenceBookRepository bookRepository;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Autowired
    private ReferenceBookService service;

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

        List<ReferenceBook> actualList = service.findAll();
        Assertions.assertThat(actualList).usingRecursiveFieldByFieldElementComparator().containsExactly(EXPECTED_BOOK);
    }

    @Test
    @DisplayName("return the expected book by its id")
    void shouldReturnExpectedBookById() {

        given(bookRepository.findById(EXPECTED_BOOK.getId())).willReturn(Optional.of(EXPECTED_BOOK));
        ReferenceBook referenceBook = service.findById(EXPECTED_BOOK.getId());

        assertThat(referenceBook).usingRecursiveComparison().isEqualTo(EXPECTED_BOOK);
    }

    @Test
    @DisplayName("add a book")
    void shouldSaveBook() {
        given(bookRepository.save(any(ReferenceBook.class))).willReturn(EXPECTED_BOOK);
        given(authorService.findById(AUTHOR.getId())).willReturn(AUTHOR);
        given(genreService.findById(GENRE.getId())).willReturn(GENRE);
        ReferenceBook referenceBook = service.save(ReferenceBookDto.builder()
                .id(EXPECTED_BOOK.getId())
                .title(EXPECTED_BOOK.getTitle())
                .text(EXPECTED_BOOK.getText())
                .authorId(EXPECTED_BOOK.getAuthor().getId())
                .genreId(EXPECTED_BOOK.getGenre().getId())
                .build());

        assertThat(referenceBook).isEqualTo(EXPECTED_BOOK);
    }

    @Test
    @DisplayName("delete a given book by its id")
    void shouldCorrectlyDeleteBook() {

        given(bookRepository.findById(EXPECTED_BOOK.getId())).willReturn(Optional.of(EXPECTED_BOOK));

        service.delete(1L);

        verify(bookRepository, times(1)).delete(EXPECTED_BOOK);
    }
}