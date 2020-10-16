package com.lms.servicesImpl;

import com.lms.repositories.ReaderRepository;
import com.lms.services.ReaderService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class ReaderServiceImpl implements ReaderService {

    @Inject
    private ReaderRepository readerRepository;

    public List<Object[]> loadBooks() {
        List<Object[]> books = readerRepository.loadBooks();
        return books;
    }
}
