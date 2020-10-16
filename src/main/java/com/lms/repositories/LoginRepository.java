package com.lms.repositories;

import com.lms.models.dtos.LoginDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.Form;
import com.lms.models.entities.User;

public interface LoginRepository {

    User findByEmailAndPass(LoginDTO loginDTO);
    void persistNewReaderForm(SignUpDTO signUpDTO);

}
