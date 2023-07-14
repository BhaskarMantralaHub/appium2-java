package config;

import interfaces.MobileDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class IosDriverManager implements MobileDriver {
    public void launch() {
        System.out.printf("URL is " + PropertyReader.getProperty("SERVER_URL"));
        try {
            IOSDriver androidDriver = new IOSDriver(new URL(PropertyReader.getProperty("SERVER_URL")), DriverFactoryManager.capabilities().getIosCaps());
            Thread.sleep(10000);
        } catch (MalformedURLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
