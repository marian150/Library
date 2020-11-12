package com.lms.services;

import com.lms.models.dtos.SignUpDTO;

public interface AdminService extends PrivilegedUserService {
    boolean createOperator(SignUpDTO signUpDTO);

}
