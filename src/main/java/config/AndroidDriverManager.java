package config;

import interfaces.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager implements MobileDriver {
    public void launch() {
        try {
            Capabilities capabilities = new Capabilities();
            AndroidDriver androidDriver = new AndroidDriver(new URL(PropertyReader.getProperty("SERVER_URL")), capabilities.getAndroidCaps());
//            RemoteWebDriver driver = new RemoteWebDriver(new URL(PropertyReader.getProperty("SERVER_URL")), capabilities.getAndroidCaps());
//            driver.get("https://google.com");
            Thread.sleep(10000);
        } catch (MalformedURLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
