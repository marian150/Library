package com.lms.servicesImpl;

import com.lms.models.dtos.SignUpDTO;
import com.lms.repositories.OperatorRepository;
import com.lms.services.OperatorService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class OperatorServiceImpl implements OperatorService {

    @Inject
    private OperatorRepository operatorRepository;

    @Override
    public boolean createReader(SignUpDTO signUpDTO) {
        return operatorRepository.createReader(signUpDTO);
    }
}
