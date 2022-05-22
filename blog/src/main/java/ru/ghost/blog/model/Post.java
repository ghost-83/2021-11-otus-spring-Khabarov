package ru.ghost.blog.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@NamedEntityGraph(name = "Post.Author.Genre", attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "image", nullable = false)
    private String image;

    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Author author;

    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Genre genre;
}