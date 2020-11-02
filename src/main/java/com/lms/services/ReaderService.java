package com.lms.services;

import com.lms.models.entities.RentBook;
import com.lms.models.entities.User;

import java.util.List;

public interface ReaderService {
    List<Object[]> loadBooks(Long userId);
}
