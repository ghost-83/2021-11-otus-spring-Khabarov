package ru.ghost.service;

import ru.ghost.entity.mongo.Genre;
import ru.ghost.entity.relationalstorage.RS_Genre;

public interface GenreService {

    RS_Genre convert(Genre genre);

    RS_Genre findByMongoId(String id);
}
