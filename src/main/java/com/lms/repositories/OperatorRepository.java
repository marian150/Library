package com.lms.repositories;

import com.lms.models.dtos.AddBookDTO;
import com.lms.models.dtos.LendBookDTO;
import com.lms.models.dtos.ReturnBookDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OperatorRepository extends PrivilegedUserRepository {
    void checkForPostponed();
}
