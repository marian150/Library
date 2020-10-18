package com.lms.repositoriesImpl;

import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.User;
import com.lms.models.entities.UserType;
import com.lms.repositories.OperatorRepository;
import com.lms.security.Password;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.Dependent;
import java.time.LocalDate;

@Dependent
public class OperatorRepositoryImpl implements OperatorRepository {

    @Override
    public void createReader(SignUpDTO signUpDTO) {
        Configuration config = new Configuration();
        config.configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        User user = new User();

        try {
            user.setFirstName(signUpDTO.getFirstname());
            user.setLastName(signUpDTO.getLastname());
            user.setEmail(signUpDTO.getEmail());
            user.setPassword(Password.hashPassword(signUpDTO.getPassword()));
            user.setPhone(signUpDTO.getPhone());
            user.setRegDate(LocalDate.now());
        } catch (Exception e) {}

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            UserType userType = (UserType) session.load(UserType.class, (Long)(long)2);
            user.setUserType(userType);
            System.out.println(userType.getTypeId() + " "+ userType.getTypeName());
            session.save(user);
            tx.commit();
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
