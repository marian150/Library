package com.lms.controllers;

import com.lms.controllers.commonComponentsLogic.CommonAdminOperatorFunctionalities;
import com.lms.controllers.commonComponentsLogicImpl.CommonAdminOperatorFunctionalitiesImpl;
import com.lms.repositories.CommonAdminOperatorRepository;
import com.lms.repositories.OperatorRepository;
import com.lms.repositoriesImpl.CommonAdminOperatorRepositoryImpl;
import com.lms.repositoriesImpl.OperatorRepositoryImpl;
import com.lms.services.CommonAdminOperatorService;
import com.lms.services.OperatorService;
import com.lms.servicesImpl.CommonAdminOperatorServiceImpl;
import com.lms.servicesImpl.OperatorServiceImpl;
import org.jboss.weld.junit5.EnableWeld;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import javax.inject.Inject;

import static org.junit.Assert.*;

public class OperatorControllerTest {

    private OperatorService operatorService;
    private OperatorRepository operatorRepository;
    private CommonAdminOperatorRepository commonAdminOperatorRepository;
    private CommonAdminOperatorService commonAdminOperatorService;
    private CommonAdminOperatorFunctionalities commonAdminOperatorFunctionalities;

    @Before
    public void getDependencies(){
        operatorRepository = new OperatorRepositoryImpl();
        operatorService = new OperatorServiceImpl();
        commonAdminOperatorRepository = new CommonAdminOperatorRepositoryImpl();
        commonAdminOperatorService = new CommonAdminOperatorServiceImpl();
        commonAdminOperatorFunctionalities = new CommonAdminOperatorFunctionalitiesImpl();
    }

    @Test
    public void searchPublisher() {
        OperatorController operatorController = new OperatorController();
        //operatorController.searchPublisher("Bloomsbury");

        Assertions.assertEquals(true, commonAdminOperatorFunctionalities.searchPublisher(operatorService, "Bloomsbury"));

    }
}