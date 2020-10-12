package com.lms.controllers;


import com.lms.models.dtos.LoginDTO;
import com.lms.models.entities.User;
import com.lms.services.LoginService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;

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

        LoginDTO loginDto = new LoginDTO(login_email_id.getText(), login_passwd_id.getText());
        User user = loginService.login(loginDto);
        System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getRegDate());
        return user;
    }

    public void initialize() {
        login_btn_id.setOnAction(event -> {
            User pesho = login();
            if(pesho != null) {
                switch ((int)pesho.getTypeId()) {
                    case 1:
                        break;
                    case 2:
                        ((Stage) login_email_id.getScene().getWindow()).close();
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/reader.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("Reader View");
                            stage.setScene(new Scene(parent));
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });
    }
}
