package ru.ghost.blog.controller;

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
import ru.ghost.blog.config.SecurityConfig;
import ru.ghost.blog.model.Authority;
import ru.ghost.blog.model.User;
import ru.ghost.blog.security.JwtConfigurer;
import ru.ghost.blog.security.JwtTokenFilter;
import ru.ghost.blog.security.JwtTokenProvider;
import ru.ghost.blog.service.AuthorService;

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

    @Test
    @DisplayName("return a list of authors")
    public void findAllTest() throws Exception {
        Cookie cookie = new Cookie("userToken", "asdlkjadshfklsdfjals");
        cookie.setPath("/");
        cookie.setMaxAge(365 * 24 * 60 * 60);

        UserDetails userDetails = new User(1L, "user", "", "user", "test@test.ru", true, true, true, true, Set.of(new Authority(1L, "ROLE_USER")));

        given(jwtTokenProvider.getUsername("asdlkjadshfklsdfjals")).willReturn("user");
        given(userDetailsService.loadUserByUsername("user")).willReturn(userDetails);

        mockMvc.perform(get("/api/v1/author").cookie(cookie)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("fail a list of authors")
    public void findAllFailTest() throws Exception {
        mockMvc.perform(get("/api/v1/author")).andExpect(status().isForbidden());
    }
}
