package com.lms.services;

import com.lms.models.dtos.SignUpDTO;

public interface AdminService {
    boolean createOperator(SignUpDTO signUpDTO);
}
