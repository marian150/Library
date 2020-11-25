package com.lms.servicesImpl;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.LoadFormsModel;
import com.lms.repositories.AdminRepository;
import com.lms.services.AdminService;
import com.lms.services.CommonAdminOperatorService;
import com.lms.services.PrivilegedUserService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Dependent
public class AdminServiceImpl implements AdminService {
    @Inject
    private AdminRepository adminRepository;
    @Inject
    private CommonAdminOperatorService commonAdminOperatorService;

    @Override
    public boolean createOperator(SignUpDTO signUpDTO) {
        return adminRepository.createOperator(signUpDTO);
    }

    @Override
    public List<User> searchUsers(Map<String, String> values) {
        return commonAdminOperatorService.searchUsers(adminRepository, values);
    }

    @Override
    public List<Book> searchBook(Map<String, String> values) {
        return commonAdminOperatorService.searchBook(adminRepository, values);
    }

    @Override
    public boolean createReader(SignUpDTO signUpDTO) {
        return commonAdminOperatorService.createReader(adminRepository, signUpDTO);
    }

    @Override
    public boolean addBook(AddBookDTO addBookDTO) {
        return commonAdminOperatorService.addBook(adminRepository, addBookDTO);
    }

    @Override
    public boolean scrapBook(Long bookId) {
        return commonAdminOperatorService.scrapBook(adminRepository, bookId);
    }

    @Override
    public List<RentBook> findLentBooks(Map<String, String> values) {
        return commonAdminOperatorService.findLentBooks(adminRepository, values);
    }

    @Override
    public List<BookCovers> retrieveBookCovers() {
        return commonAdminOperatorService.retrieveBookCovers(adminRepository);
    }

    @Override
    public List<Genre> retrieveBookGenre() {
        return commonAdminOperatorService.retrieveBookGenre(adminRepository);
    }

    @Override
    public List<BookState> retrieveBookState() {
        return commonAdminOperatorService.retrieveBookState(adminRepository);
    }

    @Override
    public boolean addPublisher(String publisherName) {
        return commonAdminOperatorService.addPublisher(adminRepository, publisherName);
    }

    @Override
    public boolean searchPublisher(String publisherName) {
        return commonAdminOperatorService.searchPublisher(adminRepository, publisherName);
    }

    @Override
    public boolean searchAuthor(String author) {
        return commonAdminOperatorService.searchAuthor(adminRepository, author);
    }

    @Override
    public boolean addAuthor(String author) {
        return commonAdminOperatorService.addAuthor(adminRepository, author);
    }

    @Override
    public boolean lendBook(LendBookDTO lendBookDTO, Long userId) {
        return commonAdminOperatorService.lendBook(adminRepository, lendBookDTO, userId);
    }

    @Override
    public boolean returnBooks(ReturnBookDTO returnBookDTO) {
        return commonAdminOperatorService.returnBooks(adminRepository, returnBookDTO);
    }

    @Override
    public LocalDate extendDueDate(Long id) {
        return commonAdminOperatorService.extendDueDate(adminRepository, id);
    }

    @Override
    public List<LoadFormsModel> loadForms() {
        return commonAdminOperatorService.loadForms(adminRepository);
    }
}
