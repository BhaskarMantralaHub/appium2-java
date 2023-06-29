package config;

public class Env {

    public static boolean isLocal() {
        String driverType = PropertyReader.getProperty("DRIVER_TYPE");
        if (driverType == null) throw new IllegalArgumentException("DRIVER_TYPE cannot be null from application properties");
        return driverType.trim().equalsIgnoreCase("LOCAL");
    }

    public static String getDeviceType() {
        String deviceType = PropertyReader.getProperty("DEVICE_TYPE");;
        return deviceType;
    }

}
