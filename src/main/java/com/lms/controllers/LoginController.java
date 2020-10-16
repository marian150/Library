package com.lms.controllers;

import com.lms.models.dtos.LoginDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.User;
import com.lms.services.LoginService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

public class LoginController {
    @Inject
    private LoginService loginService;
    @Inject
    FXMLLoader fxmlLoader;
    @FXML
    private TextField login_email_id;
    @FXML
    private Button login_btn_id;
    @FXML
    private TextField login_passwd_id;
    @FXML
    private TextField reg_first_name_id;
    @FXML
    private TextField reg_last_name_id;
    @FXML
    private TextField reg_email_id;
    @FXML
    private TextField reg_passwd_id;
    @FXML
    private TextField reg_phone_id;
    @FXML
    private Button reg_signup_btn;
    @FXML
    private AnchorPane login_anchor;

    public LoginController() {}
    
    public User login() {

        LoginDTO loginDto = new LoginDTO(login_email_id.getText(), login_passwd_id.getText());
        User user = loginService.login(loginDto);
        return user;
    }
    public void signup(){
        SignUpDTO signUpDTO = new SignUpDTO(reg_first_name_id.getText(), reg_last_name_id.getText(), reg_email_id.getText(), reg_passwd_id.getText(), reg_phone_id.getText());
        loginService.signup(signUpDTO);
    }

    public void initialize() {
        login_btn_id.setOnAction(event -> {
            User pesho = login();
            if(pesho != null) {
                switch ((int)pesho.getTypeId()) {
                    case 1:
                        ((Stage) login_email_id.getScene().getWindow()).close();
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/operator.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("Operator View");
                            stage.setScene(new Scene(parent));
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        ((Stage) login_email_id.getScene().getWindow()).close();
                        try(InputStream fxml = LoginController.class.getResourceAsStream("/fxml/reader.fxml")){
                            Parent root = (Parent) fxmlLoader.load(fxml);
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        ((Stage) login_email_id.getScene().getWindow()).close();
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("Admin View");
                            stage.setScene(new Scene(parent));
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            } else {
                login_email_id.clear();
                login_passwd_id.clear();
                Label wrongCredentials = new Label("You have entered wrong credentials. Try again");
                wrongCredentials.setTextFill(Color.RED);
                login_anchor.getChildren().add(wrongCredentials);
            }
        });
        reg_signup_btn.setOnAction(event -> {
            signup();
        });
    }
}
