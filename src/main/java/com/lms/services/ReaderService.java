package com.lms.services;

import com.lms.models.entities.RentBook;

import java.util.List;

public interface ReaderService {
    List<Object[]> loadBooks();
}
