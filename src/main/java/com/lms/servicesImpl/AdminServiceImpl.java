package com.lms.servicesImpl;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.Book;
import com.lms.models.entities.User;
import com.lms.repositories.AdminRepository;
import com.lms.services.AdminService;
import com.lms.services.PrivilegedUserService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
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
        return null;
    }

    @Override
    public List<User> searchReader(Map<String, String> values) {
        return null;
    }

    @Override
    public boolean createReader(SignUpDTO signUpDTO) {
        return false;
    }

    @Override
    public User browseUser(Map<String, String> values) {
        return null;
    }

    @Override
    public boolean addBook(AddBookDTO addBookDTO) {
        return false;
    }

    @Override
    public boolean scrapBook(PrivilegedUserService pu, Long bookId) {
        return false;
    }


}
