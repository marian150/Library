package com.lms.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConfigurationSessionFactory {

    private static final SessionFactory sessionFactory = createSessionFactory();

    private static SessionFactory createSessionFactory(){
        try {
            Configuration configuration = new Configuration().configure();
            return configuration.buildSessionFactory();
        } catch(Throwable e){
            System.err.println("Initial SessionFactory creation failed." + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public static void shutdown() {
        getSessionFactory().close();
    }
}
