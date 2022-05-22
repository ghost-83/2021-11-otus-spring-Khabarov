package ru.ghost.blog.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.blog.exception.LibraryException;
import ru.ghost.blog.model.Genre;
import ru.ghost.blog.model.Post;
import ru.ghost.blog.repository.PostRepository;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final GenreService genreService;
    private final CachedDataService cachedDataService;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return postRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand(groupKey = "PostService", commandKey = "findAllPost", fallbackMethod = "getPosts")
    public List<Post> findAll() {
        // Добавлено специально для проверки работы Hystrix
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAllByGenre(@NonNull String genre) {
        Genre genreDao = genreService.findByName(genre);
        if (Objects.isNull(genreDao))
            throw new LibraryException("Genre not found.");
        return postRepository.findAllByGenre(genreDao);
    }

    @Override
    public List<Post> search(@NonNull String search) {
        return postRepository.findAllByTitleContainingIgnoreCase(search);
    }

    private List<Post> getPosts() {
        return List.of(cachedDataService.getPost());
    }
}