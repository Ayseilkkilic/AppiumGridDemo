package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utilities.DriverFactory;

public class HomePage {

    private AndroidDriver driver;

    // Constructor - Driver'ı ThreadLocal'den al
    public HomePage() {
        this.driver = DriverFactory.getDriver();
        if (this.driver == null) {
            System.out.println("⚠️ Uyarı: Driver henüz oluşturulmamış. null dönüyor.");
        }
    }

    // Örnek: International butonuna tıklama
    public void clickMediaContent() {
        System.out.println("🖱️ International butonuna tıklanıyor...");
        driver.findElement(By.xpath("//android.widget.TextView[@text='International']")).click();
    }
}