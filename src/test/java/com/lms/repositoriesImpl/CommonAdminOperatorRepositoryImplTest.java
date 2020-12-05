package com.lms.repositoriesImpl;

import com.lms.models.entities.Book;
import com.lms.repositories.CommonAdminOperatorRepository;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CommonAdminOperatorRepositoryImplTest {

    private CommonAdminOperatorRepository commonAdminOperatorRepository;

    @Test
    void searchBook() {
        commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        Map<String, String> map = new HashMap<>();
        map.put("title", "Brave New World");
        List<Book> books = commonAdminOperatorRepository.searchBook(map);
        for(Book b : books){
            System.out.println(b.getTitle());
        }


    }
}