package ru.ghost.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.ghost.model.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import(AuthorDaoJpa.class)
@DisplayName("The Dao for working with authors must")
class AuthorDaoJpaTest {

    public static final Long EXISTING_AUTHOR_ID = 1L;
    public static final String EXISTING_AUTHOR_FIRST_NAME = "Ivan";
    public static final String EXISTING_AUTHOR_LAST_NAME = "Pupcin";

    public static final Long DELETABLE_AUTHOR_ID = 2L;
    public static final String DELETABLE_AUTHOR_FIRST_NAME = "Sergey";
    public static final String DELETABLE_AUTHOR_LAST_NAME = "Gubcin";

    @Autowired
    private AuthorDaoJpa dao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("return the expected number of authors in the database")
    void shouldReturnExpectedAuthorCount() {
        Long actualCount = dao.count();

        assertThat(actualCount).isEqualTo(2L);
    }


    @Test
    @DisplayName("return the expected list of authors")
    void shouldReturnExpectedAuthorsList() {
        Author expectedAuthor1 = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME);
        Author expectedAuthor2 = new Author(DELETABLE_AUTHOR_ID, DELETABLE_AUTHOR_FIRST_NAME, DELETABLE_AUTHOR_LAST_NAME);

        List<Author> actualAuthorList = dao.findAll();

        Assertions.assertThat(actualAuthorList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedAuthor1, expectedAuthor2);
    }

    @Test
    @DisplayName("return the expected author by his id")
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_FIRST_NAME, EXISTING_AUTHOR_LAST_NAME);

        Author actualAuthor = dao.findById(expectedAuthor.getId());

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("add an author to the database")
    void shouldCreateAuthor() {
        Author author = new Author(null, "Ivan", "Pupcin");

        Author authorCreate = dao.save(author);
        Author actualAuthor = dao.findById(authorCreate.getId());

        assertThat(actualAuthor).extracting("firstName", "lastName")
                .doesNotContainNull()
                .containsExactly("Ivan", "Pupcin");
    }

    @Test
    @DisplayName("edit the author in the database")
    void shouldUpdateAuthor() {
        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, "Sergey", "Gubcin");

        dao.save(expectedAuthor);
        Author actualAuthor = dao.findById(expectedAuthor.getId());

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }


    @Test
    @DisplayName("delete a given author by his id")
    void shouldCorrectDeleteAuthor() {
        Author author = em.find(Author.class, DELETABLE_AUTHOR_ID);
        assertThat(author).isNotNull();

        dao.delete(DELETABLE_AUTHOR_ID);
        em.detach(author);

        author = em.find(Author.class, DELETABLE_AUTHOR_ID);
        assertThat(author).isNull();
    }
}