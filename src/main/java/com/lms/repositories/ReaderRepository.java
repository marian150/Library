package com.lms.repositories;

import com.lms.models.entities.RentBook;
import com.lms.models.entities.User;

import java.util.List;

public interface ReaderRepository {
    List<Object[]> loadBooks(Long userId);
}
