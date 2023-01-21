package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import pageObjects.AdminDashboard;
import pageObjects.AdminLoginPage;

public class AdminLoginPageSteps {
	
	WebDriver driver;
	
	AdminLoginPage alp = new AdminLoginPage(HomePageSteps.driver);
	AdminDashboard ad = new AdminDashboard(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger;
	
	// Scenarios for page details
	@And("user opens Admin portal {string} in new tab")
	public void user_opens_admin_portal_in_new_tab(String url) {
		try {
			alp.openNewTab(url);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user login with {string} and {string} credentials")
	public void user_login_with_and_credentials(String email, String pwd) {
		try {
			alp.loginAsAdmin(email, pwd);
			Assert.assertTrue("User has not correctly logged in to Admin portal.", ad.verifyDashboardPage());
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}

}
