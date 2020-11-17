package com.lms.repositories;

import com.lms.models.entities.Book;
import com.lms.models.entities.RentBook;
import com.lms.models.nonpersistentclasses.LoadFormsModel;

import java.util.List;

public interface OperatorRepository extends PrivilegedUserRepository {
    List<LoadFormsModel> loadNewForms();
    List<RentBook> loadOverdue();
    List<Book> loadBooksToBeArchived();
}
