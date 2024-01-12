package com.simulator.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final String PROPERTIES_FILE = "application.properties";

    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + PROPERTIES_FILE);
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getWebServerURL() {
        return properties.getProperty("webserver.url");
    }
}

