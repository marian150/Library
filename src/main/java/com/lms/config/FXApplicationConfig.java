package com.lms.config;

import com.lms.controllers.LoginController;
import com.lms.services.NotificationService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

public class FXApplicationConfig {
    @Inject
    FXMLLoader fxmlLoader;
    public void start(Stage stage) throws IOException{
        try(InputStream fxml = LoginController.class.getResourceAsStream("/fxml/Initial.fxml")){
            Parent root = (Parent) fxmlLoader.load(fxml);
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
