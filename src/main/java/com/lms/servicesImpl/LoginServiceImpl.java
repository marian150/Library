package com.lms.servicesImpl;

import com.lms.models.dtos.LoginDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.Form;
import com.lms.models.entities.FormStatus;
import com.lms.models.entities.User;
import com.lms.repositories.LoginRepository;
import com.lms.services.LoginService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.LocalDate;

@Dependent
public class LoginServiceImpl implements LoginService {

    @Inject
    private LoginRepository loginRepository;

    public User login(LoginDTO loginDTO) {
        User user = loginRepository.findByEmailAndPass(loginDTO);
        return user;
    }

    @Override
    public void signup(SignUpDTO signUpDTO) {
        loginRepository.persistNewReaderForm(signUpDTO);
    }
}
