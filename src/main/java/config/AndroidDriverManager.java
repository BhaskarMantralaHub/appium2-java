package config;

import interfaces.MobileDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverManager implements MobileDriver {
    public void launch() {
        try {
            Capabilities capabilities = new Capabilities();
            AndroidDriver androidDriver = new AndroidDriver(new URL(PropertyReader.getProperty("SERVER_URL")), capabilities.getAndroidCaps());
            Thread.sleep(10000);
        } catch (MalformedURLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
