package ru.ghost.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ru.ghost.blog.config.SecurityConfig;
import ru.ghost.blog.dto.PostDto;
import ru.ghost.blog.model.Authority;
import ru.ghost.blog.model.User;
import ru.ghost.blog.security.JwtConfigurer;
import ru.ghost.blog.security.JwtTokenFilter;
import ru.ghost.blog.security.JwtTokenProvider;
import ru.ghost.blog.service.ReferenceBookService;

import javax.servlet.http.Cookie;
import java.util.Set;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReferenceBookController.class)
@Import(ReferenceBookController.class)
@ContextConfiguration(classes = {JwtConfigurer.class, JwtTokenFilter.class, SecurityConfig.class})
@DisplayName("The controller for working with reference books must")
public class ReferenceBookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReferenceBookService referenceBookService;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    public void setup() {

        UserDetails userDetails = new User(1L, "user", "", "user", "test@test.ru", true, true, true, true, Set.of(new Authority(1L, "ROLE_USER")));

        given(jwtTokenProvider.getUsername("asdlkjadshfklsdfjals")).willReturn("user");
        given(userDetailsService.loadUserByUsername("user")).willReturn(userDetails);
    }

    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("Test ok")
    public void allTest(RequestBuilder data) throws Exception {
        mockMvc.perform(data).andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("generateDataFail")
    @DisplayName("Test fail")
    public void allFailTest(RequestBuilder data) throws Exception {
        mockMvc.perform(data).andExpect(status().isForbidden());
    }

    private static Stream<Arguments> generateData() throws Exception {

        Cookie cookie = new Cookie("userToken", "asdlkjadshfklsdfjals");
        cookie.setPath("/");
        cookie.setMaxAge(365 * 24 * 60 * 60);

        ObjectMapper mapper = new ObjectMapper();

        return Stream.of(
                Arguments.of(get("/api/v1/book").cookie(cookie)),
                Arguments.of(post("/api/v1/book")
                        .cookie(cookie)
                        .content(mapper.writeValueAsBytes(new PostDto()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)),
                Arguments.of(delete("/api/v1/book/1").cookie(cookie)));
    }

    private static Stream<Arguments> generateDataFail() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        return Stream.of(
                Arguments.of(get("/api/v1/book")),
                Arguments.of(post("/api/v1/book")
                        .content(mapper.writeValueAsBytes(new PostDto()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)),
                Arguments.of(delete("/api/v1/book/1")));
    }
}
