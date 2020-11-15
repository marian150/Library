package com.lms.repositories;

import com.lms.models.nonpersistentclasses.LoadFormsModel;

import java.util.List;

public interface OperatorRepository extends PrivilegedUserRepository {
    List<LoadFormsModel> loadNewForms();
}
