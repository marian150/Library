package com.lms.repositories;

import com.lms.models.dtos.SignUpDTO;

public interface OperatorRepository {
    void createReader(SignUpDTO signUpDTO);
}
