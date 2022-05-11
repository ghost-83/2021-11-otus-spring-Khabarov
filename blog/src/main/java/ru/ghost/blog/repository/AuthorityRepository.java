package ru.ghost.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ghost.blog.model.Authority;
import ru.ghost.blog.model.User;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByAuthority(String authority);
}