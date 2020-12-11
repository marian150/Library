package com.lms.repositoriesImpl;

import com.lms.models.entities.RentBook;
import com.lms.repositories.ReaderRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ReaderRepositoryImplTest {

    @Test
    public void loadLentBooks(){
        ReaderRepository readerRepository = new ReaderRepositoryImpl();
        List<RentBook> lentBooks = readerRepository.loadBooks(3L);
        for(RentBook r : lentBooks){
            Assert.assertNotNull(r);
        }
    }
}
