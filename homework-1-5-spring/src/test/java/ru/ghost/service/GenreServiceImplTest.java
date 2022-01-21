package ru.ghost.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ghost.dao.GenreDao;
import ru.ghost.domain.Genre;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("Class GenreServiceImpl")
@SpringBootTest(classes = GenreServiceImpl.class)
class GenreServiceImplTest {

    @MockBean
    private GenreDao dao;

    @Autowired
    private GenreServiceImpl service;

    @Test
    @DisplayName("return the expected list of genres")
    void shouldReturnExpectedGenresList() {
        Genre genre = new Genre(1L, "new genre");

        given(dao.findAll()).willReturn(List.of(genre));
        List<Genre> actualList = service.findAll();

        Assertions.assertThat(actualList).usingRecursiveFieldByFieldElementComparator().containsExactly(genre);
    }

    @Test
    @DisplayName("return the expected number of genres")
    void shouldReturnExpectedGenreCount() {
        int expectedCount = 3;

        given(dao.count()).willReturn(expectedCount);
        int actualCount = service.count();

        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("return the expected genre by its id")
    void shouldReturnExpectedGenreById() {
        Genre expectedGenre = new Genre(1L, "genre");

        given(dao.findById(expectedGenre.getId())).willReturn(expectedGenre);
        Genre actualGenre = service.findById(expectedGenre.getId());

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("add a genre")
    void shouldCreateGenre() {
        Long id = 1L;
        String name = "genre";

        given(dao.create(any(Genre.class))).willReturn(id);
        Long actualId = service.create(name);

        assertThat(actualId).isEqualTo(id);
    }


    @Test
    @DisplayName("edit genre")
    void shouldUpdateGenre() {
        Long id = 1L;
        String name = "genre";
        int expectedUpdatedCount = 1;

        given(dao.update(any(Genre.class))).willReturn(expectedUpdatedCount);
        int actualUpdatedCount = service.update(id, name);

        assertThat(actualUpdatedCount).isEqualTo(expectedUpdatedCount);
    }

    @Test
    @DisplayName("delete a given genre by its id")
    void shouldCorrectlyDeleteGenre() {
        Long id = 1L;
        int expectedDeletedCount = 1;

        given(dao.delete(id)).willReturn(expectedDeletedCount);
        int actualDeletedCount = service.delete(id);

        assertThat(actualDeletedCount).isEqualTo(expectedDeletedCount);
    }
}