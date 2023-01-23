package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.HotelSearchResultsPage;

public class HotelSearchResultsPageSteps {
	
	WebDriver driver;
	
	HotelSearchResultsPage hsrp = new HotelSearchResultsPage(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger;
	
	// Steps for page details
	@Then("user redirects to search results page with heading as {string}")
	public void user_redirects_to_search_results_page_with_heading_as(String expectedHeading) {
		try {
			Assert.assertTrue("Hotel searching is not working as expected.", hsrp.verifyPageHeading(expectedHeading));
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("user can see list of hotels as search results")
	public void user_can_see_list_of_hotels_as_search_results() {
		try {
			Assert.assertTrue("No valid hotesl returned as part of search.", hsrp.listOfhotels());
			Thread.sleep(5000);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			Assert.assertTrue(false);
		}
	}

}
