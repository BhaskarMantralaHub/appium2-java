package config;

import config.annotation.Config;

/**
 * Configuration class for Appium capabilities.
 * Provides methods to access capability configuration values.
 */
public class CapabilityConfig {

    // Android Capabilities
    @Config(key = "android.platform.name", defaultValue = "Android", description = "Android platform name")
    public static String getAndroidPlatformName() {
        return PropertyReader.getProperty("android.platform.name", "Android");
    }

    @Config(key = "android.automation.name", defaultValue = "UiAutomator2", description = "Android automation name")
    public static String getAndroidAutomationName() {
        return PropertyReader.getProperty("android.automation.name", "UiAutomator2");
    }

    @Config(key = "android.device.name", defaultValue = "Pixel_6_Pro_API_34", description = "Android device name")
    public static String getAndroidDeviceName() {
        return PropertyReader.getProperty("android.device.name", "Pixel_6_Pro_API_34");
    }

    @Config(key = "android.app.package", defaultValue = "com.google.android.youtube", description = "Android app package")
    public static String getAndroidAppPackage() {
        return PropertyReader.getProperty("android.app.package", "com.google.android.youtube");
    }

    @Config(key = "android.app.activity", defaultValue = "com.google.android.youtube.HomeActivity",
            description = "Android app activity")
    public static String getAndroidAppActivity() {
        return PropertyReader.getProperty("android.app.activity",
                "com.google.android.youtube.HomeActivity");
    }

    // iOS Capabilities
    @Config(key = "ios.platform.name", defaultValue = "iOS", description = "iOS platform name")
    public static String getIosPlatformName() {
        return PropertyReader.getProperty("ios.platform.name", "iOS");
    }

    @Config(key = "ios.automation.name", defaultValue = "XCuiTest", description = "iOS automation name")
    public static String getIosAutomationName() {
        return PropertyReader.getProperty("ios.automation.name", "XCuiTest");
    }

    @Config(key = "ios.device.name", defaultValue = "iPhone 14", description = "iOS device name")
    public static String getIosDeviceName() {
        return PropertyReader.getProperty("ios.device.name", "iPhone 14");
    }

    @Config(key = "ios.app.path", defaultValue = "/apps/SauceLabs-Demo-App.Simulator.zip",
            description = "iOS app path (relative to user.dir)")
    public static String getIosAppPath() {
        return PropertyReader.getProperty("ios.app.path", "/apps/SauceLabs-Demo-App.Simulator.zip");
    }

    // Common Capabilities
    @Config(key = "appium.new.command.timeout", defaultValue = "60",
            description = "New command timeout in seconds")
    public static int getNewCommandTimeout() {
        return PropertyReader.getIntProperty("appium.new.command.timeout", 60);
    }

    @Config(key = "appium.full.reset", defaultValue = "false",
            description = "Whether to perform a full reset")
    public static boolean isFullReset() {
        return PropertyReader.getBooleanProperty("appium.full.reset", false);
    }

    @Config(key = "appium.no.reset", defaultValue = "true",
            description = "Whether to skip reset")
    public static boolean isNoReset() {
        return PropertyReader.getBooleanProperty("appium.no.reset", true);
    }
}
