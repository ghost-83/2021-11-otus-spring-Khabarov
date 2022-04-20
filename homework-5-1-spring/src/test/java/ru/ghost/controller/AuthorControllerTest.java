package ru.ghost.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.ghost.model.Authority;
import ru.ghost.model.User;
import ru.ghost.security.JwtConfigurer;
import ru.ghost.security.JwtTokenFilter;
import ru.ghost.security.JwtTokenProvider;
import ru.ghost.security.SecurityConfig;
import ru.ghost.service.AuthorService;

import javax.servlet.http.Cookie;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthorController.class)
@Import(AuthorController.class)
@ContextConfiguration(classes = {JwtConfigurer.class, JwtTokenFilter.class, SecurityConfig.class})
@DisplayName("The controller for working with authors must")
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private Cookie cookie;

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
    @DisplayName("return a list of authors")
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/api/v1/author").cookie(cookie)).andExpect(status().isOk());
        mockMvc.perform(get("/api/v1/author")).andExpect(status().isForbidden());
    }
}
