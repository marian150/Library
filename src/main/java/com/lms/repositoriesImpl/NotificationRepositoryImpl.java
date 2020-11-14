package com.lms.repositoriesImpl;

import com.lms.config.ConfigurationSessionFactory;
import com.lms.models.entities.Form;
import com.lms.repositories.NotificationRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.enterprise.context.Dependent;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;


@Dependent
public class NotificationRepositoryImpl implements NotificationRepository {
    @Override
    public List<Form> formTask() {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        Query query = session.createQuery("from Form");

        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Form> forms = query.getResultList();

            for(Form f : forms){
                System.out.println(f.getFormId() + " " + f.getFirstName() + " " + f.getLastName() + " " + f.getEmail() + " " + f.getSubmitDate());
            }

            return forms;

        } catch (NoResultException e){
            System.out.println("No results: " + e.getMessage());
            return null;
        }
        finally {
            session.close();
        }
    }
}
