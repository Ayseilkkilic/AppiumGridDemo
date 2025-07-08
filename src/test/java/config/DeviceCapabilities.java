package config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DeviceCapabilities {

    public static AndroidDriver initializeDriver(String deviceName, String udid, String platformVersion, String systemPort, String appiumPort) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        // APK yolu
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/apps/entertainment_v_0_22_0_debug.apk");

        // Package ve activity
        capabilities.setCapability("appPackage", "aero.tci.entertainment");
        capabilities.setCapability("appActivity", "aero.tci.entertainment.activity.SplashActivity");
        capabilities.setCapability("appWaitActivity", "*");
        capabilities.setCapability("autoLaunch", true);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);


        AndroidDriver driver = null;
        try {
            // Dinamik Appium portu ile URL oluştur
            URL appiumServerUrl = new URL("http://127.0.0.1:" + appiumPort + "/");
            driver = new AndroidDriver(appiumServerUrl, capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }
}