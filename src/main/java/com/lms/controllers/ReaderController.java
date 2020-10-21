package com.lms.controllers;

import com.lms.models.entities.User;
import com.lms.models.nonpersistentclasses.ReaderTableView;
import com.lms.services.ReaderService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ReaderController {

    @Inject
    private ReaderService readerService;
    @Inject
    FXMLLoader fxmlLoader;

    private User currentUser;
    @FXML
    private Button logout_btn;
    @FXML
    private Label greeting_label;
    @FXML
    private TableView<ReaderTableView> books_table_view;
    @FXML
    private TableColumn<ReaderTableView, String> title_column_id;
    @FXML
    private TableColumn<ReaderTableView, String> author_column_id;
    @FXML
    private TableColumn<ReaderTableView, String> publisher_column_id;
    @FXML
    private TableColumn<ReaderTableView, String> lenddate_column_id;
    @FXML
    private TableColumn<ReaderTableView, String> duedate_column_id;

    ObservableList<ReaderTableView> booksObservableList = FXCollections.observableArrayList();

    public ReaderController() {}

    public List<Object[]> loadBooks() {
        List<Object[]> books = readerService.loadBooks();
        return books;
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
        logout_btn.setOnAction(event -> {
            logout();
        });

        List<Object[]> books = loadBooks();
        Object[][] array = books.toArray(new Object[books.size()][]);
        for(int i = 0; i < array.length; i ++){
            booksObservableList.add(new ReaderTableView(
                    new SimpleStringProperty((String)array[i][0]),
                    new SimpleStringProperty((String)array[i][1]),
                    new SimpleStringProperty((String)array[i][2]),
                    new SimpleStringProperty(array[i][3].toString()),
                    new SimpleStringProperty(array[i][4].toString())));
        }
        title_column_id.setCellValueFactory(new PropertyValueFactory<>("title"));
        author_column_id.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisher_column_id.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        lenddate_column_id.setCellValueFactory(new PropertyValueFactory<>("lenddate"));
        duedate_column_id.setCellValueFactory(new PropertyValueFactory<>("duedate"));
        books_table_view.setItems(booksObservableList);
    }
}
