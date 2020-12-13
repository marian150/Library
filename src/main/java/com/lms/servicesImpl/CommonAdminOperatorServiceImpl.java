package com.lms.servicesImpl;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.LoadFormsModel;
import com.lms.repositories.PrivilegedUserRepository;
import com.lms.security.Password;
import com.lms.services.CommonAdminOperatorService;

import javax.enterprise.context.Dependent;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Dependent
public class CommonAdminOperatorServiceImpl implements CommonAdminOperatorService {
    @Override
    public List<Book> searchBook(PrivilegedUserRepository pu, Map<String, String> values) {
        return pu.searchBook(values);
    }

    @Override
    public List<User> searchUsers(PrivilegedUserRepository pu, Map<String, String> values) {
        List<User> users = pu.searchUsers(values);
        for(User u : users){
            if(u.getRating() == null){
                u.setRating(-1);
            }
        }
        return users;
    }

    @Override
    public boolean createReader(PrivilegedUserRepository pu, SignUpDTO signUpDTO) {
        User user = new User();
        user.setFirstName(signUpDTO.getFirstname());
        user.setLastName(signUpDTO.getLastname());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(Password.hashPassword(signUpDTO.getPassword()));
        user.setPhone(signUpDTO.getPhone());
        user.setRegDate(LocalDate.now());
        user.setRating(50);

        return pu.createReader(user);
    }

    @Override
    public boolean addBook(PrivilegedUserRepository pu, AddBookDTO addBookDTO) {
        List<String> authorListString = Arrays.asList(addBookDTO.getAuthor().split(","));
        for (String aut : authorListString) {
            if (!pu.searchAuthor(aut))
                pu.addAuthor(aut);
        }
        if (!pu.searchPublisher(addBookDTO.getPublisher())) {
            pu.addPublisher(addBookDTO.getPublisher());
        }
        return pu.addBook(addBookDTO);
    }

    @Override
    public boolean scrapBook(PrivilegedUserRepository pu, Long bookId) {
        return pu.scrapBook(bookId);
    }

    @Override
    public List<RentBook> findLentBooks(PrivilegedUserRepository pu, Map<String, String> values) {
        return pu.findLentBooks(values);
    }

    @Override
    public List<BookCovers> retrieveBookCovers(PrivilegedUserRepository pu) {
        return pu.retrieveBookCovers();
    }

    @Override
    public List<Genre> retrieveBookGenre(PrivilegedUserRepository pu) {
        return pu.retrieveBookGenre();
    }

    @Override
    public List<BookState> retrieveBookState(PrivilegedUserRepository pu) {
        return pu.retrieveBookState();
    }

    @Override
    public boolean addPublisher(PrivilegedUserRepository pu, String publisherName) {
        return pu.addPublisher(publisherName);
    }

    @Override
    public boolean searchPublisher(PrivilegedUserRepository pu, String publisherName) {
        return pu.searchPublisher(publisherName);
    }

    @Override
    public boolean searchAuthor(PrivilegedUserRepository pu, String author) {
        return pu.searchAuthor(author);
    }

    @Override
    public boolean addAuthor(PrivilegedUserRepository pu, String author) {
        return pu.addAuthor(author);
    }

    @Override
    public boolean lendBook(PrivilegedUserRepository pu, LendBookDTO lendBookDTO, Long userId) {
        return pu.lendBook(lendBookDTO, userId);
    }

    @Override
    public boolean returnBooks(PrivilegedUserRepository pu, ReturnBookDTO returnBookDTO) {
        return pu.returnBooks(returnBookDTO);
    }

    @Override
    public LocalDate extendDueDate(PrivilegedUserRepository pu, Long id) {
        return pu.extendDueDate(id);
    }

    @Override
    public List<LoadFormsModel> loadForms(PrivilegedUserRepository pu) {
        return pu.loadForms();
    }
}
