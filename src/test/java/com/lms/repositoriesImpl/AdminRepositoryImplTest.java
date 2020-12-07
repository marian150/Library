package com.lms.repositoriesImpl;

import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.User;
import com.lms.repositories.AdminRepository;
import com.lms.repositories.CommonAdminOperatorRepository;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AdminRepositoryImplTest {
    @Test
    public void searchUsers() {
        CommonAdminOperatorRepository commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        AdminRepository adminRepository = new AdminRepositoryImpl(commonAdminOperatorRepository);
        Map<String, String> values = new HashMap<>();
        values.put("firstName", "Yana");
        values.put("userType", "Operator");
        List<User> result = adminRepository.searchUsers(values);
        assertEquals("Operator",result.get(0).getUserType().getTypeName());
        assertEquals("Yana", result.get(0).getFirstName());
    }

}
