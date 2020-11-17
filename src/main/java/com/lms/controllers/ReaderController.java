package com.lms.controllers;

import com.lms.controllers.commonComponentsLogic.CommonUserFunctionalities;
import com.lms.models.entities.User;
import com.lms.models.nonpersistentclasses.ReaderTableView;
import com.lms.services.ReaderService;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.inject.Inject;

import java.util.List;

import java.util.logging.Logger;

public class ReaderController {

    @Inject
    private ReaderService readerService;
    @Inject
    FXMLLoader fxmlLoader;
    @Inject
    private CommonUserFunctionalities commonUserFunctionalities;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private User currentUser;
    @FXML
    private Button logout_btn;
    @FXML
    private Label greeting_label;
    @FXML
    private ListView personal_data_list;
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

    // Object[] to be changed to RentBook entity
    public List<Object[]> loadBooks(Long userId) {
        List<Object[]> books = readerService.loadBooks(userId);
        return books;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void setGreeting_label(String names) {
        greeting_label.setText(names);
    }

    public void displayBooks(List<Object[]> books){
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

    public void displayPersonalData(){
        personal_data_list.getItems().add("Reader №:  " + currentUser.getUserId());
        personal_data_list.getItems().add("First Name:  " + currentUser.getFirstName());
        personal_data_list.getItems().add("Last Name:  " +currentUser.getLastName());
        personal_data_list.getItems().add("Phone:  " + currentUser.getPhone());
        personal_data_list.getItems().add("Email:  " + currentUser.getEmail());
        personal_data_list.getItems().add("Registration:  " + currentUser.getRegDate());
    }
    public void initialize() {
        logout_btn.setOnAction(event -> {
            logger.info(currentUser.getUserId().toString() + " is logging out");
            commonUserFunctionalities.logout(greeting_label, fxmlLoader);
        });

        Platform.runLater(() -> {
            displayBooks(loadBooks(currentUser.getUserId()));
            displayPersonalData();
        });
        /*
        books_table_view.setRowFactory(tv -> {
            for(int i = 0; i < booksObservableList.size(); i++) {
                TableRow<ReaderTableView> row = new TableRow<>();
                LocalDate dueDate = LocalDate.parse(row.getTableView().getItems().get(i).getDuedate());
                BooleanBinding critical = row.getTableView().getItems().get(i).getDuedate().;
                        row.styleProperty().bind(Bindings.when(critical)
                        .then("-fx-background-color: red ;")
                        .otherwise(""));
                return row;
            }
        });

         */
    }
}
