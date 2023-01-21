package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.AdminDashboard;
import pageObjects.CustomerDashboard;

public class CustomerDashboardSteps {
	
	WebDriver driver;
	CustomerDashboard d = new CustomerDashboard(HomePageSteps.driver);
	AdminDashboard ad = new AdminDashboard(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger;
	
	// Scenarios steps
	@Then("user redirects to Dashboard with title {string}")
	public void user_redirects_to_dashboard_with_title(String expectedTitle) {
		try
		{
			boolean comparePageTitle = d.checkPageTitle(expectedTitle);
			Assert.assertTrue("User redirected to incorrect page.", comparePageTitle);
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@Then("user will see welcome text as {string}")
	public void user_will_see_welcome_text_as(String expectedMsg) {
		try
		{
			String actualMsg = d.verifyWelcomeTxt();
			boolean compareWelcomeMsg = actualMsg.equals(expectedMsg);
			Assert.assertTrue("Welcome message is not as expected:\nCurrent message is: '" +actualMsg + "' | While it should be: '" +expectedMsg + "'.", 
					compareWelcomeMsg);
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@And("user sees {string} flex card")
	public void user_sees_flex_card(String expectedTxt) {
		try {
			boolean compareWalletBalance = d.verifyWalletBalanceFlex(expectedTxt);
			Assert.assertTrue("Wallet Balance flex is missing.", compareWalletBalance);
		}
		catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	
	@Then("user will see same Currency and Balance under Wallet Balance flex card")
	public void user_will_see_same_currency_and_balance_under_wallet_balance_flex_card() {
		try {
			boolean compareWalletBalance = d.verifyWalletBalanceAmount(ad.currency, ad.balance);
			Assert.assertTrue("Wallet balance is not matching with Admin portal.", compareWalletBalance);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user clicks on My Profile option")
	public void user_clicks_on_my_profile_option() {
		try {
			d.clickMyProfile();
		}
		catch(Exception e){
			logger.info(e.getMessage());
		}
	}
	
	@And("user clicks on Hotels link")
	public void user_clicks_on_hotels_link() {
		try {
			d.clickHotels();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}

}
