package config;

public class DriverFactoryManager {

    public String getDriver() {
        boolean isLocal = Env.isLocal();
        String deviceType = Env.getDeviceType();
        return deviceType + isLocal;
    }

}
