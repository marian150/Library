package com.lms.servicesImpl;

import com.lms.models.dtos.LoginDTO;
import com.lms.models.entities.User;
import com.lms.repositories.LoginRepository;
import com.lms.services.LoginService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class LoginServiceImpl implements LoginService {

    @Inject
    private LoginRepository loginRepository;

    public User login(LoginDTO loginDTO) {

        User user = loginRepository.findByEmailAndPass(loginDTO);

        return user;
    }
}
