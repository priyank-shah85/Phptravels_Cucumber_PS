package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
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
			Thread.sleep(5000);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
}
