package ru.ghost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ghost.entity.mongo.Author;
import ru.ghost.entity.relationalstorage.RS_Author;
import ru.ghost.repository.relationalstorage.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public RS_Author convert(final Author author) {
        final RS_Author rs_author = RS_Author.builder()
                .name(author.getName())
                .mongoId(author.getId())
                .build();
        return authorRepository.save(rs_author);
    }

    @Override
    public RS_Author findByMongoId(String mongoId) {
        return authorRepository.findByMongoId(mongoId);
    }
}
