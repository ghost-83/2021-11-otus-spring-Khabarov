package ru.ghost.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.ghost.model.Comment;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

    Flux<Comment> findAllByBookId(String id);
}
