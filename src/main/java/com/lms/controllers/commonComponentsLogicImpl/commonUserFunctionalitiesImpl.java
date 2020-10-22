package com.lms.controllers.commonComponentsLogicImpl;

import com.lms.controllers.LoginController;
import com.lms.controllers.commonComponentsLogic.commonUserFunctionalities;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.io.InputStream;

@Dependent
public class commonUserFunctionalitiesImpl implements commonUserFunctionalities {
    @Override
    public void logout(Label greeting_label, FXMLLoader fxmlLoader) {
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
}
