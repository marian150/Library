package com.lms.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public void display(String title, String message) throws Exception{
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/alertBox.fxml"));
        stage.setTitle("Library");
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root, 406, 170);
        stage.setScene(scene);
        stage.setResizable(false);

        Label label = (Label) scene.lookup("#message");
        label.setText(message);

        Button closeBtn = (Button) scene.lookup("#close_btn");
        closeBtn.setOnAction(e -> stage.close());

        stage.showAndWait();
    }
}
