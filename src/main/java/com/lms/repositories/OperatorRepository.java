package com.lms.repositories;

import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.User;


import java.util.List;
import java.util.Map;

public interface OperatorRepository {
    boolean createReader(SignUpDTO signUpDTO);
    List<User> searchReader(Map<String, String> values);
}
