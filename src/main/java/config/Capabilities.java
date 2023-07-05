package config;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.remote.CapabilityType;

public class Capabilities {

    private String remoteIOS() {
        return "remote";
    }

    private String localIOS() {
        return "local";
    }

    public String get() {
        return Env.isLocal() ? localIOS() : remoteIOS();
    }

    public UiAutomator2Options getAndroidCaps() {
        UiAutomator2Options androidOptions = new UiAutomator2Options();
        androidOptions.setPlatformName("Android");
        androidOptions.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        androidOptions.setDeviceName("Pixel_6_Pro_API_34");
//        androidOptions.setAppPackage("com.facebook.katana");
        androidOptions.setAppPackage("com.google.android.youtube");
        androidOptions.setAppActivity("com.google.android.youtube.HomeActivity");
//        androidOptions.setPlatformVersion("14");
//        androidOptions.setAppActivity("com.saucelabs.mydemoapp.android.test");
//        androidOptions.setApp(System.getProperty("user.dir") + "/apps/facebook-421-0-0-33-47.apk");

//        androidOptions.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
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
