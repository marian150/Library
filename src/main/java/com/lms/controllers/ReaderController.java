package com.lms.controllers;

import com.lms.models.entities.RentBook;
import com.lms.services.ReaderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.inject.Inject;
import java.util.List;

public class ReaderController {

    @Inject
    private ReaderService readerService;

    @FXML
    private Label greeting_label;
    @FXML
    private TableView books_table_view;
    @FXML
    private TableColumn title_column_id;
    @FXML
    private TableColumn author_column_id;
    @FXML
    private TableColumn publisher_column_id;
    @FXML
    private TableColumn lenddate_column_id;
    @FXML
    private TableColumn duedate_column_id;


    //ObservableList<> books = FXCollections.observableArrayList();

    public ReaderController() {}

    public List<Object[]> loadBooks() {
        List<Object[]> books = readerService.loadBooks();
        return books;
    }

    public void displayBooks(){

    }

    public void initialize() {
        greeting_label.setText("Hello ");
        List<Object[]> books = loadBooks();
        Object[][] array = books.toArray(new Object[books.size()][]);
        for (int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                System.out.println(array[i][j]);
            }
        }
    }
}
