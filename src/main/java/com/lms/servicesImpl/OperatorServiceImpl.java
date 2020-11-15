package com.lms.servicesImpl;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.LoadFormsModel;
import com.lms.models.nonpersistentclasses.LoadOverdueModel;
import com.lms.repositories.OperatorRepository;
import com.lms.services.OperatorService;
import com.lms.services.PrivilegedUserService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Dependent
public class OperatorServiceImpl implements OperatorService {

    @Inject
    private OperatorRepository operatorRepository;

    @Override
    public boolean createReader(SignUpDTO signUpDTO) {
        return operatorRepository.createReader(signUpDTO);
    }

    @Override
    public List<User> searchUsers(Map<String, String> values) {
        List<User> users = operatorRepository.searchUsers(values);
        for(User u : users){
            if(u.getRating() == null){
                u.setRating(-1);
            }
        }
        return users;
    }

    @Override
    public List<Book> searchBook(Map<String, String> values) {
        return operatorRepository.searchBook(values);
    }
    

    @Override
    public boolean addBook(AddBookDTO addBookDTO) {
        return operatorRepository.addBook(addBookDTO);
    }

    @Override
    public boolean scrapBook(PrivilegedUserService pu, Long bookId) { return operatorRepository.scrapBook(bookId); }

    @Override
    public List<BookCovers> retrieveBookCovers() {
        return operatorRepository.retrieveBookCovers();
    }

    @Override
    public List<Genre> retrieveBookGenre() {
        return operatorRepository.retrieveBookGenre();
    }

    @Override
    public List<BookState> retrieveBookState() {
        return operatorRepository.retrieveBookState();
    }

    @Override
    public boolean addPublisher(String publisherName) {
        return operatorRepository.addPublisher(publisherName);
    }

    @Override
    public boolean searchPublisher(String publisherName) {
        return operatorRepository.searchPublisher(publisherName);
    }

    @Override
    public boolean searchAuthor(String author) {
        return operatorRepository.searchAuthor(author);
    }

    @Override
    public boolean addAuthor(String author) {
        return operatorRepository.addAuthor(author);
    }

    @Override
    public boolean lendBook(LendBookDTO lendBookDTO, Long userId) { return operatorRepository.lendBook(lendBookDTO, userId); }

    @Override
    public boolean returnBooks(ReturnBookDTO returnBookDTO) { return operatorRepository.returnBooks(returnBookDTO); }

    @Override
    public LocalDate extendDueDate(Long id) { return operatorRepository.extendDueDate(id); }

    @Override
    public List<LoadFormsModel> loadForms() {
        return operatorRepository.loadForms();
    }

    @Override
    public List<RentBook> findLentBooks(Map<String, String> values) { return operatorRepository.findLentBooks(values); }


    @Override
    public List<LoadFormsModel> loadNewForms() {
        return operatorRepository.loadNewForms();
    }

    @Override
    public List<RentBook> loadOverdue() {
        return operatorRepository.loadOverdue();
    }


}
