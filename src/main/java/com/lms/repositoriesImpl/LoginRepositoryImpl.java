package com.lms.repositoriesImpl;

import com.lms.models.dtos.LoginDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.Form;
import com.lms.models.entities.FormStatus;
import com.lms.models.entities.User;
import com.lms.repositories.LoginRepository;
import com.lms.security.Password;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.Dependent;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.LocalDate;

@Dependent
public class LoginRepositoryImpl implements LoginRepository {

    public User findByEmailAndPass(LoginDTO loginDTO) {

        Configuration config = new Configuration();
        config.configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String hql = "FROM User u WHERE u.email LIKE '" + loginDTO.getEmail() + "' AND u.password" +
                " = '" + Password.hashPassword(loginDTO.getPassword()) + "'";
        Query query = session.createQuery(hql);
        User result = null;

        try {
            result = (User) query.getSingleResult();
            session.close();
            return result;
        } catch (Exception e) {
            session.close();
        }

        return null;
    }

    @Override
    public void persistNewReaderForm(SignUpDTO signUpDTO) {
        Configuration config = new Configuration();
        config.configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Form form = new Form();
        form.setFirstName(signUpDTO.getFirstname());
        form.setLastName(signUpDTO.getLastname());
        form.setEmail(signUpDTO.getEmail());
        form.setPassword(signUpDTO.getPassword());
        form.setPhone(signUpDTO.getPhone());
        form.setSubmitDate(LocalDate.now());

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            FormStatus formStatus = (FormStatus) session.load(FormStatus.class, (Long)(long)1);
            form.setFormStatus(formStatus);
            System.out.println(formStatus.getStatusId() + " "+ formStatus.getStatusName());
            session.save(form);
            tx.commit();
        } catch (Exception e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
