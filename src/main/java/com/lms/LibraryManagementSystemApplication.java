package com.lms;

import com.lms.config.ConfigurationSessionFactory;
import com.lms.config.FXApplicationConfig;
import com.lms.config.NotificationConfiguration;
import com.lms.services.NotificationService;
import com.lms.servicesImpl.NotificationServiceImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import org.jboss.weld.environment.se.Weld;

import javax.inject.Inject;


public class LibraryManagementSystemApplication extends Application {

    private Weld weld;
    private ConfigurationSessionFactory configurationSessionFactory;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        weld = new Weld();
    }

    @Override
    public void start(Stage stage) throws Exception {
        weld.containerId("one").initialize().select(FXApplicationConfig.class).get().start(stage);
        weld.containerId("two").initialize().select(NotificationConfiguration.class).get().start();
    }

    @Override
    public void stop() throws Exception {
        weld.shutdown();
    }

}
