package config;

import config.annotation.Config;

/**
 * Configuration class for wait-related settings.
 * Provides methods to access wait configuration values.
 */
public class WaitConfig {

    /**
     * Get the default timeout for waits in seconds.
     *
     * @return the default timeout in seconds
     */
    @Config(key = "wait.default.timeout", defaultValue = "30",
            description = "Default timeout for waits in seconds")
    public static int getDefaultTimeout() {
        return PropertyReader.getIntProperty("wait.default.timeout", 30);
    }

    /**
     * Get the default polling interval for waits in milliseconds.
     *
     * @return the default polling interval in milliseconds
     */
    @Config(key = "wait.default.polling", defaultValue = "500",
            description = "Default polling interval for waits in milliseconds")
    public static int getDefaultPollingInterval() {
        return PropertyReader.getIntProperty("wait.default.polling", 500);
    }

    /**
     * Get the driver readiness timeout in seconds.
     * This is the timeout used when waiting for a driver to be ready after creation.
     *
     * @return the driver readiness timeout in seconds
     */
    @Config(key = "wait.driver.readiness.timeout", defaultValue = "10",
            description = "Timeout for driver readiness check in seconds")
    public static int getDriverReadinessTimeout() {
        return PropertyReader.getIntProperty("wait.driver.readiness.timeout", 10);
    }

    /**
     * Get the driver readiness polling interval in milliseconds.
     * This is the polling interval used when waiting for a driver to be ready after creation.
     *
     * @return the driver readiness polling interval in milliseconds
     */
    @Config(key = "wait.driver.readiness.polling", defaultValue = "500",
            description = "Polling interval for driver readiness check in milliseconds")
    public static int getDriverReadinessPollingInterval() {
        return PropertyReader.getIntProperty("wait.driver.readiness.polling", 500);
    }

    /**
     * Get the element visibility timeout in seconds.
     * This is the timeout used when waiting for an element to be visible.
     *
     * @return the element visibility timeout in seconds
     */
    @Config(key = "wait.element.visibility.timeout", defaultValue = "15",
            description = "Timeout for element visibility in seconds")
    public static int getElementVisibilityTimeout() {
        return PropertyReader.getIntProperty("wait.element.visibility.timeout", 15);
    }

    /**
     * Get the element clickability timeout in seconds.
     * This is the timeout used when waiting for an element to be clickable.
     *
     * @return the element clickability timeout in seconds
     */
    @Config(key = "wait.element.clickability.timeout", defaultValue = "15",
            description = "Timeout for element clickability in seconds")
    public static int getElementClickabilityTimeout() {
        return PropertyReader.getIntProperty("wait.element.clickability.timeout", 15);
    }
}
