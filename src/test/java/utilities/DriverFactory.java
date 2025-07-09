package utilities;

import config.DeviceCapabilities;
import io.appium.java_client.android.AndroidDriver;

public class DriverFactory {

    private static ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    public static AndroidDriver getDriver() {
        AndroidDriver currentDriver = driver.get();
        if (currentDriver == null) {
            System.out.println("⚠️ Uyarı: Driver henüz oluşturulmamış. null dönüyor.");
        } else {
            System.out.println("📲 Aktif driver alındı.");
        }
        return currentDriver;
    }

    public static void setDriver(String deviceName, String udid, String platformVersion, String systemPort, String appiumPort) {
        System.out.println("🛠 Driver set ediliyor: " + deviceName + " | UDID: " + udid);
        AndroidDriver newDriver = DeviceCapabilities.initializeDriver(deviceName, udid, platformVersion, systemPort, appiumPort);
        if (newDriver != null) {
            driver.set(newDriver);
            System.out.println("✅ Driver başarıyla set edildi.");
        } else {
            System.out.println("❌ HATA: Driver set edilemedi.");
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            System.out.println("🧹 Driver kapatılıyor...");
            driver.get().quit();
            driver.remove();
            System.out.println("✅ Driver temizlendi.");
        } else {
            System.out.println("ℹ️ Kapatılacak aktif driver yok.");
        }
    }
}