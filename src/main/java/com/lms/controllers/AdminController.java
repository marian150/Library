package com.lms.controllers;

import com.lms.models.dtos.SignUpDTO;
import com.lms.services.AdminService;
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

public class AdminController {

    @Inject
    private AdminService adminService;
    @Inject
    FXMLLoader fxmlLoader;
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
        return false;
    }

    public void logout() {
        ((Stage) greeting_label.getScene().getWindow()).close();
        try(InputStream fxml = LoginController.class.getResourceAsStream("/fxml/initial.fxml")){
            Parent root = (Parent) fxmlLoader.load(fxml);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void initialize(){
        logout_btn.setOnAction(event -> {
            logout();
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
