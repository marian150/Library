package com.lms.repositories;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;


import java.util.List;
import java.util.Map;

public interface OperatorRepository {
    boolean createReader(SignUpDTO signUpDTO);
    List<User> searchReader(Map<String, String> values);
    List<Book> searchBook(Map<String, String> values);
    User browseUser(Map<String, String> values);
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
    boolean returnBooks(ReturnBookDTO books, Long libId);
}
