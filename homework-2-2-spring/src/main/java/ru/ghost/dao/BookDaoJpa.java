package ru.ghost.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.ghost.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJpa implements BookDao {

    private final EntityManager em;

    @Override
    public Long count() {
        TypedQuery<Long> query = em.createQuery("select count(id) from Book", Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                "join fetch b.author " +
                "join fetch b.genre ", Book.class);
        return query.getResultList();
    }

    @Override
    public List<Book> findAllByAuthorId(Long id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.author.id = :id", Book.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.fetchgraph", this.em.getEntityGraph("Book.Author.Genre"));

        return query.getResultList();
    }

    @Override
    public List<Book> findAllByGenreId(Long id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.genre.id = :id", Book.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.fetchgraph", this.em.getEntityGraph("Book.Author.Genre"));

        return query.getResultList();
    }

    @Override
    public Book findById(Long id) {
        Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", em.getEntityGraph("Book.Author.Genre"));
        return em.find(Book.class, id, properties);
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public int delete(Long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }

}