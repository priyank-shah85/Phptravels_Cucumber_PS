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
	
	@And("user can see City Name field title as {string}")
	public void user_can_see_citynName_field_title_as(String expectedTitle) {
		try {
			hp.verifyCityNameTitle(expectedTitle);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user clicks on City Name field")
	public void user_clicks_on_city_name_field() {
		try {
			hp.clickCityName();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user can see {string} text as field value")
	public void user_can_see_text_as_field_value(String expectedMsg) {
		try {
			hp.verifyCityNameValueMsg(expectedMsg);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user enters {string} in City Name field")
	public void user_enters_in_city_name_field(String name) {
		try {
			hp.enterCityName(name);
			Thread.sleep(10000); // Intentionally sleeping driver to allow page to load city names
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user selects {string} from suggestions")
	public void user_selects_from_suggestions(String cityName) {
		try {
			hp.selectCity(cityName);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user selects next month's start date as Checkin date")
	public void user_selects_next_months_start_date_as_checkin_date() {
		try {
			hp.clickCheckinDate();
			hp.calendarControl_Checkin();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user selects Checkout date as 3 days after Checkin date")
	public void user_selects_checkout_date_as_3_days_after_checkin_date() {
		try {
			//hp.clickCheckoutDate();
			hp.calendarControl_Checkout();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user opens Travellers entry box")
	public void user_opens_travellers_entry_box() {
		try {
			hp.clickTravellers();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user selects {string} rooms")
	public void user_selects_rooms(String desiredRooms) {
		try {
			hp.selectRooms(desiredRooms);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}

	@And("user selects {string} adults")
	public void user_selects_adults(String desiredAdults) {
		try {
			hp.selectAdults(desiredAdults);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user selects {string} children")
	public void user_selects_children(String desiredChild) {
		try {
			hp.selectChildren(desiredChild);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user selects first child age as {string} and second child age as {string}")
	public void user_selects_first_child_age_as_and_second_child_age_as(String age1, String age2) {
		try {
			hp.selectChildAges(age1, age2);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user selects Nationality as {string}")
	public void user_selects_nationality_as(String desiredNationality) {
		try {
			hp.selectNationality(desiredNationality);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user clicks on Search icon")
	public void user_clicks_on_search_icon() {
		try {
			hp.clickSearch();
			Thread.sleep(5000);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}

}
