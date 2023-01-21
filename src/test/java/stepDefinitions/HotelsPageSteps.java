package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.HotelsPage;

public class HotelsPageSteps {
	
	WebDriver driver;
	
	HotelsPage hp = new HotelsPage(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger;
	
	// Steps for page details
	@Then("user redirects to {string} page")
	public void user_redirects_to_page(String expectedTitle) {
		try {
			hp.verifyPageTitle(expectedTitle);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user can see page heading as {string}")
	public void user_can_see_page_heading_as(String expectedHeading) {
		try {
			hp.verifyPageHeading(expectedHeading);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}

}
