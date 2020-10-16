package com.lms.repositories;

import com.lms.models.entities.RentBook;

import java.util.List;

public interface ReaderRepository {
    List<Object[]> loadBooks();
}
