package com.lms.services;

import com.lms.models.entities.*;
import com.lms.repositories.AdminRepository;
import com.lms.repositories.CommonAdminOperatorRepository;
import com.lms.repositoriesImpl.AdminRepositoryImpl;
import com.lms.repositoriesImpl.CommonAdminOperatorRepositoryImpl;
import com.lms.servicesImpl.AdminServiceImpl;
import com.lms.servicesImpl.CommonAdminOperatorServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminServiceImplTest {

    @Test
    public void searchUsers(){
        CommonAdminOperatorService commonAdminOperatorService = new CommonAdminOperatorServiceImpl();
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        AdminRepository adminRepository = new AdminRepositoryImpl(commonAdminOperatorRepository);
        AdminService adminService = new AdminServiceImpl(adminRepository, commonAdminOperatorService);
        Map<String, String> values = new HashMap<>();
        values.put("firstName", "Yana");
        values.put("userType", "Operator");
        List<User> users = adminService.searchUsers(values);
        Assert.assertEquals("Yana", users.get(0).getFirstName());
        Assert.assertEquals("Operator", users.get(0).getUserType().getTypeName());
    }
    @Test
    public void searchBooks(){
        CommonAdminOperatorService commonAdminOperatorService = new CommonAdminOperatorServiceImpl();
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        AdminRepository adminRepository = new AdminRepositoryImpl(commonAdminOperatorRepository);
        AdminService adminService = new AdminServiceImpl(adminRepository, commonAdminOperatorService);
        Map<String, String> values = new HashMap<>();
        values.put("bookState", "New");
        List<Book> books = adminService.searchBook(values);
        for(Book book : books){
            Assert.assertEquals("New", book.getBookState().getStateName());
        }
    }
    @Test
    public void retrieveBookCovers(){
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        AdminRepository adminRepository = new AdminRepositoryImpl(commonAdminOperatorRepository);
        CommonAdminOperatorService commonAdminOperatorService = new CommonAdminOperatorServiceImpl();
        AdminService adminService = new AdminServiceImpl(adminRepository, commonAdminOperatorService);
        List<BookCovers> bookCovers = adminService.retrieveBookCovers();
        for(BookCovers bookCover : bookCovers){
            Assert.assertNotNull(bookCover.getCoverName());
        }
    }
    @Test
    public void retrieveBookGenres(){
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        AdminRepository adminRepository = new AdminRepositoryImpl(commonAdminOperatorRepository);
        CommonAdminOperatorService commonAdminOperatorService = new CommonAdminOperatorServiceImpl();
        AdminService adminService = new AdminServiceImpl(adminRepository, commonAdminOperatorService);
        List<Genre> genres = adminService.retrieveBookGenre();
        for(Genre genre : genres){
            Assert.assertNotNull(genre.getName());
        }
    }

    @Test
    public void retrieveBookStates(){
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        AdminRepository adminRepository = new AdminRepositoryImpl(commonAdminOperatorRepository);
        CommonAdminOperatorService commonAdminOperatorService = new CommonAdminOperatorServiceImpl();
        AdminService adminService = new AdminServiceImpl(adminRepository, commonAdminOperatorService);
        List<BookState> bookStates = adminService.retrieveBookState();
        for(BookState bookState : bookStates){
            Assert.assertNotNull(bookState.getStateName());
        }
    }
}
