package com.lms.servicesImpl;

import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.User;
import com.lms.repositories.SignUpRepository;
import com.lms.services.SignUpService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class SignUpServiceImpl implements SignUpService {
    @Inject
    private SignUpRepository signUpRepository;

    @Override
    public void signUp(SignUpDTO signUpDTO) {

    }
}
