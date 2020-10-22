package com.lms.controllers;

import com.lms.controllers.commonComponentsLogic.commonUserFunctionalities;
import com.lms.models.dtos.SignUpDTO;
import com.lms.services.AdminService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javax.inject.Inject;

public class AdminController {

    @Inject
    private AdminService adminService;
    @Inject
    FXMLLoader fxmlLoader;
    @Inject
    private commonUserFunctionalities commonUserFunctionalities;
    @FXML
    private Label greeting_label;
    @FXML
    private Button logout_btn;
    @FXML
    private TextField create_fname_id;
    @FXML
    private TextField create_lname_id;
    @FXML
    private TextField create_email_id;
    @FXML
    private TextField create_password_id;
    @FXML
    private TextField create_phone_id;
    @FXML
    private Button create_operator_btn;
    @FXML
    private AnchorPane create_operator_anchor;

    public boolean createOperator(){
        SignUpDTO signUpDTO = new SignUpDTO(create_fname_id.getText(), create_lname_id.getText(), create_email_id.getText(), create_password_id.getText(), create_phone_id.getText());
        return adminService.createOperator(signUpDTO);
    }

    public void initialize(){
        logout_btn.setOnAction(event -> {
            commonUserFunctionalities.logout(greeting_label, fxmlLoader);
        });
        create_operator_btn.setOnAction(event -> {
            create_fname_id.clear(); create_lname_id.clear(); create_email_id.clear(); create_password_id.clear(); create_phone_id.clear();
            if(createOperator()){
                Label createdUser = new Label("Successfully created operator.");
                createdUser.setTextFill(Color.GREEN);
                create_operator_anchor.getChildren().add(createdUser);
            } else{
                Label wentWrong = new Label("Something went wrong");
                wentWrong.setTextFill(Color.RED);
                create_operator_anchor.getChildren().add(wentWrong);
            }
        });
    }
}
