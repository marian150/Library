package com.lms.services;

import com.lms.models.entities.*;
import com.lms.models.nonpersistentclasses.LoadFormsModel;
import com.lms.repositories.CommonAdminOperatorRepository;
import com.lms.repositories.OperatorRepository;
import com.lms.repositoriesImpl.CommonAdminOperatorRepositoryImpl;
import com.lms.repositoriesImpl.OperatorRepositoryImpl;
import com.lms.servicesImpl.CommonAdminOperatorServiceImpl;
import com.lms.servicesImpl.OperatorServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperatorServiceTest {

    @Test
    public void searchBookByTitle(){
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        OperatorRepository operatorRepository = new OperatorRepositoryImpl(commonAdminOperatorRepository);
        CommonAdminOperatorService commonAdminOperatorService = new CommonAdminOperatorServiceImpl();
        OperatorService operatorService = new OperatorServiceImpl(operatorRepository, commonAdminOperatorService);
        Map<String, String> values = new HashMap<>();
        values.put("title", "Brave New World");
        List<Book> actualBooks = operatorService.searchBook(values);
        if(!actualBooks.isEmpty()){
            for(Book book : actualBooks){
                Assert.assertEquals("Brave New World", book.getTitle());
            }
        }
    }
    @Test
    public void searchUsersByFirstName(){
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        OperatorRepository operatorRepository = new OperatorRepositoryImpl(commonAdminOperatorRepository);
        CommonAdminOperatorService commonAdminOperatorService = new CommonAdminOperatorServiceImpl();
        OperatorService operatorService = new OperatorServiceImpl(operatorRepository, commonAdminOperatorService);
        Map<String, String> values = new HashMap<>();
        values.put("firstName", "John");
        List<User> actualUsers = operatorService.searchUsers(values);
        if(!actualUsers.isEmpty()){
            for(User user : actualUsers){
                Assert.assertEquals("John", user.getFirstName());
            }
        }
    }

    @Test
    public void retrieveBookCovers(){
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        OperatorRepository operatorRepository = new OperatorRepositoryImpl(commonAdminOperatorRepository);
        CommonAdminOperatorService commonAdminOperatorService = new CommonAdminOperatorServiceImpl();
        OperatorService operatorService = new OperatorServiceImpl(operatorRepository, commonAdminOperatorService);
        List<BookCovers> bookCovers = operatorService.retrieveBookCovers();
        for(BookCovers bookCover : bookCovers){
            Assert.assertNotNull(bookCover.getCoverName());
        }
    }
    @Test
    public void retrieveBookGenres(){
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        OperatorRepository operatorRepository = new OperatorRepositoryImpl(commonAdminOperatorRepository);
        CommonAdminOperatorService commonAdminOperatorService = new CommonAdminOperatorServiceImpl();
        OperatorService operatorService = new OperatorServiceImpl(operatorRepository, commonAdminOperatorService);
        List<Genre> genres = operatorService.retrieveBookGenre();
        for(Genre genre : genres){
            Assert.assertNotNull(genre.getName());
        }
    }

    @Test
    public void retrieveBookStates(){
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        OperatorRepository operatorRepository = new OperatorRepositoryImpl(commonAdminOperatorRepository);
        CommonAdminOperatorService commonAdminOperatorService = new CommonAdminOperatorServiceImpl();
        OperatorService operatorService = new OperatorServiceImpl(operatorRepository, commonAdminOperatorService);
        List<BookState> bookStates = operatorService.retrieveBookState();
        for(BookState bookState : bookStates){
            Assert.assertNotNull(bookState.getStateName());
        }
    }

    @Test
    public void searchAuthor(){
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        OperatorRepository operatorRepository = new OperatorRepositoryImpl(commonAdminOperatorRepository);
        CommonAdminOperatorService commonAdminOperatorService = new CommonAdminOperatorServiceImpl();
        OperatorService operatorService = new OperatorServiceImpl(operatorRepository, commonAdminOperatorService);
        boolean isAuthorFound = operatorService.searchAuthor("Steven King");
        Assert.assertTrue(isAuthorFound);
    }

    @Test
    public void loadForms(){
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        OperatorRepository operatorRepository = new OperatorRepositoryImpl(commonAdminOperatorRepository);
        CommonAdminOperatorService commonAdminOperatorService = new CommonAdminOperatorServiceImpl();
        OperatorService operatorService = new OperatorServiceImpl(operatorRepository, commonAdminOperatorService);
        List<LoadFormsModel> loadFormsModels = operatorService.loadForms();
        for(LoadFormsModel loadFormsModel : loadFormsModels){
            Assert.assertNotNull(loadFormsModel);
        }
    }
}
