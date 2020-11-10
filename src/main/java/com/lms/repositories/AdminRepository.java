package com.lms.repositories;

import com.lms.models.dtos.SignUpDTO;

public interface AdminRepository extends PrivilegedUserRepository{
    boolean createOperator(SignUpDTO signUpDTO);
}
