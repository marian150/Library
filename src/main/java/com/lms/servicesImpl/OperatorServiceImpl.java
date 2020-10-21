package com.lms.servicesImpl;

import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.Book;
import com.lms.models.entities.User;
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
}
