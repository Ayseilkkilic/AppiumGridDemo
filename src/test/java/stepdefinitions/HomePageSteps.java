package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import utilities.DriverFactory;

public class HomePageSteps {

    @Given("the app is launched")
    public void theAppIsLaunched() {
        System.out.println("App launched on device");
        try {
            Thread.sleep(5000); // Gözlemlemek için 5 saniye beklet
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("the user taps the International button")
    public void theUserTapsInternationalButton() {
        DriverFactory.getDriver().findElement(By.xpath("//*[@text='International']")).click();
    }


}
