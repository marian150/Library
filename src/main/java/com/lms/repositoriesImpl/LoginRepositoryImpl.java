package com.lms.repositoriesImpl;

import com.lms.models.dtos.LoginDTO;
import com.lms.models.entities.User;
import com.lms.repositories.LoginRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Dependent
public class LoginRepositoryImpl implements LoginRepository {

    public User findByEmailAndPass(LoginDTO loginDTO) {

        Configuration config = new Configuration();
        config.configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM User u WHERE u.email LIKE '" + loginDTO.getEmail() + "' AND u.password" +
                "='" + loginDTO.getPassword() + "'";
        Query query = session.createQuery(hql);
        User result = (User) query.getSingleResult();

        return result;
    }
}
