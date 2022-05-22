package ru.ghost.blog.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.blog.exception.LibraryException;
import ru.ghost.blog.model.Author;
import ru.ghost.blog.repository.AuthorRepository;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CachedDataService cachedDataService;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return authorRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand(groupKey = "AuthorService", commandKey = "findAllAuthor", fallbackMethod = "getAuthors")
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new LibraryException("author not found."));
    }

    @Override
    @Transactional
    public Author save(Author author) {
        validate(author);
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        Author author = findById(id);
        authorRepository.delete(author);
    }

    private List<Author> getAuthors() {
        return List.of(cachedDataService.getAuthor());
    }

    private void validate(Author author) {
        if (isEmpty(author.getFirstName())) {
            throw new LibraryException("first name is null or empty.");
        }

        if (isEmpty(author.getLastName())) {
            throw new LibraryException("last name is null or empty.");
        }
    }
}