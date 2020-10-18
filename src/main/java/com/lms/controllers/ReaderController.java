package com.lms.controllers;

import com.lms.models.entities.RentBook;
import com.lms.models.nonpersistentclasses.ReaderTableView;
import com.lms.services.ReaderService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReaderController {

    @Inject
    private ReaderService readerService;

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

    public void initialize() {
        greeting_label.setText("Hello ");
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
