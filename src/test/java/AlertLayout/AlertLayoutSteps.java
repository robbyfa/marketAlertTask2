package AlertLayout;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import webScraper.MarketAlert;
import java.io.IOException;
import java.text.ParseException;

public class AlertLayoutSteps {

    MarketAlert marketAlert;

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) throws InterruptedException, ParseException, IOException {
        marketAlert = new MarketAlert();
        marketAlert.addAlerts(arg0);
    }

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {
        marketAlert = new MarketAlert();
    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {
        marketAlert.validLogin();
    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        marketAlert.findIcon();
        Assertions.assertEquals(true, marketAlert.findIcon());
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
        marketAlert.findHeading();
        Assertions.assertEquals(true, marketAlert.findHeading());
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        marketAlert.findDescription();
        Assertions.assertEquals(true, marketAlert.findDescription());
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        marketAlert.findImage();
        Assertions.assertEquals(true, marketAlert.findImage());
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        marketAlert.findPrice();
        Assertions.assertEquals(true, marketAlert.findPrice());
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        marketAlert.findLink();

        Assertions.assertEquals(true, marketAlert.findLink());
    }
}
