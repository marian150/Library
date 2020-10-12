package com.lms.repositoriesImpl;

import com.lms.repositories.SignUpRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.Dependent;

@Dependent
public class SignUpRepositoryImpl implements SignUpRepository {
    @Override
    public void signUpReader() {
        Configuration config = new Configuration();
        config.configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.close();

    }
}
