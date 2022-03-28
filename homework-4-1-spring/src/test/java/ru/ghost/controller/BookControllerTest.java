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
import ru.ghost.model.Book;
import ru.ghost.repository.BookRepository;
import ru.ghost.repository.CommentRepository;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@WebFluxTest
@ContextConfiguration(classes = {BookController.class})
@DisplayName("The controller for working with books must")
public class BookControllerTest {
    private static final String ID = ObjectId.get().toString();

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BookRepository repository;

    @MockBean
    private CommentRepository commentRepository;

    @Test
    @DisplayName("return a book by id")
    public void findOneTest() {
        webTestClient.get()
                .uri("/api/v1/book/" + ID)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("return a list of books")
    public void findAllTest() {
        webTestClient.get()
                .uri("/api/v1/book")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @DisplayName("saving a book")
    @Test
    public void saveTest() {
        webTestClient.post()
                .uri("/api/v1/book")
                .bodyValue(new Book())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @DisplayName("delete a book")
    @Test
    public void deleteTest() {
        given(repository.deleteById(anyString())).willReturn(Mono.empty());
        given(commentRepository.deleteByBookId(anyString())).willReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/v1/book/" + ID)
                .exchange()
                .expectStatus()
                .isOk();
    }
}
