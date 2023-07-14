package config;

import interfaces.ICaps;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

public class RemoteCapabilities implements ICaps {

    public UiAutomator2Options getAndroidCaps() {
        UiAutomator2Options androidOptions = new UiAutomator2Options();
        androidOptions.setPlatformName("Android");
        androidOptions.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        androidOptions.setDeviceName("Pixel_6_Pro_API_34");
        androidOptions.setAppPackage("com.google.android.youtube");
        androidOptions.setAppActivity("com.google.android.youtube.HomeActivity");
        return androidOptions;
    }

    public DesiredCapabilities getRemoteAndroidCaps() {
        // Use Java Client v6.0.0 or above
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("appiumVersion", "2.0.0");
        browserstackOptions.put("automationVersion", "latest");
        browserstackOptions.put("userName", "bhaskarsarma_AdqIBJ");
        browserstackOptions.put("accessKey", "qxnQ46F26zAeDcTgmk6D");
        capabilities.setCapability("bstack:options", browserstackOptions);
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("platformVersion", "13.0");
        capabilities.setCapability("deviceName", "Samsung Galaxy S23 Ultra");
        capabilities.setCapability("app", "bs://23ee57ce1c1f9214c0d65c28ce363cf422d58913");
        capabilities.setCapability("userName", "bhaskarsarma_AdqIBJ");
        capabilities.setCapability("accessKey", "qxnQ46F26zAeDcTgmk6D");
        return capabilities;
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
