package com.lms.servicesImpl;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;
import com.lms.repositories.OperatorRepository;
import com.lms.services.OperatorService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
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
    public List<User> searchReader(Map<String, String> values) {
        return operatorRepository.searchReader(values);
    }

    @Override
    public List<Book> searchBook(Map<String, String> values) {
        return operatorRepository.searchBook(values);
    }

    @Override
    public User browseUser(Map<String, String> values) {
        return operatorRepository.browseUser(values);
    }

    @Override
    public boolean addBook(AddBookDTO addBookDTO) {
        return operatorRepository.addBook(addBookDTO);
    }

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
}
