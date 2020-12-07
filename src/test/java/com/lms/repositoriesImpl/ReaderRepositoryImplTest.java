package com.lms.repositoriesImpl;

import com.lms.repositories.ReaderRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ReaderRepositoryImplTest {

    @Test
    public void loadLentBooks(){
        ReaderRepository readerRepository = new ReaderRepositoryImpl();
        List<Object[]> lentBooks = readerRepository.loadBooks(3L);
        Object[][] array = lentBooks.toArray(new Object[lentBooks.size()][]);
        for(int i = 0; i < array.length; i++){
            Assert.assertNotNull(array[i]);
        }
    }
}
