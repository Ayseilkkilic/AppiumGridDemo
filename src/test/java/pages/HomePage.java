package pages;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.LocatorReader;

import java.time.Duration;

public class HomePage {

    private AndroidDriver driver;
    private LocatorReader locatorReader;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        this.locatorReader = new LocatorReader("homePage.json");
    }

    @Step("User clicks on the 'Media Content' button")
    public void clickMediaContent() {
        String id = locatorReader.getAndroidLocator("openInternational");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        element.click();
    }
    @Step("Control Planet Loga")
    public void controlLogo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locatorReader.getAndroidBy("planetLogo")));
    }
    @Step("User clicks on the 'Toolbar Home' button")
    public void clickToolbarHome() {
        String xpath = locatorReader.getAndroidLocator("componentMainToolbarHome");
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }
}