package com.lms.servicesImpl;

import com.lms.models.entities.Author;
import com.lms.models.entities.RentBook;
import com.lms.models.nonpersistentclasses.BooksReaderView;
import com.lms.repositories.ReaderRepository;
import com.lms.services.ReaderService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class ReaderServiceImpl implements ReaderService {

    @Inject
    private ReaderRepository readerRepository;

    public List<BooksReaderView> loadBooks(Long userId) {
        List<RentBook> books = readerRepository.loadBooks(userId);
        List<BooksReaderView> booksReaderViews = new ArrayList<>();
        for(RentBook rb : books){
            String authors = "";
            for(Author a : rb.getBook().getAuthors()){
                authors += a.getName()+", ";
            }
            booksReaderViews.add(
                    new BooksReaderView(
                            rb.getBook().getTitle(),
                            authors,
                            rb.getBook().getPublisher().getPublisherName(),
                            rb.getRentDate().toString(),
                            rb.getDueDate().toString()
                    ));
        }

        return booksReaderViews;
    }
}
