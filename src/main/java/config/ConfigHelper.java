package config;

import config.annotation.ConfigAnnotationProcessor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Helper class for working with configuration.
 */
public class ConfigHelper {

    /**
     * Generate documentation for all configuration classes.
     *
     * @param outputPath the path to write the documentation to
     * @throws IOException if an error occurs while writing the documentation
     */
    public static void generateDocumentation(String outputPath) throws IOException {
        String documentation = ConfigAnnotationProcessor.generateDocumentation(
                Env.class,
                CapabilityConfig.class,
                WaitConfig.class
        );

        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write(documentation);
        }
    }

    /**
     * Validate that all required configuration properties are present.
     *
     * @throws IllegalStateException if a required configuration property is missing
     */
    public static void validateRequiredProperties() {
        ConfigAnnotationProcessor.validateRequiredProperties(Env.class);
        ConfigAnnotationProcessor.validateRequiredProperties(CapabilityConfig.class);
        ConfigAnnotationProcessor.validateRequiredProperties(WaitConfig.class);
    }

    /**
     * Print all available configuration properties to the console.
     */
    public static void printAvailableProperties() {
        System.out.println("Available Configuration Properties:");
        System.out.println("==================================");

        Arrays.asList(Env.class, CapabilityConfig.class, WaitConfig.class).forEach(clazz -> {
            System.out.println("\n" + clazz.getSimpleName() + ":");
            ConfigAnnotationProcessor.getConfigProperties(clazz).forEach(property -> {
                System.out.println("  " + property.getKey() + " (" + property.getType().getSimpleName() + ")");
                System.out.println("    Description: " + property.getDescription());
                System.out.println("    Default: " + property.getDefaultValue());
                System.out.println("    Required: " + property.isRequired());
            });
        });
    }

    /**
     * Get the current configuration as a string.
     *
     * @return a string representation of the current configuration
     */
    public static String getCurrentConfiguration() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Configuration:\n");
        sb.append("=====================\n\n");

        sb.append("Environment: ").append(Env.getEnvironment()).append("\n");
        sb.append("Is Local: ").append(Env.isLocal()).append("\n");
        sb.append("Device Type: ").append(Env.getDeviceType()).append("\n");
        sb.append("Server URL: ").append(Env.getServerUrl()).append("\n\n");

        sb.append("Android Capabilities:\n");
        sb.append("  Platform Name: ").append(CapabilityConfig.getAndroidPlatformName()).append("\n");
        sb.append("  Automation Name: ").append(CapabilityConfig.getAndroidAutomationName()).append("\n");
        sb.append("  Device Name: ").append(CapabilityConfig.getAndroidDeviceName()).append("\n");
        sb.append("  App Package: ").append(CapabilityConfig.getAndroidAppPackage()).append("\n");
        sb.append("  App Activity: ").append(CapabilityConfig.getAndroidAppActivity()).append("\n\n");

        sb.append("iOS Capabilities:\n");
        sb.append("  Platform Name: ").append(CapabilityConfig.getIosPlatformName()).append("\n");
        sb.append("  Automation Name: ").append(CapabilityConfig.getIosAutomationName()).append("\n");
        sb.append("  Device Name: ").append(CapabilityConfig.getIosDeviceName()).append("\n");
        sb.append("  App Path: ").append(CapabilityConfig.getIosAppPath()).append("\n\n");

        sb.append("Common Capabilities:\n");
        sb.append("  New Command Timeout: ").append(CapabilityConfig.getNewCommandTimeout()).append("\n");
        sb.append("  Full Reset: ").append(CapabilityConfig.isFullReset()).append("\n");
        sb.append("  No Reset: ").append(CapabilityConfig.isNoReset()).append("\n\n");

        sb.append("Wait Configuration:\n");
        sb.append("  Default Timeout: ")
                .append(PropertyReader.getIntProperty("wait.default.timeout", 30))
                .append(" seconds\n");
        sb.append("  Default Polling Interval: ")
                .append(PropertyReader.getIntProperty("wait.default.polling", 500))
                .append(" ms\n");
        sb.append("  Driver Readiness Timeout: ")
                .append(PropertyReader.getIntProperty("wait.driver.readiness.timeout", 10))
                .append(" seconds\n");
        sb.append("  Element Visibility Timeout: ")
                .append(PropertyReader.getIntProperty("wait.element.visibility.timeout", 15))
                .append(" seconds\n");

        return sb.toString();
    }
}
