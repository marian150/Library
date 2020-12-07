package com.lms.repositoriesImpl;

import com.lms.models.entities.Book;
import com.lms.repositories.CommonAdminOperatorRepository;
import org.junit.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class CommonAdminOperatorRepositoryImplTest {

    @Test
    public void searchBook() {
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        Map<String, String> map = new HashMap<>();
        map.put("title", "Brave New World");
        List<Book> books = commonAdminOperatorRepository.searchBook(map);
        for(Book b : books){
            System.out.println(b.getTitle());
        }
    }

    @Test
    public void searchPublisher() {
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        boolean result = commonAdminOperatorRepository.searchPublisher("Bloomsbury");
        assertTrue(result);
    }
}