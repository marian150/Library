package com.lms.config;

import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;


public class FXMLLoaderProducer {
    @Inject
    Instance<Object> instance;

    @Produces
    public FXMLLoader createLoader(){
        return new FXMLLoader(null, null, null, new Callback<Class<?>, Object>() {

            @Override
            public Object call(Class<?> aClass) {
                return instance.select(aClass).get();
            }
        });
    }

}
