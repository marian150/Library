package com.lms.controllers;

import com.lms.controllers.commonComponentsLogic.CommonAdminOperatorFunctionalities;
import com.lms.controllers.commonComponentsLogic.CommonUserFunctionalities;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.User;
import com.lms.services.AdminService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javax.inject.Inject;
import java.util.logging.Logger;

public class AdminController {

    @Inject
    private AdminService adminService;
    @Inject
    FXMLLoader fxmlLoader;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Inject
    private CommonUserFunctionalities commonUserFunctionalities;
    @Inject
    private CommonAdminOperatorFunctionalities commonAdminOperatorFunctionalities;
    private User currentUser;
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

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void setGreeting_label(String names) {
        greeting_label.setText(names);
    }
    public void initialize(){
        logout_btn.setOnAction(event -> {
            logger.info(currentUser.getUserId().toString() + " is logging out");
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
