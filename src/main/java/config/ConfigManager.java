package config;

import config.annotation.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Manages configuration for the application.
 * Provides methods to access configuration values from different sources.
 */
public class ConfigManager {
    private static ConfigManager instance;
    private final Map<String, String> configCache = new HashMap<>();
    private final Properties properties = new Properties();
    
    private ConfigManager() {
        loadDefaultProperties();
        loadEnvironmentProperties();
        loadSystemProperties();
    }
    
    /**
     * Get the singleton instance of ConfigManager.
     * @return the ConfigManager instance
     */
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
    
    /**
     * Load the default properties from application.properties.
     */
    private void loadDefaultProperties() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException e) {
            System.err.println("Failed to load application.properties: " + e.getMessage());
        }
    }
    
    /**
     * Load environment-specific properties based on the current environment.
     */
    private void loadEnvironmentProperties() {
        String env = System.getProperty("env", "local");
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application-" + env + ".properties")) {
            if (inputStream != null) {
                Properties envProperties = new Properties();
                envProperties.load(inputStream);
                // Override default properties with environment-specific ones
                properties.putAll(envProperties);
            }
        } catch (IOException e) {
            System.err.println("Failed to load environment properties: " + e.getMessage());
        }
    }
    
    /**
     * Load system properties and environment variables.
     * These have the highest priority and will override any other configuration.
     */
    private void loadSystemProperties() {
        // Add system properties
        for (String key : properties.stringPropertyNames()) {
            String systemValue = System.getProperty(key);
            if (systemValue != null) {
                properties.setProperty(key, systemValue);
            }
            
            // Check environment variables (convert property key to env var format)
            String envKey = key.toUpperCase().replace('.', '_');
            String envValue = System.getenv(envKey);
            if (envValue != null) {
                properties.setProperty(key, envValue);
            }
        }
    }
    
    /**
     * Get a configuration value as a string.
     * @param key the configuration key
     * @return the configuration value, or null if not found
     */
    public String getString(String key) {
        return getString(key, null);
    }
    
    /**
     * Get a configuration value as a string.
     * @param key the configuration key
     * @param defaultValue the default value to return if the key is not found
     * @return the configuration value, or the default value if not found
     */
    public String getString(String key, String defaultValue) {
        if (configCache.containsKey(key)) {
            return configCache.get(key);
        }
        
        String value = properties.getProperty(key, defaultValue);
        if (value != null) {
            configCache.put(key, value);
        }
        return value;
    }
    
    /**
     * Get a configuration value as a boolean.
     * @param key the configuration key
     * @return the configuration value as a boolean, or false if not found
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }
    
    /**
     * Get a configuration value as a boolean.
     * @param key the configuration key
     * @param defaultValue the default value to return if the key is not found
     * @return the configuration value as a boolean, or the default value if not found
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        String value = getString(key);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
    
    /**
     * Get a configuration value as an integer.
     * @param key the configuration key
     * @return the configuration value as an integer, or 0 if not found or not a valid integer
     */
    public int getInt(String key) {
        return getInt(key, 0);
    }
    
    /**
     * Get a configuration value as an integer.
     * @param key the configuration key
     * @param defaultValue the default value to return if the key is not found or not a valid integer
     * @return the configuration value as an integer, or the default value if not found or not a valid integer
     */
    public int getInt(String key, int defaultValue) {
        String value = getString(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * Get a configuration value using the Config annotation.
     * @param configAnnotation the Config annotation
     * @return the configuration value, or the default value from the annotation if not found
     */
    public String getConfigValue(Config configAnnotation) {
        String key = configAnnotation.key();
        String defaultValue = configAnnotation.defaultValue();
        
        String value = getString(key, defaultValue);
        
        if (value == null && configAnnotation.required()) {
            throw new IllegalStateException("Required configuration key not found: " + key);
        }
        
        return value;
    }
    
    /**
     * Reload all configuration properties.
     * This will clear the cache and reload all properties from all sources.
     */
    public void reload() {
        configCache.clear();
        properties.clear();
        loadDefaultProperties();
        loadEnvironmentProperties();
        loadSystemProperties();
    }
}
