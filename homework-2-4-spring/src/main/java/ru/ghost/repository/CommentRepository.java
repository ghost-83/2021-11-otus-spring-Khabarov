package ru.ghost.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.ghost.model.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}
