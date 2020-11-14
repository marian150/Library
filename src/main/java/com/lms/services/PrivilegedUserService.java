package com.lms.services;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.FormTableView;
import com.lms.models.nonpersistentclasses.ReturnBookTableView;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PrivilegedUserService {
    List<Book> searchBook(Map<String, String> values);
    List<User> searchUsers(Map<String, String> values);
    boolean createReader(SignUpDTO signUpDTO);
    boolean addBook(AddBookDTO addBookDTO);
    boolean scrapBook(PrivilegedUserService pu, Long bookId);
    List<RentBook> findLentBooks(Map<String, String> values);
    List<BookCovers> retrieveBookCovers();
    List<Genre> retrieveBookGenre();
    List<BookState> retrieveBookState();
    boolean addPublisher(String publisherName);
    boolean searchPublisher(String publisherName);
    boolean searchAuthor(String author);
    boolean addAuthor(String author);
    boolean lendBook(LendBookDTO lendBookDTO, Long userId);
    boolean returnBooks(ReturnBookDTO returnBookDTO);
    LocalDate extendDueDate(Long id);
    List<FormTableView> loadForms();
}
