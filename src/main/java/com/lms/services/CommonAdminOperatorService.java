package com.lms.services;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.LoadFormsModel;
import com.lms.repositories.PrivilegedUserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CommonAdminOperatorService {
    List<Book> searchBook(PrivilegedUserRepository pu, Map<String, String> values);
    List<User> searchUsers(PrivilegedUserRepository pu, Map<String, String> values);
    boolean createReader(PrivilegedUserRepository pu, SignUpDTO signUpDTO);
    boolean addBook(PrivilegedUserRepository pu, AddBookDTO addBookDTO);
    boolean scrapBook(PrivilegedUserRepository pu, Long bookId);
    List<RentBook> findLentBooks(PrivilegedUserRepository pu, Map<String, String> values);
    List<BookCovers> retrieveBookCovers(PrivilegedUserRepository pu);
    List<Genre> retrieveBookGenre(PrivilegedUserRepository pu);
    List<BookState> retrieveBookState(PrivilegedUserRepository pu);
    boolean addPublisher(PrivilegedUserRepository pu, String publisherName);
    boolean searchPublisher(PrivilegedUserRepository pu, String publisherName);
    boolean searchAuthor(PrivilegedUserRepository pu, String author);
    boolean addAuthor(PrivilegedUserRepository pu, String author);
    boolean lendBook(PrivilegedUserRepository pu, LendBookDTO lendBookDTO, Long userId);
    boolean returnBooks(PrivilegedUserRepository pu, ReturnBookDTO returnBookDTO);
    LocalDate extendDueDate(PrivilegedUserRepository pu, Long id);
    List<LoadFormsModel> loadForms(PrivilegedUserRepository pu);
}
