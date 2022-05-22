package ru.ghost.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.blog.dto.AuthDto;
import ru.ghost.blog.dto.RegistrationDto;
import ru.ghost.blog.dto.UserDto;
import ru.ghost.blog.exception.LibraryException;
import ru.ghost.blog.model.User;
import ru.ghost.blog.repository.AuthorityRepository;
import ru.ghost.blog.repository.UserRepository;
import ru.ghost.blog.security.JwtTokenProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Set;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthorityRepository authorityRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

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
    public void registrationUser(RegistrationDto registrationDto) {

        validate(registrationDto);

        if (Objects.nonNull(userRepository.findByUsername(registrationDto.getUsername()).orElse(null)))
            throw new LibraryException("Username is already taken.");

        userRepository.save(User.builder()
                        .username(registrationDto.getUsername())
                        .password(passwordEncoder.encode(registrationDto.getPassword()))
                        .fullName(registrationDto.getFullName())
                        .email(registrationDto.getEmail())
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .enabled(true)
                        .authorities(Set.of(authorityRepository.findByAuthority("ROLE_USER")))
                .build());
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

    private void validate(RegistrationDto registrationDto) {
        if (isEmpty(registrationDto.getUsername())) {
            throw new LibraryException("Username is null or empty.");
        }

        if (isEmpty(registrationDto.getPassword())) {
            throw new LibraryException("Password is null or empty.");
        }

        if (isEmpty(registrationDto.getEmail())) {
            throw new LibraryException("Email is null or empty.");
        }

        if (isEmpty(registrationDto.getFullName())) {
            throw new LibraryException("FullName is null or empty.");
        }
    }
}
