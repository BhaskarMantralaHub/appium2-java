package config;

import interfaces.MobileDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class IosDriverManager implements MobileDriver {
    public void launch() {
        System.out.printf("URL is " + PropertyReader.getProperty("SERVER_URL"));
        try {
            Capabilities capabilities = new Capabilities();
            IOSDriver androidDriver = new IOSDriver(new URL(PropertyReader.getProperty("SERVER_URL")), capabilities.getIosCaps());
            Thread.sleep(10000);
        } catch (MalformedURLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
