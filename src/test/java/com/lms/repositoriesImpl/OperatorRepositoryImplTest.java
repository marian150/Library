package com.lms.repositoriesImpl;

import com.lms.models.entities.User;
import com.lms.repositories.CommonAdminOperatorRepository;
import com.lms.repositories.OperatorRepository;
import org.junit.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class OperatorRepositoryImplTest {

    @Test
    public void searchUsers() {
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        OperatorRepository operatorRepository = new OperatorRepositoryImpl(commonAdminOperatorRepository);
        Map<String, String> values = new HashMap<>();
        values.put("firstName", "Curtis");
        List<User> result = operatorRepository.searchUsers(values);
        for(User user : result){
            assertEquals("Curtis", user.getFirstName());
        }
    }
}