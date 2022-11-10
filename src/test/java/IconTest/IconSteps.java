package IconTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import webScraper.MarketAlert;

import java.io.IOException;
import java.text.ParseException;

public class IconSteps {

    MarketAlert marketAlert;

    @Given("I am an administrator of the website and I upload an alert of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType(int arg0) throws ParseException, InterruptedException, IOException {
        marketAlert = new MarketAlert();
        marketAlert.uploadAlertWithType(arg0);
    }

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {
        marketAlert = new MarketAlert();
    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {
        marketAlert.validLogin();
    }

    @Then("I should see {int} alerts")
    public void  iShouldSeeAlerts(int arg0) {
        Boolean result =  marketAlert.countAlerts(arg0);
        Assertions.assertEquals(true, result);
    }

    @And("the icon displayed should be {string}")
    public void theIconDisplayedShouldBe(String arg0) {

       String result =  marketAlert.checkIconFileName(arg0);

        Assertions.assertEquals("test", result);
    }
}
