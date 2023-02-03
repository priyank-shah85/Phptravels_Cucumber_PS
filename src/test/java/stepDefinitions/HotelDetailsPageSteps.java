package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.HotelDetailsPage;

public class HotelDetailsPageSteps {
	
	WebDriver driver;
	
	HotelDetailsPage hdp = new HotelDetailsPage(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger;
	
	// Steps for page details
	@And("user can see {string} name under {string} tab")
	public void user_can_see_name_under_tab(String hotelName, String tab) {
		try {
			Assert.assertTrue("Either tab or hotel name is not correct.", hdp.verifyHotelName(hotelName, tab));
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@Then("user can see correct Stars and Discount on {string} tab")
	public void user_can_see_correct_Stars_and_discount_on_tab(String tab) {
		try {
			Assert.assertTrue("Incorrect hotel stars.", hdp.verifyHotelStars(tab));
			Assert.assertTrue("Incorrect hotel discount.", hdp.verifyHotelDiscount(tab));
		}
		catch(Exception e) {
			Assert.assertTrue("Issue while verifying Hotel stars or discount.", false);
		}
	}
	
}
