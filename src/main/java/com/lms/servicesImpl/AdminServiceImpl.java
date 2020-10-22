package com.lms.servicesImpl;

import com.lms.repositories.AdminRepository;
import com.lms.services.AdminService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class AdminServiceImpl implements AdminService {
    @Inject
    private AdminRepository adminRepository;
}
