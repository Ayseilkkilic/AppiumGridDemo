package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.HomePage;
import utilities.DriverFactory;
import io.qameta.allure.Step;
import io.qameta.allure.Description;

public class HomePageSteps {

    HomePage homePage;

    @Given("the app is launched")
    @Description("Launches the app and lands on the Home Screen")
    @Step("App is launched")
    public void theAppIsLaunched() {
        System.out.println("App launched on device");
        homePage = new HomePage(DriverFactory.getDriver());

        try {
            Thread.sleep(3000); // gözlem için bekleme
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("the user taps the International button")
    @Description("User taps on the International button on Home Page")
    @Step("User taps the International button")
    public void theUserTapsInternationalButton() {
        homePage.clickMediaContent(); // JSON’daki locator üzerinden
    }
    @When("Control Planet Loga")
    @Description("Control Planet Loga")
    @Step("Control Planet Loga")
    public void controlPlanetLogo() {
        homePage.controlLogo();
    }


}