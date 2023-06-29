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
}
