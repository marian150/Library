package com.lms.servicesImpl;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.LoadFormsModel;
import com.lms.repositories.PrivilegedUserRepository;
import com.lms.services.CommonAdminOperatorService;

import javax.enterprise.context.Dependent;
import java.time.LocalDate;
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
        return pu.createReader(signUpDTO);
    }

    @Override
    public boolean addBook(PrivilegedUserRepository pu, AddBookDTO addBookDTO) {
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
