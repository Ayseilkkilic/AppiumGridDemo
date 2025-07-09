package config;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DeviceCapabilities {

    public static AndroidDriver initializeDriver(String deviceName, String udid, String platformVersion, String systemPort, String appiumPort) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Zorunlu yetenekler
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", deviceName);
        capabilities.setCapability("appium:udid", udid);
        capabilities.setCapability("appium:platformVersion", platformVersion);
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:systemPort", systemPort);

        // APK ve uygulama ayarları (appium: prefix ZORUNLU)
        capabilities.setCapability("appium:app", System.getProperty("user.dir") + "/apps/entertainment_v_0_22_0_debug.apk");
        capabilities.setCapability("appium:appPackage", "aero.tci.entertainment");
        capabilities.setCapability("appium:appActivity", "aero.tci.entertainment.activity.SplashActivity");
        capabilities.setCapability("appium:appWaitActivity", "*");

        // Diğer yetenekler
        capabilities.setCapability("appium:autoGrantPermissions", true);
        capabilities.setCapability("appium:autoLaunch", true);
        capabilities.setCapability("appium:noReset", false);

        // Log
        System.out.println("🚀 Driver başlatılıyor...");
        System.out.println("📱 Cihaz Adı: " + deviceName);
        System.out.println("🔌 UDID: " + udid);
        System.out.println("🧪 Platform Versiyonu: " + platformVersion);
        System.out.println("🎯 SystemPort: " + systemPort);
        System.out.println("🛠 Appium Port: " + appiumPort);
        System.out.println("📦 APK Yolu: " + System.getProperty("user.dir") + "/apps/entertainment_v_0_22_0_debug.apk");

        AndroidDriver driver = null;
        try {
            URL appiumServerUrl = new URL("http://127.0.0.1:" + appiumPort);
            System.out.println("🌐 Appium URL: " + appiumServerUrl);
            driver = new AndroidDriver(appiumServerUrl, capabilities);
        } catch (MalformedURLException e) {
            System.out.println("❌ Appium URL hatalı: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Driver başlatılamadı: " + e.getMessage());
        }

        return driver;
    }
}