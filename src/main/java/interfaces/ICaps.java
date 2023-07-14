package interfaces;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

public interface ICaps {

    public UiAutomator2Options getAndroidCaps();
    public XCUITestOptions getIosCaps();
}
