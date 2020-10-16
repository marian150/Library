package com.lms.repositories;

import java.util.List;

public interface ReaderRepository {
    List<Object[]> loadBooks();
}
