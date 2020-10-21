package com.lms.repositoriesImpl;

import com.lms.config.ConfigurationSessionFactory;
import com.lms.models.entities.RentBook;
import com.lms.models.entities.User;
import com.lms.repositories.ReaderRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.Dependent;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Dependent
public class ReaderRepositoryImpl implements ReaderRepository {
    @Override
    public List<Object[]> loadBooks() {

        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        // Query for current reader's personal data
        //String hql = "select u.firstName, u.lastName, u.email, u.phone, u.regDate from User u ";

        // Query for the books currently taken by the reader
        String hql = "select b.title, aut.name, pub.publisherName, rb.rentDate, rb.dueDate " +
                    "from RentBook rb \n" +
                    "join rb.client uc \n" +
                    "join rb.book b \n" +
                    "join b.authors aut \n" +
                    "join b.publisher pub \n" +
                    "where uc.firstName like :fname and uc.lastName like :lname";

        Query query = session.createQuery(hql);
        query.setParameter("fname", "Pesho");
        query.setParameter("lname", "Peshev");

        List<Object[]> result;

        try {
            result = query.getResultList();
            return result;
        } catch (NoResultException e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return null;
    }
}
