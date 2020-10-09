package com.lms.controllers;


import com.lms.models.dtos.LoginDTO;
import com.lms.models.entities.User;
import com.lms.services.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class LoginController {
    @Inject
    private LoginService loginService;
    @FXML
    private TextField login_email_id;
    @FXML
    private Button login_btn_id;
    @FXML
    private TextField login_passwd_id;

    public LoginController() {}
    
    public User login() {

        LoginDTO loginDto = new LoginDTO();
        loginDto.setEmail(login_email_id.getText());
        loginDto.setPassword(login_passwd_id.getText());

        User user = loginService.login(loginDto);
        System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getRegDate());
        //System.out.println(loginDto.getEmail() + " " + loginDto.getPassword());
        return user;
    }

    public void initialize() {
        login_btn_id.setOnAction(event -> {
            login();
        });
    }
}
