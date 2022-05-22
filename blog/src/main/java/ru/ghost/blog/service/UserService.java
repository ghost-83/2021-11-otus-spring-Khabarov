package ru.ghost.blog.service;

import ru.ghost.blog.dto.AuthDto;
import ru.ghost.blog.dto.RegistrationDto;
import ru.ghost.blog.dto.UserDto;
import ru.ghost.blog.model.User;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    UserDto authUser(AuthDto authDto, HttpServletResponse response);

    void registrationUser(RegistrationDto registrationDto);

    User findByUsername(String username);

    void removeToken(HttpServletResponse response);
}
