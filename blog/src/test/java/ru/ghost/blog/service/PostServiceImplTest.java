package ru.ghost.blog.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ghost.blog.model.Author;
import ru.ghost.blog.model.Genre;
import ru.ghost.blog.model.Post;
import ru.ghost.blog.repository.PostRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Class BookServiceImpl")
@SpringBootTest(classes = PostServiceImpl.class)
class PostServiceImplTest {

    private final Author AUTHOR = new Author(1L, "testFirstName", "testLastName");
    private final Genre GENRE = new Genre(1L, "testGenre");
    private final Post EXPECTED_Post = new Post(1L, "testPost", "text", "test.img", AUTHOR, GENRE);

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private GenreService genreService;

    @Autowired
    private PostServiceImpl service;

    @Test
    @DisplayName("return the expected number of books")
    void shouldReturnExpectedBookCount() {
        Long expectedCount = 3L;

        given(postRepository.count()).willReturn(expectedCount);

        Long actualCount = service.count();
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("return the expected list of books")
    void shouldReturnExpectedBooksList() {
        given(postRepository.findAll()).willReturn(List.of(EXPECTED_Post));

        List<Post> actualList = service.findAll();
        Assertions.assertThat(actualList).usingRecursiveFieldByFieldElementComparator().containsExactly(EXPECTED_Post);
    }
}