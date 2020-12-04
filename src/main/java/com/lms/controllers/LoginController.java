package com.lms.controllers;

import com.lms.models.dtos.LoginDTO;
import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.User;
import com.lms.services.LoginService;
import com.lms.validation.base.Error;
import com.lms.validation.err_decorators.*;
import com.lms.validation.err_types.EmailError;
import com.lms.validation.err_types.NameError;
import com.lms.validation.err_types.PasswordError;
import com.lms.validation.err_types.PhoneError;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

public class LoginController {
    @Inject
    private LoginService loginService;
    @Inject
    FXMLLoader fxmlLoader;
    private final Logger logger = LogManager.getLogger(LoginController.class);
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
        Error emailError = new NotNullErrorDecorator(new EmailError());
        Error passError = new NotNullErrorDecorator(new UpperErrorDecorator(new LongLengthErrorDecorator(new ShortLengthErrorDecorator(new PasswordError()))));
        Error nameError = new NotNullErrorDecorator(new OnlyCharErrorDecorator(new LongLengthErrorDecorator(new NameError())));
        Error phoneError = new NotNullErrorDecorator(new OnlyNumbersErrorDecorator(new PhoneError()));
        if (nameError.errors(signUpDTO.getFirstname()) != "Name errors:") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(nameError.errors(signUpDTO.getFirstname()));
            alert.show();
        } else if (nameError.errors(signUpDTO.getLastname()) != "Name errors:") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(nameError.errors(signUpDTO.getLastname()));
            alert.show();
        } else if(emailError.errors(signUpDTO.getEmail()) != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(emailError.errors(signUpDTO.getEmail()));
            alert.show();
        } else if(passError.errors(signUpDTO.getPassword()) != "Password errors:") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(passError.errors(signUpDTO.getPassword()));
            alert.show();
        }else if (phoneError.errors(signUpDTO.getPhone()) != "Phone errors:") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(phoneError.errors(signUpDTO.getPhone()));
            alert.show();
        }else {
            loginService.signup(signUpDTO);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You have successfully send a registration form. \n" +
                    "Your account will be ready in 1 hour.");
            alert.show();
        }
    }

    public void initialize() {
        login_btn_id.setOnAction(event -> {
            User loadedUser = login();
            if(loadedUser != null) {
                switch (loadedUser.getUserType().getTypeId().intValue()) {
                    case 1:
                        ((Stage) login_email_id.getScene().getWindow()).close();
                        try(InputStream fxml = LoginController.class.getResourceAsStream("/fxml/operator.fxml")){
                            Parent root = (Parent) fxmlLoader.load(fxml);
                            OperatorController operatorController = fxmlLoader.getController();
                            operatorController.setGreetingLabel("Hello, " + loadedUser.getFirstName() + " " + loadedUser.getLastName());
                            operatorController.setCurrentUser(loadedUser);
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                            logger.info(loadedUser.getUserId().toString() + " logged in");
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        ((Stage) login_email_id.getScene().getWindow()).close();
                        try(InputStream fxml = LoginController.class.getResourceAsStream("/fxml/reader.fxml")){
                            Parent root = (Parent) fxmlLoader.load(fxml);
                            ReaderController readerController = fxmlLoader.getController();
                            readerController.setGreeting_label("Hello, " + loadedUser.getFirstName() + " " + loadedUser.getLastName());
                            readerController.setCurrentUser(loadedUser);
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                            logger.info(loadedUser.getUserId().toString() + " logged in");
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        ((Stage) login_email_id.getScene().getWindow()).close();
                        try(InputStream fxml = LoginController.class.getResourceAsStream("/fxml/admin.fxml")){
                            Parent root = (Parent) fxmlLoader.load(fxml);
                            AdminController adminController = fxmlLoader.getController();
                            adminController.setGreeting_label("Hello, " + loadedUser.getFirstName() + " " + loadedUser.getLastName());
                            adminController.setCurrentUser(loadedUser);
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                            logger.info(loadedUser.getUserId().toString() + " logged in");
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                }
            } else {
                login_email_id.clear();
                login_passwd_id.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You have entered wrong credentials!");
                alert.show();
            }
        });
        reg_signup_btn.setOnAction(event -> {
            signup();
        });
    }
}
