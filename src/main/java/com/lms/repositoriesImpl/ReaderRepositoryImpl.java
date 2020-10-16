package com.lms.repositoriesImpl;

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

        Configuration config = new Configuration();
        config.configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String hql = "select uc.firstName, ul.firstName, b.title, aut.name, pub.publisherName, gen.name, bc.coverName, bs.stateName\n" +
                "from RentBook rb \n" +
                "join rb.client uc \n" +
                "join rb.librarian ul \n" +
                "join rb.book b \n" +
                "join " +
                "join b.authors aut \n" +
                "join b.publisher pub \n" +
                "join b.genre gen \n" +
                "join b.bookCovers bc \n" +
                "join b.bookState bs \n" +
                "where CONCAT(CONCAT(uc.firstName, ' '), uc.lastName) like 'Pesho Peshev'";
        Query query = session.createQuery(hql);

        List<Object[]> result;

        try {
            result = query.getResultList();
            session.close();
            return result;
        } catch (NoResultException e){}

        return null;
    }
}
