package ru.ghost.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.ghost.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@Import(GenreDaoJdbc.class)
@DisplayName("The Dao for working with genres must")
class GenreDaoJdbcTest {

    public static final Long EXISTING_GENRE_ID = 1L;
    public static final String EXISTING_GENRE_NAME = "Educational";
    public static final Long DELETABLE_GENRE_ID = 2L;
    public static final String DELETABLE_GENRE_NAME = "Scientific";

    @Autowired
    private GenreDaoJdbc dao;

    @Test
    @DisplayName("return the expected number of genres in the database")
    void shouldReturnExpectedGenreCount() {
        int expectedCount = 2;

        int actualCount = dao.count();

        assertThat(actualCount).isEqualTo(expectedCount);
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

        Long id = dao.create(genre);
        Genre actualGenre = dao.findById(id);

        assertThat(actualGenre.getName()).isEqualTo(genre.getName());
    }

    @Test
    @DisplayName("edit genre in db")
    void shouldUpdateGenre() {
        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, "Detective");

        dao.update(expectedGenre);
        Genre actualGenre = dao.findById(expectedGenre.getId());

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("delete a given genre by its id")
    void shouldCorrectDeleteGenre() {
        assertThatCode(() -> dao.findById(DELETABLE_GENRE_ID)).doesNotThrowAnyException();

        dao.delete(DELETABLE_GENRE_ID);

        assertThatThrownBy(() -> dao.findById(DELETABLE_GENRE_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }
}