package com.lms.controllers;

import com.lms.models.dtos.SignUpDTO;
import com.lms.services.SignUpService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class SignUpController {
    @Inject
    private SignUpService signUpService;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField phone;

    public SignUpController(){}

    public void signUpReader(){
        SignUpDTO signUpDTO = new SignUpDTO(firstname.getText(), lastname.getText(), email.getText(), password.getText(), phone.getText());
        signUpService.signUp(signUpDTO);
    }
    public void initialize() {
        /*
        register_btn_id.setOnAction(event -> {

        });

        */
    }
}

