package com.lms.repositoriesImpl;

import com.lms.config.ConfigurationSessionFactory;
import com.lms.models.entities.Book;
import com.lms.models.entities.RentBook;
import com.lms.repositories.ReaderRepository;
import org.hibernate.Session;
import javax.enterprise.context.Dependent;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class ReaderRepositoryImpl implements ReaderRepository {
    @Override
    public List<RentBook> loadBooks(Long userId) {

        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        List<Predicate> predicates = new ArrayList<>();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<RentBook> cq = cb.createQuery(RentBook.class);
        Root<RentBook> rb = cq.from(RentBook.class);
        rb.fetch("client", JoinType.LEFT);

        Fetch<RentBook, Book> rentBookBookFetch = rb.fetch("book", JoinType.LEFT);
        rentBookBookFetch.fetch("authors", JoinType.LEFT);
        rentBookBookFetch.fetch("publisher", JoinType.LEFT);

        cq.select(rb).distinct(true);

        predicates.add(cb.isNull(rb.get("returnBook")));
        predicates.add(cb.equal(rb.get("client"), userId));
        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
        cq.where(finalPredicate);
        TypedQuery<RentBook> typedQuery = session.createQuery(cq);

        List<RentBook> result = typedQuery.getResultList();
/*
        // Query for the books currently taken by the reader
        String hql = "select distinct b.title, aut.name, pub.publisherName, rb.rentDate, rb.dueDate " +
                    "from RentBook rb \n" +
                    "join rb.client uc \n" +
                    "join rb.book b \n" +
                    "join b.authors aut \n" +
                    "join b.publisher pub \n" +
                    "where uc.userId like :userId and rb.returnBook is null";

        List<Object[]> result;
*/
        try {
            result = typedQuery.getResultList();
            return result;
        } catch (NoResultException e){
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }

    }
}
