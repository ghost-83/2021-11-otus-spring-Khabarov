package ru.ghost.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ghost.model.Genre;
import ru.ghost.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Class GenreServiceImpl")
@SpringBootTest(classes = GenreServiceImpl.class)
class GenreServiceImplTest {

    private final Genre EXPECTED_GENRE = new Genre("1L", "genre");

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private BookService bookService;

    @Autowired
    private GenreServiceImpl service;

    @Test
    @DisplayName("return the expected list of genres")
    void shouldReturnExpectedGenresList() {
        Genre genre = new Genre("1L", "new genre");

        given(genreRepository.findAll()).willReturn(List.of(genre));
        List<Genre> actualList = service.findAll();

        Assertions.assertThat(actualList).usingRecursiveFieldByFieldElementComparator().containsExactly(genre);
    }

    @Test
    @DisplayName("return the expected number of genres")
    void shouldReturnExpectedGenreCount() {
        Long expectedCount = 3L;

        given(genreRepository.count()).willReturn(expectedCount);
        Long actualCount = service.count();

        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("return the expected genre by its id")
    void shouldReturnExpectedGenreById() {
        given(genreRepository.findById(EXPECTED_GENRE.getId())).willReturn(Optional.of(EXPECTED_GENRE));
        Genre actualGenre = service.findById(EXPECTED_GENRE.getId());

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(EXPECTED_GENRE);
    }

    @Test
    @DisplayName("add a genre")
    void shouldCreateGenre() {
        given(genreRepository.save(any(Genre.class))).willReturn(EXPECTED_GENRE);
        Genre genreCreate = service.create("test");

        assertThat(genreCreate).isEqualTo(EXPECTED_GENRE);
    }


    @Test
    @DisplayName("edit genre")
    void shouldUpdateGenre() {
        given(genreRepository.save(any(Genre.class))).willReturn(EXPECTED_GENRE);
        given(genreRepository.findById(EXPECTED_GENRE.getId())).willReturn(Optional.of(EXPECTED_GENRE));
        Genre genre = service.update(EXPECTED_GENRE.getId(), EXPECTED_GENRE.getName());

        assertThat(genre).isEqualTo(EXPECTED_GENRE);
    }

    @Test
    @DisplayName("delete a given genre by its id")
    void shouldCorrectlyDeleteGenre() {

        given(genreRepository.findById(EXPECTED_GENRE.getId())).willReturn(Optional.of(EXPECTED_GENRE));

        service.delete("1L");

        verify(genreRepository, times(1)).deleteById(EXPECTED_GENRE.getId());
    }
}