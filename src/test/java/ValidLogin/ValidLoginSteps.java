package ValidLogin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import webScraper.MarketAlert;

public class ValidLoginSteps {

    MarketAlert marketAlert;

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {
        marketAlert = new MarketAlert();

    }

    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() throws InterruptedException {
        marketAlert.validLogin();

    }

    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/List", marketAlert.url);

    }
}
