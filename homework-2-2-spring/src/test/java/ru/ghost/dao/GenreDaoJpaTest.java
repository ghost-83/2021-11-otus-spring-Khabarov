package ru.ghost.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.ghost.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import(GenreDaoJpa.class)
@DisplayName("The Dao for working with genres must")
class GenreDaoJpaTest {

    public static final Long EXISTING_GENRE_ID = 1L;
    public static final String EXISTING_GENRE_NAME = "Educational";
    public static final Long DELETABLE_GENRE_ID = 2L;
    public static final String DELETABLE_GENRE_NAME = "Scientific";

    @Autowired
    private GenreDaoJpa dao;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("return the expected number of genres in the database")
    void shouldReturnExpectedGenreCount() {
        Long actualCount = dao.count();

        assertThat(actualCount).isEqualTo(2L);
    }

    @Test
    @DisplayName("return the expected list of genres")
    void shouldReturnExpectedGenresList() {
        Genre expectedGenre1 = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
        Genre expectedGenre2 = new Genre(DELETABLE_GENRE_ID, DELETABLE_GENRE_NAME);

        List<Genre> actualGenreList = dao.findAll();

        Assertions.assertThat(actualGenreList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedGenre1, expectedGenre2);
    }

    @Test
    @DisplayName("return the expected genre by its id")
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);

        Genre actualGenre = dao.findById(expectedGenre.getId());

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("add a genre to the database")
    void shouldCreateGenre() {
        Genre genre = new Genre(null, "text");

        Genre genreCreate = dao.save(genre);
        Genre actualGenre = dao.findById(genreCreate.getId());

        assertThat(actualGenre.getName()).isEqualTo(genre.getName());
    }

    @Test
    @DisplayName("edit genre in db")
    void shouldUpdateGenre() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, "Detective");

        dao.save(expectedGenre);
        Genre actualGenre = dao.findById(expectedGenre.getId());

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("delete a given genre by its id")
    void shouldCorrectDeleteGenre() {
        Genre genre = em.find(Genre.class, DELETABLE_GENRE_ID);
        assertThat(genre).isNotNull();

        dao.delete(DELETABLE_GENRE_ID);
        em.detach(genre);

        genre = em.find(Genre.class, DELETABLE_GENRE_ID);
        assertThat(genre).isNull();
    }
}