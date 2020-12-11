package com.lms.repositories;

import com.lms.models.entities.RentBook;
import com.lms.models.entities.User;
import com.lms.models.nonpersistentclasses.ReaderTableView;

import java.util.List;

public interface ReaderRepository {
    List<RentBook> loadBooks(Long userId);
}
