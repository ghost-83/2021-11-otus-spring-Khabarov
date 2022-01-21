package ru.ghost.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ghost.dao.AuthorDao;
import ru.ghost.domain.Author;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("Class AuthorServiceImpl")
@SpringBootTest(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {

    @MockBean
    private AuthorDao dao;

    @Autowired
    private AuthorServiceImpl service;

    @Test
    @DisplayName("return the expected list of authors")
    void shouldReturnExpectedAuthorsList() {
        Author author = new Author(1L, "Sergey", "Gubcin");

        given(dao.findAll()).willReturn(List.of(author));
        List<Author> actualList = service.findAll();

        Assertions.assertThat(actualList).usingRecursiveFieldByFieldElementComparator().containsExactly(author);
    }

    @Test
    @DisplayName("return the expected number of authors")
    void shouldReturnExpectedAuthorCount() {
        int expectedCount = 3;

        given(dao.count()).willReturn(expectedCount);
        int actualCount = service.count();

        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("return the expected author by his id")
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(1L, "Sergey", "Gubcin");

        given(dao.findById(expectedAuthor.getId())).willReturn(expectedAuthor);
        Author actualAuthor = service.findById(expectedAuthor.getId());

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("add an author")
    void shouldCreateAuthor() {
        Long id = 1L;
        String firstName = "Sergey";
        String lastName = "Gubcin";

        given(dao.create(any(Author.class))).willReturn(id);
        Long actualId = service.create(firstName, lastName);

        assertThat(actualId).isEqualTo(id);
    }

    @Test
    @DisplayName("edit the author")
    void shouldUpdateAuthor() {
        Long id = 1L;
        String firstName = "Sergey";
        String lastName = "Gubcin";
        int expectedUpdatedCount = 1;

        given(dao.update(any(Author.class))).willReturn(expectedUpdatedCount);
        int actualUpdatedCount = service.update(id, firstName, lastName);

        assertThat(actualUpdatedCount).isEqualTo(expectedUpdatedCount);
    }

    @Test
    @DisplayName("delete a given author by his id")
    void shouldCorrectlyDeleteAuthor() {
        Long id = 1L;
        int expectedDeletedCount = 1;

        given(dao.delete(id)).willReturn(expectedDeletedCount);
        int actualDeletedCount = service.delete(id);

        assertThat(actualDeletedCount).isEqualTo(expectedDeletedCount);
    }
}