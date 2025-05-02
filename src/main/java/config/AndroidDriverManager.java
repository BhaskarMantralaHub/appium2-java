package config;

import config.annotation.Config;
import interfaces.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import utils.WaitUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Manages the Android driver instance.
 */
public class AndroidDriverManager implements MobileDriver {

    @Config(key = "android.driver", description = "Android driver configuration")
    public void launch() {
        try {
            String serverUrl = Env.getServerUrl();
            System.out.println("Connecting to Appium server at: " + serverUrl);

            // Get capabilities based on environment
            UiAutomator2Options androidOptions = DriverFactoryManager.capabilities().getAndroidCaps();

            // Create and configure the driver
            AndroidDriver androidDriver = new AndroidDriver(new URL(serverUrl), androidOptions);

            // Log configuration for debugging
            System.out.println("Android driver created with capabilities:\n" + androidOptions);

            // Wait for the driver to be ready using our custom wait utility
            WaitUtils.waitForDriverReady(androidDriver);

            // In a real implementation, you would store the driver instance
            // For this example, we'll just quit it
            androidDriver.quit();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to create Android driver: " + e.getMessage(), e);
        }
    }
}
