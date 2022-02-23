package ru.ghost.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.ghost.repository.BookRepository;
import ru.ghost.repository.GenreRepository;

@WebFluxTest
@ContextConfiguration(classes = {GenreController.class})
@DisplayName("The controller for working with genres must")
public class GenreControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GenreRepository repository;

    @Test
    @DisplayName("return a list of genres")
    public void findAllTest() {
        webTestClient.get()
                .uri("/api/v1/genre")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
