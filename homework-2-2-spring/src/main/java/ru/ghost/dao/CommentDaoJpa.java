package ru.ghost.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.ghost.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class CommentDaoJpa implements CommentDao {

    private final EntityManager em;

    @Override
    public Long count() {
        TypedQuery<Long> query = em.createQuery("select count(id) from Comment", Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c ", Comment.class);
        query.setHint("javax.persistence.fetchgraph", this.em.getEntityGraph("Comment.Book.Author.Genre"));
        return query.getResultList();
    }

    @Override
    public List<Comment> findAllByBookId(Long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.id = :bookId", Comment.class);
        query.setParameter("bookId", bookId);
        query.setHint("javax.persistence.fetchgraph", this.em.getEntityGraph("Comment.Book.Author.Genre"));
        return query.getResultList();
    }

    @Override
    public Comment findById(Long id) {
        Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", em.getEntityGraph("Comment.Book.Author.Genre"));
        return em.find(Comment.class, id, properties);
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public int delete(Long id) {
        Query query = em.createQuery("delete from Comment с where с.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
