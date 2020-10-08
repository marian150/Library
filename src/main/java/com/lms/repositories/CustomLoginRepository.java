package com.lms.repositories;


import com.lms.models.dtos.LoginDTO;
import com.lms.models.entities.User;

public interface CustomLoginRepository {

    User findByEmailAndPass(LoginDTO loginDTO);

}
