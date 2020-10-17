package com.lms.repositoriesImpl;

import com.lms.models.dtos.SignUpDTO;
import com.lms.repositories.OperatorRepository;

import javax.enterprise.context.Dependent;

@Dependent
public class OperatorRepositoryImpl implements OperatorRepository {

    @Override
    public void createReader(SignUpDTO signUpDTO) {

    }
}
