package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.exception.LibraryException;
import ru.ghost.model.Author;
import ru.ghost.repository.AuthorRepository;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return authorRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
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
    public void delete(Long id) {
        Author author = findById(id);
        authorRepository.delete(author);
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