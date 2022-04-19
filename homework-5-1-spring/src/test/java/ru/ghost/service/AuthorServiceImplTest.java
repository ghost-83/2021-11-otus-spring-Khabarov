package ru.ghost.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ghost.model.Author;
import ru.ghost.repository.AuthorRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Class AuthorServiceImpl")
@SpringBootTest(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {

    private final Author EXPECTED_AUTHOR = new Author(1L, "Sergey", "Gubcin");

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorServiceImpl service;

    @Test
    @DisplayName("return the expected number of authors")
    void shouldReturnExpectedAuthorCount() {
        Long expectedCount = 3L;

        given(authorRepository.count()).willReturn(expectedCount);
        Long actualCount = service.count();

        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("return the expected list of authors")
    void shouldReturnExpectedAuthorsList() {
        Long expectedCount = 2L;

        given(authorRepository.count()).willReturn(expectedCount);
        Long actualCount = service.count();

        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("return the expected author by his id")
    void shouldReturnExpectedAuthorById() {
        given(authorRepository.findById(EXPECTED_AUTHOR.getId())).willReturn(Optional.of(EXPECTED_AUTHOR));
        Author actualAuthor = service.findById(EXPECTED_AUTHOR.getId());

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(EXPECTED_AUTHOR);
    }

    @Test
    @DisplayName("add an author")
    void shouldSaveAuthor() {
        given(authorRepository.save(any(Author.class))).willReturn(EXPECTED_AUTHOR);
        Author actualAuthor = service.save(EXPECTED_AUTHOR);

        assertThat(actualAuthor).isEqualTo(EXPECTED_AUTHOR);
    }

    @Test
    @DisplayName("delete a given author by his id")
    void shouldCorrectlyDeleteAuthor() {

        given(authorRepository.findById(EXPECTED_AUTHOR.getId())).willReturn(Optional.of(EXPECTED_AUTHOR));

        service.delete(1L);

        verify(authorRepository, times(1)).delete(EXPECTED_AUTHOR);
    }
}