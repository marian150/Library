package com.lms.services;

import com.lms.models.entities.RentBook;
import com.lms.models.entities.User;
import com.lms.models.nonpersistentclasses.BooksReaderView;

import java.util.List;

public interface ReaderService {
    List<BooksReaderView> loadBooks(Long userId);
}
