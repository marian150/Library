package com.lms.services;

import com.lms.models.dtos.SignUpDTO;

public interface OperatorService {
    void createReader(SignUpDTO signUpDTO);
}
