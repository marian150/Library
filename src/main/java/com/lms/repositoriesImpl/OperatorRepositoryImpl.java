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
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.*;

@Dependent
public class OperatorRepositoryImpl implements OperatorRepository {

    private CommonAdminOperatorRepository commonAdminOperatorRepository;

    @Inject
    public OperatorRepositoryImpl(CommonAdminOperatorRepository commonAdminOperatorRepository){
        this.commonAdminOperatorRepository = commonAdminOperatorRepository;
    }

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
                    notificationsRoot.get("form").get("submitDate"), notificationsRoot.get("status").get("statusName"),
                    notificationsRoot.get("notifyId"));

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
    public List<Notifications> loadOverdue() {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        List<Predicate> predicates = new ArrayList<>();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Notifications> cq = cb.createQuery(Notifications.class);
            Root<Notifications> root = cq.from(Notifications.class);

            Fetch<Notifications, RentBook> rb = root.fetch("rentBook", JoinType.LEFT);
            Fetch<RentBook, Book> b = rb.fetch("book", JoinType.LEFT);
            b.fetch("authors", JoinType.LEFT);
            rb.fetch("client", JoinType.LEFT);
            root.fetch("status", JoinType.LEFT);

            predicates.add(cb.like(root.get("status").get("statusName"), "New"));
            predicates.add(cb.isNotNull(root.get("rentBook")));

            Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
            cq.select(root).distinct(true);
            cq.where(finalPredicate);

            TypedQuery<Notifications> typedQuery = session.createQuery(cq);
            List<Notifications> result = typedQuery.getResultList();

            return result;
        } catch (NoResultException e) {
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Notifications> loadBooksToBeArchived() {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        List<Predicate> predicates = new ArrayList<>();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Notifications> cq = cb.createQuery(Notifications.class);
            Root<Notifications> root = cq.from(Notifications.class);

            root.fetch("status", JoinType.LEFT);
            Fetch<Notifications, Book> rb = root.fetch("book", JoinType.LEFT);
            rb.fetch("authors", JoinType.LEFT);

            predicates.add(cb.like(root.get("status").get("statusName"), "New"));
            predicates.add(cb.isNotNull(root.get("book")));

            Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
            cq.select(root).distinct(true);
            cq.where(finalPredicate);


            List<Notifications> result = session.createQuery(cq).getResultList();

            return result;
        } catch (NoResultException e) {
            if(tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean changeNotificationStatus(Long notifID, Long statusID) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            String hql = "select n from Notifications n where n.notifyId like :idN";
            Query query = session.createQuery(hql);
            query.setParameter("idN", notifID);

            String hql2 = "select s from Status s where s.statusId like :idS";
            Query query2 = session.createQuery(hql2);
            query2.setParameter("idS", statusID);

            Notifications notification = (Notifications) query.getSingleResult();
            Status status = (Status) query2.getSingleResult();
            System.out.println(status.getStatusId());
            System.out.println(notification.getNotifyId());
            notification.setStatus(status);
            session.update(notification);
            tx.commit();
            return true;
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Notifications getNotification(Long id) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        String hql = "select n from Notifications n where n.notifyId like :idN";
        Query query = session.createQuery(hql);
        query.setParameter("idN", id);

        Notifications notification;
        try {
            notification = (Notifications)query.getSingleResult();
            return notification;
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean archiveBook(Long id) {
        Session session = ConfigurationSessionFactory.getSessionFactory().openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            javax.persistence.Query query = session.createQuery("select bs from BookState bs where bs.stateName like 'Archived'");
            BookState bs = (BookState)query.getSingleResult();
            Book book = (Book)session.load(Book.class, id);
            book.setBookState(bs);
            session.update(book);
            tx.commit();
            logger.info("Book " + book.getBookId().toString() + " status is changed to " + bs.getStateName());
            return true;
        } catch (Exception e) {
            if(tx != null)tx.rollback();
            logger.error("Unable to archive book", e);
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean lendBook(LendBookDTO lendBookDTO, Long userId) {
        return commonAdminOperatorRepository.lendBook(lendBookDTO, userId);
    }
}
