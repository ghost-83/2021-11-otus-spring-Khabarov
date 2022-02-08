package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ghost.exception.LibraryException;
import ru.ghost.model.Author;
import ru.ghost.model.Book;
import ru.ghost.repository.AuthorRepository;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final BookService bookService;

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
    public Author findById(String id) {
        return authorRepository.findById(id).orElseThrow(() -> new LibraryException("author not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public Author create(String firstName, String lastName) {
        Author author = new Author(null, firstName, lastName);
        validate(author);
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Author update(String id, String firstName, String lastName) {
        ofNullable(id).orElseThrow(() -> new LibraryException("id is null."));
        Author author = this.findById(id);

        List<Book> books = bookService.findAllByAuthor(author);

        author.setFirstName(firstName);
        author.setLastName(lastName);
        validate(author);

        if (books.size() > 0) {
            books.forEach(book -> book.setAuthor(author));
            bookService.updateAll(books);
        }

        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public void delete(String id) {
        authorRepository.deleteById(id);
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