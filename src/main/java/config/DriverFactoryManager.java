package config;

import interfaces.ICaps;

public class DriverFactoryManager {

    public static ICaps capabilities() {
        boolean isLocal = Env.isLocal();
        return isLocal ? new Capabilities() : new RemoteCapabilities();
    }

}
