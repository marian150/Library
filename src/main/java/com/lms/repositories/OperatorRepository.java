package com.lms.repositories;

import com.lms.models.entities.Book;
import com.lms.models.entities.Notifications;
import com.lms.models.nonpersistentclasses.LoadFormsModel;

import java.util.List;

public interface OperatorRepository extends PrivilegedUserRepository {
    List<LoadFormsModel> loadNewForms();
    List<Notifications> loadOverdue();
    List<Notifications> loadBooksToBeArchived();
    boolean changeNotificationStatus(Long notifID, Long statusID);
    Notifications getNotification(Long id);
    boolean archiveBook(Long id);
}
