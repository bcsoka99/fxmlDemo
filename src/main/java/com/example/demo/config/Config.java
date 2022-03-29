package com.example.demo.config;

import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Properties props = new Properties();

    static{
        try {
            props.load(Config.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            // TODO: logging
            e.printStackTrace();
        }

    }

    public static Properties getProps() {
        return props;
    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }
}
