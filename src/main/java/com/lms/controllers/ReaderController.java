package com.lms.controllers;

import com.lms.services.ReaderService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.util.List;

public class ReaderController {

    @Inject
    private ReaderService readerService;

    @FXML
    private Label greeting_label;

    public ReaderController() {}

    public List<Object[]> loadBooks() {
        List<Object[]> books = readerService.loadBooks();
        return books;
    }

    public void initialize() {
        greeting_label.setText("Hello ");
        List<Object[]> books = loadBooks();
        for(Object b : books){
            System.out.println(b.toString());
        }


    }
}
