package config;

import config.annotation.Config;
import interfaces.MobileDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import utils.WaitUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Manages the iOS driver instance.
 */
public class IosDriverManager implements MobileDriver {

    @Config(key = "ios.driver", description = "iOS driver configuration")
    public void launch() {
        try {
            String serverUrl = Env.getServerUrl();
            System.out.println("Connecting to Appium server at: " + serverUrl);

            // Get capabilities based on environment
            XCUITestOptions iosOptions = DriverFactoryManager.capabilities().getIosCaps();

            // Create and configure the driver
            IOSDriver iosDriver = new IOSDriver(new URL(serverUrl), iosOptions);

            // Log configuration for debugging
            System.out.println("iOS driver created with capabilities:\n" + iosOptions);

            // Wait for the driver to be ready using our custom wait utility
            WaitUtils.waitForDriverReady(iosDriver);

            // In a real implementation, you would store the driver instance
            // For this example, we'll just quit it
            iosDriver.quit();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to create iOS driver: " + e.getMessage(), e);
        }
    }
}
