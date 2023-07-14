package interfaces;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

public interface ICaps {

    UiAutomator2Options getAndroidCaps();
    XCUITestOptions getIosCaps();
}
