package com.lms.repositoriesImpl;

import com.lms.config.ConfigurationSessionFactory;
import com.lms.models.entities.Notifications;
import com.lms.models.entities.RentBook;
import com.lms.models.entities.Status;
import com.lms.models.entities.User;
import com.lms.repositories.NotificationRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;


@Dependent
public class NotificationRepositoryImpl implements NotificationRepository {
    @Override
    public void checkForOverdue() {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        Transaction tx = null;

        LocalDate currentDate = LocalDate.now();

        try {
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<RentBook> cq = cb.createQuery(RentBook.class);
            Root<RentBook> rentBookRoot = cq.from(RentBook.class);

            Predicate checkDate = cb.lessThan(rentBookRoot.get("dueDate"), cb.currentDate());

            cq.where(checkDate);

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
}
