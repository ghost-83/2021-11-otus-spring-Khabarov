package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ghost.dao.AuthorDao;
import ru.ghost.domain.Author;
import ru.ghost.exception.LibraryException;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public List<Author> findAll() {
        return dao.findAll();
    }

    @Override
    public Author findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Long create(String firstName, String lastName) {
        Author author = new Author(null, firstName, lastName);
        validate(author);
        return dao.create(author);
    }

    @Override
    public int update(Long id, String firstName, String lastName) {
        Author author = new Author(id, firstName, lastName);
        ofNullable(id).orElseThrow(() -> new LibraryException("id is null."));
        validate(author);
        return dao.update(author);
    }

    @Override
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