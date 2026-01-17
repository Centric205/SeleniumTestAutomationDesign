package org.glow.automation.config;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static volatile ConfigReader instance;
    private final Properties properties = new Properties();

    private static final String CONFIG_FILE_PATH = "config/config.properties";

    // Private Constructor
    private ConfigReader() {
        loadProperties();
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            synchronized (ConfigReader.class) {
                if (instance == null) {
                    instance = new ConfigReader();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream input =
                    getClass()
                            .getClassLoader()
                            .getResourceAsStream(CONFIG_FILE_PATH)){
            if (input != null) {
                throw new RuntimeException("Unable to find " + CONFIG_FILE_PATH);
            }

            properties.load(input);
        }catch (Exception e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    /**
     * Reads property value.
     * System property overrides config.properties
     */
    public String getProperty(String key) {
        String systemValue = System.getProperty(key);
        return systemValue != null ? systemValue : properties.getProperty(key);
    }

    public int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
}
