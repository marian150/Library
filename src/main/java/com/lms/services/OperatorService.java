package com.lms.services;

import com.lms.models.entities.Book;
import com.lms.models.entities.RentBook;
import com.lms.models.nonpersistentclasses.LoadBooksToBeArchivedModel;
import com.lms.models.nonpersistentclasses.LoadFormsModel;

import java.util.List;

public interface OperatorService extends PrivilegedUserService {
    List<LoadFormsModel> loadNewForms();
    List<RentBook> loadOverdue();
    List<LoadBooksToBeArchivedModel> loadBooksToBeArchived();
}
