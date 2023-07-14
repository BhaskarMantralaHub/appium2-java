package config;

import interfaces.MobileDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class IosDriverManager implements MobileDriver {
    public void launch() {
        final String serverUrl = PropertyReader.getProperty("SERVER_URL");
        System.out.printf("URL is " + serverUrl);
        try {
            IOSDriver androidDriver = new IOSDriver(new URL(serverUrl), DriverFactoryManager.capabilities().getIosCaps());
            Thread.sleep(10000);
        } catch (MalformedURLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
