package com.lms.controllers;


import com.lms.models.dtos.SignUpDTO;
import com.lms.models.entities.User;
import com.lms.models.nonpersistentclasses.ReaderTableView;
import com.lms.models.nonpersistentclasses.SearchReaderTableView;
import com.lms.services.OperatorService;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperatorController {

    @Inject
    private OperatorService operatorService;

    private User currentUser;

    @Inject
    FXMLLoader fxmlLoader;
    @FXML
    private Label greeting_label;
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
    @FXML
    private Button logout_btn;
    @FXML
    private AnchorPane create_reader_anchor;
    @FXML
    private TextField search_reader_fname;
    @FXML
    private TextField search_reader_lname;
    @FXML
    private TextField search_reader_email;
    @FXML
    private TextField search_reader_phone;
    @FXML
    private DatePicker search_reader_from;
    @FXML
    private DatePicker search_reader_to;
    @FXML
    private Button search_reader_btn;
    @FXML
    private TableView<SearchReaderTableView> reader_table_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> readerid_column_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> fname_column_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> lname_column_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> email_column_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> phone_column_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> regdate_column_id;
    @FXML
    private TableColumn<SearchReaderTableView, String> rating_column_id;

    ObservableList<SearchReaderTableView> readersObservableList = FXCollections.observableArrayList();

    public OperatorController() {}

    public boolean createReader(){
        SignUpDTO signUpDTO = new SignUpDTO(fname.getText(), lname.getText(), email.getText(), pass.getText(), phone.getText());
        return operatorService.createReader(signUpDTO);
    }

    public List<User> searchReader() {
        Map<String, String> values = new HashMap<>();
        if(search_reader_fname.getText() != "")
            values.put("firstName", search_reader_fname.getText());
        if(search_reader_lname.getText() != "")
            values.put("lastName", search_reader_lname.getText());
        if(search_reader_email.getText() != "")
            values.put("email", search_reader_email.getText());
        if(search_reader_phone.getText() != "")
            values.put("phone", search_reader_phone.getText());
        if(search_reader_from.getValue() != null)
            values.put("fromDate", search_reader_from.getValue().toString());
        if(search_reader_to.getValue() != null)
            values.put("toDate", search_reader_to.getValue().toString());
        search_reader_fname.clear();
        search_reader_lname.clear();
        search_reader_email.clear();
        search_reader_phone.clear();
        search_reader_from.setValue(null);
        search_reader_to.setValue(null);
        return operatorService.searchReader(values);
    }
    public void displayUsers(List<User> users){
        reader_table_id.getItems().clear();
        for(int i = 0; i < users.size(); i ++){
            readersObservableList.add(new SearchReaderTableView(
                    new SimpleLongProperty(users.get(i).getUserId()),
                    new SimpleStringProperty(users.get(i).getFirstName()),
                    new SimpleStringProperty(users.get(i).getLastName()),
                    new SimpleStringProperty(users.get(i).getEmail()),
                    new SimpleStringProperty(users.get(i).getPhone()),
                    new SimpleStringProperty(users.get(i).getRegDate().toString())));
        }
        readerid_column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname_column_id.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lname_column_id.setCellValueFactory(new PropertyValueFactory<>("lname"));
        email_column_id.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone_column_id.setCellValueFactory(new PropertyValueFactory<>("phone"));
        regdate_column_id.setCellValueFactory(new PropertyValueFactory<>("regdate"));
        reader_table_id.setItems(readersObservableList);
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void setGreeting_label(String names) {
        greeting_label.setText(names);
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

    public void initialize() {
        create_btn.setOnAction(event -> {
            if(createReader()) {
                fname.clear(); lname.clear(); email.clear(); pass.clear(); phone.clear();
                Label createdUser = new Label("Successfully created user.");
                createdUser.setTextFill(Color.GREEN);
                create_reader_anchor.getChildren().add(createdUser);
            } else {
                fname.clear(); lname.clear(); email.clear(); pass.clear(); phone.clear();
                Label wentWrong = new Label("Something went wrong");
                wentWrong.setTextFill(Color.RED);
                create_reader_anchor.getChildren().add(wentWrong);
            }
        });

        search_reader_btn.setOnAction(event -> {
            List<User> result = searchReader();
            displayUsers(result);
        });

        logout_btn.setOnAction(event -> {
            logout();
        });
    }

}
