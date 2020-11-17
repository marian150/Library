package com.lms.services;

import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.User;

import java.util.List;
import java.util.Map;

public interface AdminService extends PrivilegedUserService {
    boolean createOperator(SignUpDTO signUpDTO);
}
