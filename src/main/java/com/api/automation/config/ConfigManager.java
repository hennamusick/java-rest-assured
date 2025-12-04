package com.api.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration manager to load and manage application properties
 */
public class ConfigManager {
    private static ConfigManager instance;
    private Properties properties;

    private ConfigManager() {
        loadProperties();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadProperties() {
        properties = new Properties();
        try {
            // Try to load from config.properties file
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            // Load default values if file not found
            setDefaultProperties();
        }
    }

    private void setDefaultProperties() {
        properties.setProperty("base.uri", "https://jsonplaceholder.typicode.com");
        properties.setProperty("timeout", "30");
        properties.setProperty("environment", "dev");
    }

    public String getBaseUri() {
        return properties.getProperty("base.uri");
    }

    public int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout", "30"));
    }

    public String getEnvironment() {
        return properties.getProperty("environment", "dev");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
