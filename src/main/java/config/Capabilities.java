package config;

import interfaces.ICaps;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;

public class Capabilities implements ICaps {

    public UiAutomator2Options getAndroidCaps() {
        UiAutomator2Options androidOptions = new UiAutomator2Options();
        androidOptions.setPlatformName("Android");
        androidOptions.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        androidOptions.setDeviceName("Pixel_6_Pro_API_34");
        androidOptions.setAppPackage("com.google.android.youtube");
        androidOptions.setAppActivity("com.google.android.youtube.HomeActivity");
        return androidOptions;
    }

    public XCUITestOptions getIosCaps() {
        XCUITestOptions iosOptions = new XCUITestOptions();
        iosOptions.setPlatformName("iOS");
        iosOptions.setAutomationName("XCuiTest");
        iosOptions.setDeviceName("iPhone 14");
        iosOptions.setApp(System.getProperty("user.dir") + "/apps/SauceLabs-Demo-App.Simulator.zip");
        return iosOptions;
    }

}
