package ru.ghost.blog.service;

import lombok.NonNull;
import ru.ghost.blog.dto.ReferenceBookDto;
import ru.ghost.blog.model.ReferenceBook;

import java.util.List;

public interface ReferenceBookService {

    Long count();

    List<ReferenceBook> findAll();

    List<ReferenceBook> findAllByGenre(@NonNull String genre);

    List<ReferenceBook> search(@NonNull String search);

    ReferenceBook findById(Long id);

    ReferenceBook save(ReferenceBookDto referenceBookDto);

    void delete(Long id);
}