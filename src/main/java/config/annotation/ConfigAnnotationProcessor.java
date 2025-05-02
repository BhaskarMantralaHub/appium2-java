package config.annotation;

import config.ConfigManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Processes @Config annotations to generate documentation and validate configuration.
 */
public class ConfigAnnotationProcessor {
    
    /**
     * Get all configuration properties defined with @Config annotations.
     * @param clazz the class to scan for @Config annotations
     * @return a list of ConfigProperty objects
     */
    public static List<ConfigProperty> getConfigProperties(Class<?> clazz) {
        List<ConfigProperty> properties = new ArrayList<>();
        
        // Process methods with @Config annotations
        for (Method method : clazz.getDeclaredMethods()) {
            Config config = method.getAnnotation(Config.class);
            if (config != null) {
                ConfigProperty property = new ConfigProperty(
                        config.key(),
                        config.defaultValue(),
                        config.description(),
                        config.required(),
                        method.getReturnType()
                );
                properties.add(property);
            }
        }
        
        return properties;
    }
    
    /**
     * Validate that all required configuration properties are present.
     * @param clazz the class to scan for @Config annotations
     * @throws IllegalStateException if a required configuration property is missing
     */
    public static void validateRequiredProperties(Class<?> clazz) {
        ConfigManager configManager = ConfigManager.getInstance();
        
        for (Method method : clazz.getDeclaredMethods()) {
            Config config = method.getAnnotation(Config.class);
            if (config != null && config.required()) {
                String value = configManager.getString(config.key());
                if (value == null) {
                    throw new IllegalStateException("Required configuration property missing: " + config.key());
                }
            }
        }
    }
    
    /**
     * Generate documentation for all configuration properties.
     * @param classes the classes to scan for @Config annotations
     * @return a string containing documentation for all configuration properties
     */
    public static String generateDocumentation(Class<?>... classes) {
        StringBuilder sb = new StringBuilder();
        sb.append("# Configuration Properties\n\n");
        
        for (Class<?> clazz : classes) {
            sb.append("## ").append(clazz.getSimpleName()).append("\n\n");
            
            List<ConfigProperty> properties = getConfigProperties(clazz);
            for (ConfigProperty property : properties) {
                sb.append("### `").append(property.getKey()).append("`\n");
                sb.append("- **Description**: ").append(property.getDescription()).append("\n");
                sb.append("- **Type**: ").append(property.getType().getSimpleName()).append("\n");
                sb.append("- **Default**: `").append(property.getDefaultValue()).append("`\n");
                sb.append("- **Required**: ").append(property.isRequired()).append("\n\n");
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Represents a configuration property defined with @Config annotation.
     */
    public static class ConfigProperty {
        private final String key;
        private final String defaultValue;
        private final String description;
        private final boolean required;
        private final Class<?> type;
        
        public ConfigProperty(String key, String defaultValue, String description, boolean required, Class<?> type) {
            this.key = key;
            this.defaultValue = defaultValue;
            this.description = description;
            this.required = required;
            this.type = type;
        }
        
        public String getKey() {
            return key;
        }
        
        public String getDefaultValue() {
            return defaultValue;
        }
        
        public String getDescription() {
            return description;
        }
        
        public boolean isRequired() {
            return required;
        }
        
        public Class<?> getType() {
            return type;
        }
    }
}
