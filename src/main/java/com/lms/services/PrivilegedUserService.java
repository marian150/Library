package com.lms.services;

import com.lms.models.entities.Book;
import com.lms.models.entities.User;

import java.util.List;
import java.util.Map;

public interface PrivilegedUserService {
    List<Book> searchBook(Map<String, String> values);
    List<User> searchReader(Map<String, String> values);
}
