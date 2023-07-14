package config;

import interfaces.MobileDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager implements MobileDriver {
    public void launch() {
        try {
            System.out.println("Remote URL is " + PropertyReader.getProperty("SERVER_URL"));
            UiAutomator2Options androidOptions = new UiAutomator2Options();
            AndroidDriver androidDriver = new AndroidDriver(new URL(PropertyReader.getProperty("SERVER_URL")), androidOptions);
            androidDriver.quit();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
