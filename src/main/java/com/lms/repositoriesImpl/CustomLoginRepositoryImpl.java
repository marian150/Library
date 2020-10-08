package com.lms.repositoriesImpl;

import com.lms.models.dtos.LoginDTO;
import com.lms.models.entities.User;
import com.lms.repositories.CustomLoginRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class CustomLoginRepositoryImpl implements CustomLoginRepository {

    EntityManagerFactory emfactory;

    public User findByEmailAndPass(LoginDTO loginDTO) {
        EntityManager manager = emfactory.createEntityManager();
        manager.getTransaction().begin();

        String hql = "FROM User u WHERE u.email LIKE '" + loginDTO.getEmail() + "' AND u.password" +
                "='" + loginDTO.getPassword() + "'";
        Query query = manager.createQuery(hql);
        User result = (User) query.getSingleResult();

        return result;
    }
}
