package ru.ghost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.ghost.dto.AuthDto;
import ru.ghost.service.UserService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthDto authDto, HttpServletResponse response) {
        try {
            return ResponseEntity.ok(userService.authUser(authDto, response));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid login/password combination!");
        }
    }

    @DeleteMapping(value = "/logout")
    public void removeToken(HttpServletResponse response) {
        userService.removeToken(response);
    }
}
