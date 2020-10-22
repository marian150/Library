package com.lms.repositories;

import com.lms.models.dtos.SignUpDTO;

public interface AdminRepository {
    boolean createOperator(SignUpDTO signUpDTO);
}
