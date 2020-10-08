package com.lms.services;

import com.lms.models.dtos.LoginDTO;
import com.lms.models.entities.User;

public interface LoginService {
    User login(LoginDTO loginDTO);
}
