package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;
    private static final String DEFAULT_ENV = "qa";

    public ConfigReader() {
        loadConfig();
    }

    private void loadConfig() {
        try {
            String env = System.getProperty("env", DEFAULT_ENV);
            String configPath = String.format("config/config.%s.properties", env);
            
            properties = new Properties();
            FileInputStream fis = new FileInputStream(configPath);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + e.getMessage());
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property " + key + " not found in config file");
        }
        return value;
    }

    public String getBrowser() {
        return getProperty("browser");
    }

    public String getBaseUrl() {
        return getProperty("base.url");
    }

    public String getLoginUrl() {
        return getProperty("login.url");
    }

    public String getUsername() {
        return getProperty("test.username");
    }

    public String getPassword() {
        return getProperty("test.password");
    }

    public int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    public int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }

    public int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout"));
    }
} 