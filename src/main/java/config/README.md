# Configuration System

This package provides a flexible and robust configuration system for the Appium test framework.

## Features

- **Property-based configuration**: Configure your tests using properties files
- **Environment-specific configuration**: Override configuration for different environments
- **Annotation-based configuration**: Use `@Config` annotations to document and validate configuration
- **Type conversion**: Automatically convert configuration values to the appropriate types
- **Default values**: Specify default values for configuration properties
- **Required properties**: Mark properties as required to ensure they are provided
- **Documentation generation**: Generate documentation for all configuration properties

## Usage

### Basic Usage

```java
// Get a configuration value
String serverUrl = PropertyReader.getProperty("SERVER_URL");

// Get a configuration value with a default
String deviceType = PropertyReader.getProperty("DEVICE_TYPE", "ANDROID");

// Get a boolean configuration value
boolean isRemote = PropertyReader.getBooleanProperty("IS_REMOTE", false);

// Get an integer configuration value
int timeout = PropertyReader.getIntProperty("TIMEOUT", 30);
```

### Using the ConfigManager

```java
// Get the ConfigManager instance
ConfigManager configManager = ConfigManager.getInstance();

// Get a configuration value
String serverUrl = configManager.getString("SERVER_URL");

// Get a configuration value with a default
String deviceType = configManager.getString("DEVICE_TYPE", "ANDROID");

// Get a boolean configuration value
boolean isRemote = configManager.getBoolean("IS_REMOTE", false);

// Get an integer configuration value
int timeout = configManager.getInt("TIMEOUT", 30);

// Reload configuration
configManager.reload();
```

### Using the @Config Annotation

```java
public class MyConfig {
    @Config(key = "my.config.key", defaultValue = "default", description = "My configuration property")
    public static String getMyConfig() {
        return PropertyReader.getProperty("my.config.key", "default");
    }
}
```

### Using the ConfigHelper

```java
// Print all available configuration properties
ConfigHelper.printAvailableProperties();

// Print the current configuration
System.out.println(ConfigHelper.getCurrentConfiguration());

// Generate documentation
ConfigHelper.generateDocumentation("config-docs.md");

// Validate required properties
ConfigHelper.validateRequiredProperties();
```

## Configuration Files

The configuration system supports multiple configuration files:

- `application.properties`: Default configuration
- `application-{env}.properties`: Environment-specific configuration (e.g., `application-remote.properties`)

## Configuration Priority

Configuration values are loaded in the following order (highest priority first):

1. Environment variables (e.g., `SERVER_URL` environment variable)
2. System properties (e.g., `-DSERVER_URL=http://localhost:4723`)
3. Environment-specific properties file (e.g., `application-remote.properties`)
4. Default properties file (`application.properties`)
5. Default values specified in code

## Example

```java
// Define a configuration property
@Config(key = "android.device.name", defaultValue = "Pixel_6_Pro_API_34", description = "Android device name")
public static String getAndroidDeviceName() {
    return PropertyReader.getProperty("android.device.name", "Pixel_6_Pro_API_34");
}

// Use the configuration property
UiAutomator2Options androidOptions = new UiAutomator2Options();
androidOptions.setDeviceName(CapabilityConfig.getAndroidDeviceName());
```
