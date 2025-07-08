package utilities;

import config.DeviceCapabilities;
import io.appium.java_client.android.AndroidDriver;

public class DriverFactory {

    private static ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    public static AndroidDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(String deviceName, String udid, String platformVersion, String systemPort, String appiumPort) {
        driver.set(DeviceCapabilities.initializeDriver(deviceName, udid, platformVersion, systemPort, appiumPort));
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
