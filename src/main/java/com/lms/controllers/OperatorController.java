package com.lms.controllers;


import com.lms.models.dtos.SignUpDTO;
import com.lms.services.OperatorService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class OperatorController {

    @Inject
    private OperatorService operatorService;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField email;

    @FXML
    private TextField pass;

    @FXML
    private TextField phone;

    @FXML
    private Button create_btn;

    public OperatorController() {}

    public void createReader(){
        SignUpDTO signUpDTO = new SignUpDTO(fname.getText(), lname.getText(), email.getText(), pass.getText(), phone.getText());
        operatorService.createReader(signUpDTO);
    }

    public void initialize() {
        create_btn.setOnAction(event -> {
            System.out.println("clicked!");
            createReader();
        });

    }
}
