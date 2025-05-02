package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    static Properties configuration;

    static {
        configuration = new Properties();
        InputStream inputStream = PropertyReader.class
                .getClassLoader()
                .getResourceAsStream("application.properties");
        try {
            configuration.load(inputStream);
            if (inputStream != null) inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String property) {
        return configuration.getProperty(property);
    }

    public static String getProperty(String property, String defaultValue) {
        return configuration.getProperty(property, defaultValue);
    }

    public static int getIntProperty(String property, int defaultValue) {
        String value = configuration.getProperty(property);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static boolean getBooleanProperty(String property, boolean defaultValue) {
        String value = configuration.getProperty(property);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
}
