package com.lms.servicesImpl;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.repositories.AdminRepository;
import com.lms.services.AdminService;
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

    @Override
    public boolean createOperator(SignUpDTO signUpDTO) {
        return adminRepository.createOperator(signUpDTO);
    }

    @Override
    public List<Book> searchBook(Map<String, String> values) {
        return adminRepository.searchBook(values);
    }

    @Override
    public List<User> searchReader(Map<String, String> values) {
        return adminRepository.searchReader(values);
    }

    @Override
    public boolean createReader(SignUpDTO signUpDTO) {
        return adminRepository.createReader(signUpDTO);
    }

    @Override
    public boolean addBook(AddBookDTO addBookDTO) {
        return adminRepository.addBook(addBookDTO);
    }

    @Override
    public boolean scrapBook(PrivilegedUserService pu, Long bookId) {
        return false;
    }

    @Override
    public List<RentBook> findLentBooks(Map<String, String> values) {
        return null;
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
    public boolean returnBooks(ReturnBookDTO returnBookDTO) {
        return false;
    }

    @Override
    public LocalDate extendDueDate(Long id) {
        return null;
    }
}
