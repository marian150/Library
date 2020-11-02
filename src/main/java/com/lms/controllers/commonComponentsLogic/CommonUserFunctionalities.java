package com.lms.controllers.commonComponentsLogic;

import com.lms.models.entities.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

public interface CommonUserFunctionalities {
    void logout(Label greeting_label, FXMLLoader fxmlLoader);
}
