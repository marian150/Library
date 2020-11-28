package com.lms.repositoriesImpl;

import com.lms.config.ConfigurationSessionFactory;
import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.LoadFormsModel;
import com.lms.repositories.CommonAdminOperatorRepository;
import com.lms.repositories.OperatorRepository;
import com.lms.security.Password;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Dependent
public class OperatorRepositoryImpl implements OperatorRepository {

    @Inject
    private CommonAdminOperatorRepository commonAdminOperatorRepository;

    Logger logger = Logger.getLogger(OperatorRepositoryImpl.class);

    @Override
    public boolean createReader(SignUpDTO signUpDTO) {
        return commonAdminOperatorRepository.createReader(signUpDTO);
    }

    @Override
    public List<User> searchUsers(Map<String, String> values) {

        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        List<Predicate> predicates = new ArrayList<>();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> u = cq.from(User.class);
        Join<User, UserType> userTypeJoin = u.join("userType");
        cq.select(u);

        if(values.containsKey("id"))
            predicates.add(cb.equal(u.get("userId"), values.get("id")));
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
            return null;
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<Book> searchBook(Map<String, String> values) {
        return commonAdminOperatorRepository.searchBook(values);
    }

    @Override
    public boolean addBook(AddBookDTO addBookDTO) {
        return commonAdminOperatorRepository.addBook(addBookDTO);
    }

    @Override
    public List<BookCovers> retrieveBookCovers() {
        return commonAdminOperatorRepository.retrieveBookCovers();
    }

    @Override
    public List<Genre> retrieveBookGenre() {
        return commonAdminOperatorRepository.retrieveBookGenre();
    }

    @Override
    public List<BookState> retrieveBookState() {
        return commonAdminOperatorRepository.retrieveBookState();
    }

    @Override
    public boolean addPublisher(String publisherName) {
        return commonAdminOperatorRepository.addPublisher(publisherName);
    }

    @Override
    public boolean searchPublisher(String publisherName) {
        return commonAdminOperatorRepository.searchPublisher(publisherName);
    }

    @Override
    public boolean searchAuthor(String author) {
        return commonAdminOperatorRepository.searchAuthor(author);
    }

    @Override
    public boolean addAuthor(String author) {
        return commonAdminOperatorRepository.addAuthor(author);
    }

    @Override
    public boolean scrapBook(Long id) {
        return commonAdminOperatorRepository.scrapBook(id);
    }

    @Override
    public List<RentBook> findLentBooks(Map<String, String> values) {
        return commonAdminOperatorRepository.findLentBooks(values);
    }

    @Override
    public boolean returnBooks(ReturnBookDTO books) {
        return commonAdminOperatorRepository.returnBooks(books);
    }

    @Override
    public LocalDate extendDueDate(Long id) {
        return commonAdminOperatorRepository.extendDueDate(id);
    }

    @Override
    public List<LoadFormsModel> loadForms() {
        return commonAdminOperatorRepository.loadForms();
    }
    @Override
    public List<LoadFormsModel> loadNewForms() {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<Predicate> predicates = new ArrayList<>();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<LoadFormsModel> cq = cb.createQuery(LoadFormsModel.class);
            Root<Notifications> notificationsRoot = cq.from(Notifications.class);

            notificationsRoot.join("form");
            notificationsRoot.join("status");

            predicates.add(cb.like(notificationsRoot.get("status").get("statusName"), "New"));
            predicates.add(cb.isNotNull(notificationsRoot.get("form")));

            Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));

            cq.multiselect(notificationsRoot.get("form").get("firstName"), notificationsRoot.get("form").get("lastName"),
                    notificationsRoot.get("form").get("email"), notificationsRoot.get("form").get("phone"),
                    notificationsRoot.get("form").get("submitDate"), notificationsRoot.get("status").get("statusName"));

            cq.where(finalPredicate);

            List<LoadFormsModel> notifications = session.createQuery(cq).getResultList();

            return notifications;
        } catch (NoResultException e) {
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<RentBook> loadOverdue() {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        List<Predicate> predicates = new ArrayList<>();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<RentBook> cq = cb.createQuery(RentBook.class);
            Root<Notifications> root = cq.from(Notifications.class);

            Join<Notifications, RentBook> rb = root.join("rentBook", JoinType.LEFT);
            Fetch<RentBook, Book> b = rb.fetch("book", JoinType.LEFT);
            b.fetch("authors", JoinType.LEFT);
            rb.fetch("client", JoinType.LEFT);
            root.join("status", JoinType.LEFT);

            predicates.add(cb.like(root.get("status").get("statusName"), "New"));
            predicates.add(cb.isNotNull(root.get("rentBook")));

            Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
            cq.select(root.get("rentBook")).distinct(true);
            cq.where(finalPredicate);

            TypedQuery<RentBook> typedQuery = session.createQuery(cq);
            List<RentBook> result = typedQuery.getResultList();

            return result;
        } catch (NoResultException e) {
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> loadBooksToBeArchived() {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        List<Predicate> predicates = new ArrayList<>();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> cq = cb.createQuery(Book.class);
            Root<Notifications> root = cq.from(Notifications.class);

            root.join("status", JoinType.LEFT);
            Join<Notifications, Book> rb = root.join("book", JoinType.LEFT);
            rb.fetch("authors", JoinType.LEFT);

            predicates.add(cb.like(root.get("status").get("statusName"), "New"));
            predicates.add(cb.isNotNull(root.get("book")));

            Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
            cq.select(root.get("book")).distinct(true);
            cq.where(finalPredicate);


            List<Book> result = session.createQuery(cq).getResultList();

            return result;
        } catch (NoResultException e) {
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean lendBook(LendBookDTO lendBookDTO, Long userId) {
        return commonAdminOperatorRepository.lendBook(lendBookDTO, userId);
    }
}
