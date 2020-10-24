package com.lms.services;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.BookCovers;
import com.lms.models.entities.Genre;
import com.lms.models.entities.User;

import java.util.List;
import java.util.Map;

public interface OperatorService extends PrivilegedUserService {
    boolean createReader(SignUpDTO signUpDTO);// added in PrivilegedUserService
    User browseUser(Map<String, String> values);// added in PrivilegedUserService
    boolean addBook(AddBookDTO addBookDTO);// added in PrivilegedUserService
    List<BookCovers> retrieveBookCovers();
    List<Genre> retrieveBookGenre();
    boolean addPublisher(String publisherName);
    boolean searchPublisher(String publisherName);
    boolean searchAuthor(String author);
    boolean addAuthor(String author);
}
