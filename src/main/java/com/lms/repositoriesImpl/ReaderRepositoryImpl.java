package com.lms.repositoriesImpl;

import com.lms.config.ConfigurationSessionFactory;
import com.lms.models.nonpersistentclasses.ReaderTableView;
import com.lms.repositories.ReaderRepository;
import org.hibernate.Session;

import javax.enterprise.context.Dependent;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Dependent
public class ReaderRepositoryImpl implements ReaderRepository {
    @Override
    public List<Object[]> loadBooks(Long userId) {

        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        // Query for the books currently taken by the reader
        String hql = "select b.title, aut.name, pub.publisherName, rb.rentDate, rb.dueDate " +
                    "from RentBook rb \n" +
                    "join rb.client uc \n" +
                    "join rb.book b \n" +
                    "join b.authors aut \n" +
                    "join b.publisher pub \n" +
                    "where uc.userId like :userId and rb.returnBook is null";

        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);

        List<Object[]> result;

        try {
            result = query.getResultList();
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
