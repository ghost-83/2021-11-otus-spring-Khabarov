package ru.ghost.controller;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import ru.ghost.model.Comment;
import ru.ghost.repository.CommentRepository;

import static java.lang.String.format;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@WebFluxTest
@ContextConfiguration(classes = {CommentController.class})
@DisplayName("To work with comments, the controller must")
public class CommentControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CommentRepository repository;

    @Test
    @DisplayName("return a list of comments for a book")
    public void findByBookIdTest() {
        webTestClient.get()
                .uri(format("/api/v1/comment/book/%s", ObjectId.get().toString()))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("add a comment")
    public void saveTest() {
        webTestClient.post()
                .uri("/api/v1/comment/")
                .bodyValue(new Comment())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("delete a comment")
    public void deleteTest() {
        given(repository.deleteById(anyString())).willReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/v1/comment/" + ObjectId.get())
                .exchange()
                .expectStatus()
                .isOk();
    }
}
