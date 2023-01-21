package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.MyProfile;

public class MyProfileSteps {
	
	WebDriver driver;
	
	MyProfile mp = new MyProfile(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger;
	
	// Steps for page details
	@And("user updates the First Name")
	public void user_updates_the_first_name() {
		try {
			mp.enterFirstName();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user updates the Last Name")
	public void user_updates_the_last_name() {
		try {
			mp.enterLastName();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user updates the Phone")
	public void user_updates_the_phone() {
		try {
			mp.enterPhone();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user updates the Postal Code")
	public void user_updates_the_postal_code() {
		try {
			mp.enterPostalCode();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user updates the Address")
	public void user_updates_the_address() {
		try {
			mp.enterAddress();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user updates the Address 2")
	public void user_updates_the_address_2() {
		try {
			mp.enterAddress2();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user clicks on Update Profile button")
	public void user_clicks_on_update_profile_button() {
		try {
			mp.clickUpdateProfile();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@Then("user can see {string} message with updated values")
	public void user_can_see_message_with_updated_values(String expectedMsg) {
		try {
			boolean compareMsg = mp.verifyUpdateSuccessMsg(expectedMsg);
			if(compareMsg) {
				boolean fName = mp.verifyFirstName();
				boolean lName = mp.verifyLastName();
				boolean phone = mp.verifyPhone();
				boolean code = mp.verifyPostalCode();
				boolean add1 = mp.verifyAddress();
				boolean add2 = mp.verifyAddress2();
				
				if(fName && lName && phone && code && add1 && add2) {
					logger.info("Profile fields updated successfully with correct values.");
					Assert.assertTrue(true);
				}
				else {
					logger.info("One or more profile fields were not updated correctly.");
					Assert.assertTrue(false);
				}
			}
			else {
				logger.info("Profile was not updated successfully.");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}

}
