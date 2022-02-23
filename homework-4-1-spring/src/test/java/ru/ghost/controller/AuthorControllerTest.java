package ru.ghost.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.ghost.repository.AuthorRepository;

@WebFluxTest
@ContextConfiguration(classes = {AuthorController.class})
@DisplayName("The controller for working with authors must")
public class AuthorControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AuthorRepository repository;

    @Test
    @DisplayName("return a list of authors")
    public void findAllTest() {
        webTestClient.get()
                .uri("/api/v1/author")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
