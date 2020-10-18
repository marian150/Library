package com.lms.repositories;

import com.lms.models.dtos.SignUpDTO;

public interface OperatorRepository {
    boolean createReader(SignUpDTO signUpDTO);
}
