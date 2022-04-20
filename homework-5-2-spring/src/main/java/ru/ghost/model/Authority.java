package ru.ghost.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authority")
public class Authority implements GrantedAuthority {
    @Id
    private Long id;
    private String authority;
}
