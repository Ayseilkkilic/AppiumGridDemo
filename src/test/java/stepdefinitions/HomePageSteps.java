package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.HomePage;

public class HomePageSteps {

    HomePage homePage = new HomePage();

    @Given("the app is launched")
    public void theAppIsLaunched() {
        System.out.println("✅ Uygulama başlatıldı, anasayfadayız.");
    }

    @When("the user taps the International button")
    public void theUserTapsInternationalButton() {
        homePage.clickMediaContent();
    }
}