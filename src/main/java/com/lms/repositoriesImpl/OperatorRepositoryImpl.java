package com.lms.repositoriesImpl;

import com.lms.config.ConfigurationSessionFactory;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.repositories.OperatorRepository;
import com.lms.security.Password;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.JoinColumn;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
public class OperatorRepositoryImpl implements OperatorRepository {

    @Override
    public boolean createReader(SignUpDTO signUpDTO) {

        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

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

            UserType userType = (UserType) session.load(UserType.class, 2L);
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

        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        List<Predicate> predicates = new ArrayList<>();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> u = cq.from(User.class);
        Join<User, UserType> userTypeJoin = u.join("userType");
        cq.select(u);

        if(values.containsKey("firstName"))
            predicates.add(cb.equal(u.get("firstName"), values.get("firstName")));
        if(values.containsKey("lastName"))
            predicates.add(cb.equal(u.get("lastName"), values.get("lastName")));
        if(values.containsKey("email"))
            predicates.add(cb.equal(u.get("email"), values.get("email")));
        if(values.containsKey("phone"))
            predicates.add(cb.equal(u.get("phone"), values.get("phone")));
        if(values.containsKey("fromDate") && values.containsKey("toDate"))
            predicates.add(cb.between(u.get("regDate"), LocalDate.parse(values.get("fromDate")), LocalDate.parse(values.get("toDate"))));

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

    @Override
    public List<Book> searchBook(Map<String, String> values) {

        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        List<Predicate> predicates = new ArrayList<>();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> b = cq.from(Book.class);
        Join<Book, Publisher> publisherJoin = b.join("publisher");
        Join<Book, Author> authorJoin = b.join("authors");
        Join<Book, Genre> genreJoin = b.join("genre");
        Join<Book, BookState> bookStateJoin = b.join("bookState");
        cq.select(b);


        if(values.containsKey("publisher"))
            predicates.add(cb.like(publisherJoin.get("publisherName"), values.get("publisher")));
        if(values.containsKey("author"))
            predicates.add(cb.like(authorJoin.get("name"), values.get("author")));
        if(values.containsKey("genre"))
            predicates.add(cb.like(genreJoin.get("name"), values.get("genre")));
        if(values.containsKey("bookState"))
            predicates.add(cb.like(bookStateJoin.get("stateName"), values.get("bookState")));
        if(values.containsKey("title"))
            predicates.add(cb.like(b.get("title"), values.get("title")));
        if(values.containsKey("isbn"))
            predicates.add(cb.like(b.get("isbn"), values.get("isbn")));
        if(values.containsKey("bookId"))
            predicates.add(cb.equal(b.get("bookId"), Long.parseLong(values.get("bookId"))));
        if(values.containsKey("issueDate"))
            predicates.add(cb.like(b.get("issueDate"), values.get("issueDate")));


        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
        cq.where(finalPredicate);

        TypedQuery<Book> typedQuery = session.createQuery(cq);

        List<Book> result;

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

    @Override
    public User browseUser(Map<String, String> values) {
        if(values.isEmpty())
            return null;
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        List<Predicate> predicates = new ArrayList<>();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> u = cq.from(User.class);
        Join<User, UserType> userTypeJoin = u.join("userType");
        cq.select(u);

        if(values.containsKey("readerId"))
            predicates.add(cb.equal(u.get("userId"), Long.parseLong(values.get("readerId"))));
        if(values.containsKey("email"))
            predicates.add(cb.like(u.get("email"), values.get("email")));


        predicates.add(cb.like(userTypeJoin.get("typeName"), "Reader"));
        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
        cq.where(finalPredicate);

        TypedQuery<User> typedQuery = session.createQuery(cq);

        User result;

        try {
            result = typedQuery.getSingleResult();
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
