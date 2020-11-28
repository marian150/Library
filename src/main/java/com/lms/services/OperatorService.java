package com.lms.services;

import com.lms.models.nonpersistentclasses.FormTableView;
import com.lms.models.nonpersistentclasses.LoadBooksToBeArchivedModel;

import com.lms.models.nonpersistentclasses.OverdueBooksTableView;

import java.util.List;

public interface OperatorService extends PrivilegedUserService {
    List<FormTableView> loadNewForms();
    List<OverdueBooksTableView> loadOverdue();
    List<LoadBooksToBeArchivedModel> loadBooksToBeArchived();
}
