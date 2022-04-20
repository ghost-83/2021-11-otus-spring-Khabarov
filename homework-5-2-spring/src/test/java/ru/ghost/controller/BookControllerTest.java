package ru.ghost.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.ghost.dto.BookDto;
import ru.ghost.model.Authority;
import ru.ghost.model.User;
import ru.ghost.security.JwtConfigurer;
import ru.ghost.security.JwtTokenFilter;
import ru.ghost.security.JwtTokenProvider;
import ru.ghost.security.SecurityConfig;
import ru.ghost.service.BookService;

import javax.servlet.http.Cookie;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@Import(BookController.class)
@ContextConfiguration(classes = {JwtConfigurer.class, JwtTokenFilter.class, SecurityConfig.class})
@DisplayName("The controller for working with books must")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private Cookie cookie;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        cookie = new Cookie("userToken", "asdlkjadshfklsdfjals");
        cookie.setPath("/");
        cookie.setMaxAge(365 * 24 * 60 * 60);

        UserDetails userDetails = new User(1L, "user", "", "user", "test@test.ru", true, true, true, true, Set.of(new Authority(1L, "ROLE_USER")));

        given(jwtTokenProvider.getUsername("asdlkjadshfklsdfjals")).willReturn("user");
        given(userDetailsService.loadUserByUsername("user")).willReturn(userDetails);
    }

    @Test
    @DisplayName("return a book by id")
    public void findOneTest() throws Exception {
        given(bookService.findById(1L)).willReturn(null);
        mockMvc.perform(get("/api/v1/book/1").cookie(cookie)).andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/book")).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("return a list of books")
    public void findAllTest() throws Exception {
        given(bookService.findAll()).willReturn(null);
        mockMvc.perform(get("/api/v1/book").cookie(cookie)).andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/book")).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("saving a book")
    public void saveTest() throws Exception {
        given(bookService.save(any())).willReturn(null);
        mockMvc.perform(post("/api/v1/book")
                .cookie(cookie)
                .content(mapper.writeValueAsBytes(new BookDto()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
        mockMvc.perform(post("/api/v1/book")).andExpect(status().isForbidden());
    }

    @DisplayName("delete a book")
    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/v1/book/1").cookie(cookie)).andExpect(status().isOk());
        mockMvc.perform(delete("/api/v1/book/1")).andExpect(status().isForbidden());
    }
}
