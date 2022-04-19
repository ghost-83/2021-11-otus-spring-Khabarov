package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.dto.AuthDto;
import ru.ghost.dto.UserDto;
import ru.ghost.model.User;
import ru.ghost.repository.UserRepository;
import ru.ghost.security.JwtTokenProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDto authUser(AuthDto authDto, HttpServletResponse response) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
        User user = this.findByUsername(authDto.getUsername());
        String token = jwtTokenProvider.getJwtToken(user);
        Cookie cookie = new Cookie("userToken", token);
        cookie.setPath("/");
        cookie.setMaxAge(365 * 24 * 60 * 60);
        response.addCookie(cookie);

        return UserDto
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .authorities(user.getAuthorities())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User is not found!"));
    }

    @Override
    public void removeToken(HttpServletResponse response) {
        Cookie cookie = new Cookie("userToken", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
