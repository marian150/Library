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
        OperatorRepository operatorRepository = new OperatorRepositoryImpl();
        Map<String, String> values = new HashMap<>();
        values.put("firstName", "Curtis");
        List<User> result = operatorRepository.searchUsers(values);
        assertEquals(result.get(0).getFirstName(), "Curtis");
    }

    @Test
    public void searchPublisher() {
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        boolean result = commonAdminOperatorRepository.searchPublisher("Bloomsbury");
        assertTrue(result);
    }
}