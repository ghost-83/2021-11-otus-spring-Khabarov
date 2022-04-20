package ru.ghost.service;

import ru.ghost.dto.AuthDto;
import ru.ghost.dto.UserDto;
import ru.ghost.model.User;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    UserDto authUser(AuthDto authDto, HttpServletResponse response);

    User findByUsername(String username);

    void removeToken(HttpServletResponse response);
}
