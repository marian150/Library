package com.lms.repositoriesImpl;

import com.lms.config.ConfigurationSessionFactory;
import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.repositories.AdminRepository;
import com.lms.security.Password;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.enterprise.context.Dependent;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    @Override
    public boolean createReader(SignUpDTO signUpDTO) {
        return false;
    }

    @Override
    public List<User> searchReader(Map<String, String> values) {
        return null;
    }

    @Override
    public List<Book> searchBook(Map<String, String> values) {
        return null;
    }

    @Override
    public User browseUser(Map<String, String> values) {
        return null;
    }

    @Override
    public boolean addBook(AddBookDTO addBookDTO) {
        return false;
    }

    @Override
    public List<BookCovers> retrieveBookCovers() {
        return null;
    }

    @Override
    public List<Genre> retrieveBookGenre() {
        return null;
    }

    @Override
    public List<BookState> retrieveBookState() {
        return null;
    }

    @Override
    public boolean addPublisher(String publisherName) {
        return false;
    }

    @Override
    public boolean searchPublisher(String publisherName) {
        return false;
    }

    @Override
    public boolean searchAuthor(String author) {
        return false;
    }

    @Override
    public boolean addAuthor(String author) {
        return false;
    }

    @Override
    public boolean lendBook(LendBookDTO lendBookDTO, Long userId) {
        return false;
    }

    @Override
    public boolean scrapBook(Long id) {
        return false;
    }

    @Override
    public List<RentBook> findLentBooks(Map<String, String> values) {
        return null;
    }

    @Override
    public boolean returnBooks(ReturnBookDTO books) {
        return false;
    }

    @Override
    public LocalDate extendDueDate(Long id) {
        return null;
    }
}
