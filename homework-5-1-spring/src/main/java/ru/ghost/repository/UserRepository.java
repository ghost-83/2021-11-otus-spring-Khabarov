package ru.ghost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ghost.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
}