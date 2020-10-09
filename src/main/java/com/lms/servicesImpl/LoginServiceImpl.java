package com.lms.servicesImpl;

import com.lms.models.dtos.LoginDTO;
import com.lms.models.entities.User;
import com.lms.repositories.LoginRepository;
import com.lms.services.LoginService;

import javax.enterprise.context.Dependent;
<<<<<<< HEAD
=======
import javax.inject.Inject;
>>>>>>> a5d49b7... Added hibernate.cfg.xml and Manage SessionFactory

@Dependent
public class LoginServiceImpl implements LoginService {

<<<<<<< HEAD
=======
    @Inject
>>>>>>> a5d49b7... Added hibernate.cfg.xml and Manage SessionFactory
    private LoginRepository loginRepository;

    public User login(LoginDTO loginDTO) {

        User user = loginRepository.findByEmailAndPass(loginDTO);

        return user;
    }
}
