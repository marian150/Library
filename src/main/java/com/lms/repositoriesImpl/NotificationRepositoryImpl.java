package com.lms.repositoriesImpl;

import com.lms.config.ConfigurationSessionFactory;
import com.lms.models.entities.*;
import com.lms.repositories.NotificationRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.enterprise.context.Dependent;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Dependent
public class NotificationRepositoryImpl implements NotificationRepository {
    @Override
    public void checkForOverdue() {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        Transaction tx = null;

        List<Predicate> predicates = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<RentBook> cq = cb.createQuery(RentBook.class);
            Root<RentBook> rentBookRoot = cq.from(RentBook.class);

            predicates.add(cb.lessThan(rentBookRoot.get("dueDate"), cb.currentDate()));
            predicates.add(cb.isNull(rentBookRoot.get("returnBook")));

            Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
            cq.where(finalPredicate);

            List<RentBook> overdueList = session.createQuery(cq).getResultList();

            for (RentBook rb : overdueList) {
                Notifications notification = new Notifications();
                notification.setRentBook(rb);

                Query queryL = session.createQuery("Select s from Status s where s.statusId = 1");
                Status status = (Status) queryL.getSingleResult();
                notification.setStatus(status);

                User user = rb.getClient();
                user.setRating(user.getRating() - 3);
                session.update(user);

                session.save(notification);
            }
            tx.commit();
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    @Override
    public void checkBooksToBeArchived() {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        List<Predicate> predicates = new ArrayList<>();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            NativeQuery<BigDecimal> query = session.createSQLQuery(
                    "SELECT rb.book_id FROM book b \n" +
                            "JOIN rent_book rb \n" +
                            "ON rb.book_id = b.book_id \n" +
                            "WHERE b.state_id = 1" +
                            "GROUP BY rb.book_id, TO_NUMBER(b.issue_date) \n" +
                            "HAVING -0.8*(2020-TO_NUMBER(b.issue_date))+60 < COUNT(rb.book_id)");

            List<BigDecimal> ids = query.getResultList();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> cq = cb.createQuery(Book.class);
            Root<Book> root = cq.from(Book.class);

            root.join("authors", JoinType.LEFT);

            cq.select(root).where(root.in(ids)).distinct(true);
            List<Book> result = session.createQuery(cq).getResultList();
            for (Book rb : result) {
                Notifications notification = new Notifications();
                notification.setBook(rb);

                Status status = session.load(Status.class, 1L);
                notification.setStatus(status);

                session.save(notification);
            }
            tx.commit();
        } catch (NoResultException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            return;
        } finally {
            session.close();
        }
    }
}
