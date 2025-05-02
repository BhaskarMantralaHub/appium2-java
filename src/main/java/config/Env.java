package config;

public class Env {

    public static boolean isLocal() {
        String isRemote = PropertyReader.getProperty("IS_REMOTE");
        if (isRemote == null) return true;
        return !isRemote.trim().equalsIgnoreCase("TRUE");
    }

    public static String getDeviceType() {
        String deviceType = PropertyReader.getProperty("DEVICE_TYPE");;
        return deviceType;
    }

    public static String getServerUrl() {
        return PropertyReader.getProperty("SERVER_URL");
    }

    public static String getEnvironment() {
        return PropertyReader.getProperty("ENV");
    }

}
