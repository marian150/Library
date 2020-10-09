package com.lms.repositories;

<<<<<<< HEAD
public interface LoginRepository extends CustomLoginRepository{

=======
import com.lms.models.dtos.LoginDTO;
import com.lms.models.entities.User;

public interface LoginRepository {

    User findByEmailAndPass(LoginDTO loginDTO);
>>>>>>> a5d49b7... Added hibernate.cfg.xml and Manage SessionFactory

}
