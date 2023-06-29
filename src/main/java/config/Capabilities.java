package config;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;

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
        androidOptions.setDeviceName("BhaskarMantrala-Device");
        androidOptions.setApp(System.getProperty("user.dir") + "/apps/mda-androidTest-1.0.17-20.apk");
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
