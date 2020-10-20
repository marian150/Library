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
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
public class OperatorRepositoryImpl implements OperatorRepository {

    @Override
    public boolean createReader(SignUpDTO signUpDTO) {
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
            return true;
        } catch (Exception e){
            if(tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> searchReader(Map<String, String> values) {
        HashMap<String, String> values1 = (HashMap<String, String>) values;

        Configuration config = new Configuration();
        config.configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        Transaction tx = session.beginTransaction();

        List<Predicate> predicates = new ArrayList<>();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> u = cq.from(User.class);
        Join<User, UserType> userTypeJoin = u.join("userType");
        cq.select(u);

        if(values1.containsKey("firstName"))
            predicates.add(cb.equal(u.get("firstName"), values1.get("firstName")));
        if(values1.containsKey("lastName"))
            predicates.add(cb.equal(u.get("lastName"), values1.get("lastName")));
        if(values1.containsKey("email"))
            predicates.add(cb.equal(u.get("email"), values1.get("email")));
        if(values1.containsKey("phone"))
            predicates.add(cb.equal(u.get("phone"), values1.get("phone")));
        if(values1.containsKey("fromDate") && values1.containsKey("toDate"))
            predicates.add(cb.between(u.get("regDate"), LocalDate.parse(values1.get("fromDate")), LocalDate.parse(values1.get("toDate"))));

        predicates.add(cb.like(userTypeJoin.get("typeName"), "Reader"));

        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
        cq.where(finalPredicate);

        TypedQuery<User> typedQuery = session.createQuery(cq);

        List<User> result;

        try {
            result = typedQuery.getResultList();
            return result;
        } catch (NoResultException e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return null;
    }
}
