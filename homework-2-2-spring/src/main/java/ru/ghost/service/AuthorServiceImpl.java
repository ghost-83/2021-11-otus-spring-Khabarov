package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.dao.AuthorDao;
import ru.ghost.model.Author;
import ru.ghost.exception.LibraryException;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return dao.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Author create(String firstName, String lastName) {
        Author author = new Author(null, firstName, lastName);
        validate(author);
        return dao.save(author);
    }

    @Override
    @Transactional
    public Author update(Long id, String firstName, String lastName) {
        Author author = new Author(id, firstName, lastName);
        ofNullable(id).orElseThrow(() -> new LibraryException("id is null."));
        validate(author);
        return dao.save(author);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return dao.delete(id);
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