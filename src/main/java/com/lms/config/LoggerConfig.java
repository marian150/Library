package com.lms.config;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerConfig {
    private static FileHandler fileHandler;
    private static SimpleFormatter simpleFormatter;

    public static void setup() throws IOException {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.ALL);
        simpleFormatter = new SimpleFormatter();
        fileHandler = new FileHandler("./log.txt", true);
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);
    }
}
