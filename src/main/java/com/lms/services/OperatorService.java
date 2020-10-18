package com.lms.services;

import com.lms.models.dtos.SignUpDTO;

public interface OperatorService {
    boolean createReader(SignUpDTO signUpDTO);
}
