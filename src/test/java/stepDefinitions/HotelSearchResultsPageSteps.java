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
	
	@Then("user can see {string} under search results")
	public void user_can_see_under_search_results(String desireName) {
		try {
			Assert.assertTrue("Hotel " + desireName + " is not present in search results.", hsrp.verifyHotelName(desireName));
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			Assert.assertTrue(false);
		}
	}
	
	@And("user can see desired image for {string} hotel")
	public void user_can_see_desired_image_for_hotel(String name) {
		try {
			if(hsrp.verifyHotelMainImage(name)) {
				logger.info("Correct thumbnail for hotel: " + name + ".");
			}
			else {
				logger.info("Incorrect thumbnail for hotel: " + name + ".");
			}
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user can see {string} stars rating for {string} hotel") 
	public void user_can_see_stars_rating_for_hotel(String stars, String name) {
		try {
			Assert.assertTrue("Hotel " + name + " does not show " + stars + " stars.", hsrp.verifyHotelStars(stars, name));
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user can see starting price of {string} of {string} for {string} hotel")
	public void user_can_see_starting_price_of_of_for_hotel(String price, String nights, String name) {
		try {
			Assert.assertTrue("Starting price " + price + " is not correct for " + nights + ".", hsrp.verifyHotelPrice(price, nights, name));
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user clicks on Details button for {string} hotel")
	public void user_clicks_on_details_button_for_hotel(String name) {
		try {
			hsrp.clickDetails(name);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			Assert.assertTrue(false);
		}
	}

}
