package com.lms.repositories;

public interface NotificationRepository {
    void checkForOverdue();
    void checkBooksToBeArchived();
}
