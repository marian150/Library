package com.lms.repositories;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.Book;
import com.lms.models.entities.BookCovers;
import com.lms.models.entities.Genre;
import com.lms.models.entities.User;


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
}
