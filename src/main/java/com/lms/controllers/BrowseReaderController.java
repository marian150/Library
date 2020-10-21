package com.lms.controllers;

import com.lms.models.entities.User;
import com.lms.models.nonpersistentclasses.BrowseReaderTableView;
import com.lms.models.nonpersistentclasses.SearchReaderTableView;
import com.lms.services.OperatorService;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BrowseReaderController {

    @Inject
    OperatorService operatorService;
    @Inject
    FXMLLoader fxmlLoader;
    @FXML
    private AnchorPane user_details_anchor;
    @FXML
    private TextField browse_scene_reader_id;
    @FXML
    private TextField browse_scene_reader_email;
    @FXML
    private Button browse_scene_reader_search_btn;
    @FXML
    private Button browse_view_choose_reader_btn;
    @FXML
    private TableView<BrowseReaderTableView> browse_reader_table;
    @FXML
    private TableColumn<BrowseReaderTableView, String> browse_view_column_ID;
    @FXML
    private TableColumn<BrowseReaderTableView, String> browse_view_column_fname;
    @FXML
    private TableColumn<BrowseReaderTableView, String> browse_view_column_lname;
    @FXML
    private TableColumn<BrowseReaderTableView, String> browse_view_column_email;
    @FXML
    private TableColumn<BrowseReaderTableView, String> browse_view_column_phone;

    ObservableList<BrowseReaderTableView> readersObservableList = FXCollections.observableArrayList();

    public BrowseReaderController() {}

    public User browseReader() {
        Map<String, String> values = new HashMap<>();
        if(browse_scene_reader_id.getText() != "")
            values.put("readerId", browse_scene_reader_id.getText());
        if(browse_scene_reader_email.getText() != "")
            values.put("email", browse_scene_reader_email.getText());
        return operatorService.browseUser(values);
    }

    public void populateTableView(User user) {
        browse_reader_table.getItems().clear();
        readersObservableList.add(new BrowseReaderTableView(
                new SimpleLongProperty(user.getUserId()),
                new SimpleStringProperty(user.getFirstName()),
                new SimpleStringProperty(user.getLastName()),
                new SimpleStringProperty(user.getEmail()),
                new SimpleStringProperty(user.getPhone())));

        browse_view_column_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        browse_view_column_fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        browse_view_column_lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        browse_view_column_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        browse_view_column_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        browse_reader_table.setItems(readersObservableList);
    }

    public void initialize() {
        browse_scene_reader_search_btn.setOnAction(event -> {
            User getUser = browseReader();
            if(getUser == null) {
                Label noUserFound = new Label("No Record Found.");
                noUserFound.setTextFill(Color.RED);
                user_details_anchor.getChildren().add(noUserFound);
            } else
                populateTableView(browseReader());
            browse_scene_reader_id.clear();
            browse_scene_reader_email.clear();
        });

        browse_view_choose_reader_btn.setOnAction(event -> {
            /*
            ((Stage) browse_reader_table.getScene().getWindow()).close();
            BrowseReaderTableView selectedUser = browse_reader_table.getSelectionModel().getSelectedItem();
            System.out.println(selectedUser.getId() + " " + selectedUser.getFname() + " " + selectedUser.getLname());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/operator.fxml"));
            OperatorController operatorController = loader.getController();
            operatorController.setBrowsedUserDetails(selectedUser);
             */
        });
    }

}
