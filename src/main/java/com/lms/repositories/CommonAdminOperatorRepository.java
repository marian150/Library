package com.lms.repositories;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.LoadFormsModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CommonAdminOperatorRepository {
    boolean createReader(SignUpDTO signUpDTO);
    List<Book> searchBook(Map<String, String> values);
    boolean addBook(AddBookDTO addBookDTO);
    List<BookCovers> retrieveBookCovers();
    List<Genre> retrieveBookGenre();
    List<BookState> retrieveBookState();
    boolean addPublisher(String publisherName);
    boolean searchPublisher(String publisherName);
    boolean searchAuthor(String author);
    boolean addAuthor(String author);
    boolean lendBook(LendBookDTO lendBookDTO, Long userId);
    boolean scrapBook(Long id);
    List<RentBook> findLentBooks(Map<String, String> values);
    boolean returnBooks(ReturnBookDTO books);
    LocalDate extendDueDate(Long id);
    List<LoadFormsModel> loadForms();
}
