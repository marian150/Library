package com.lms.repositoriesImpl;

import com.lms.config.ConfigurationSessionFactory;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.User;
import com.lms.models.entities.UserType;
import com.lms.repositories.AdminRepository;
import com.lms.security.Password;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.enterprise.context.Dependent;
import java.time.LocalDate;

@Dependent
public class AdminRepositoryImpl implements AdminRepository {
    @Override
    public boolean createOperator(SignUpDTO signUpDTO) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        User user = new User();
        user.setFirstName(signUpDTO.getFirstname());
        user.setLastName(signUpDTO.getLastname());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(Password.hashPassword(signUpDTO.getPassword()));
        user.setPhone(signUpDTO.getPhone());
        user.setRegDate(LocalDate.now());

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UserType userType = (UserType) session.load(UserType.class, 1L);
            user.setUserType(userType);
            session.save(user);
            tx.commit();
            return true;
        } catch (Exception e){
            if(tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }
}
