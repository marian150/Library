package com.lms.repositoriesImpl;

import com.lms.config.ConfigurationSessionFactory;
import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.LoadFormsModel;
import com.lms.repositories.AdminRepository;
import com.lms.repositories.CommonAdminOperatorRepository;
import com.lms.security.Password;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Dependent
public class AdminRepositoryImpl implements AdminRepository {
    Logger logger = Logger.getLogger(AdminRepositoryImpl.class);

    private CommonAdminOperatorRepository commonAdminOperatorRepository;

    @Inject
    public AdminRepositoryImpl(CommonAdminOperatorRepository commonAdminOperatorRepository){
        this.commonAdminOperatorRepository = commonAdminOperatorRepository;
    }

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
            Serializable userId = session.save(user);
            tx.commit();
            logger.info("Operator" + userId + " is created");
            return true;
        } catch (Exception e){
            if(tx != null) tx.rollback();
            logger.error("Unable to create operator", e);
            return false;
        } finally {
            session.close();
        }
    }

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

        u.fetch("userType", JoinType.LEFT);
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
    public boolean lendBook(LendBookDTO lendBookDTO, Long userId) {
        return commonAdminOperatorRepository.lendBook(lendBookDTO, userId);
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
}
