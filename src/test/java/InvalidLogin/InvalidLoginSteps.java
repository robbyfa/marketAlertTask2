package InvalidLogin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import webScraper.MarketAlert;

public class InvalidLoginSteps {

    MarketAlert marketAlert;

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {
        marketAlert = new MarketAlert();
    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        marketAlert.invalidLogin();
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/Login", marketAlert.url);
    }
}
