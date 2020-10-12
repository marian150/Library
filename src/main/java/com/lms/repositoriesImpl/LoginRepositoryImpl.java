package com.lms.repositoriesImpl;

import com.lms.models.dtos.LoginDTO;
import com.lms.models.entities.User;
import com.lms.repositories.LoginRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.Dependent;
import javax.persistence.Query;

@Dependent
public class LoginRepositoryImpl implements LoginRepository {

    public User findByEmailAndPass(LoginDTO loginDTO) {

        Configuration config = new Configuration();
        config.configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String hql = "FROM User u WHERE u.email LIKE '" + loginDTO.getEmail() + "' AND u.password" +
                "='" + loginDTO.getPassword() + "'";
        Query query = session.createQuery(hql);
        User result = (User) query.getSingleResult();
        session.close();
        return result;
    }
}
