package com.lms.services;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.Book;
import com.lms.models.entities.RentBook;
import com.lms.models.entities.User;

import java.util.List;
import java.util.Map;

public interface PrivilegedUserService {
    List<Book> searchBook(Map<String, String> values);
    List<User> searchReader(Map<String, String> values);
    boolean createReader(SignUpDTO signUpDTO);
    User browseUser(Map<String, String> values);
    boolean addBook(AddBookDTO addBookDTO);
    boolean scrapBook(PrivilegedUserService pu, Long bookId);
    List<RentBook> findLentBooks(Map<String, String> values);
}
