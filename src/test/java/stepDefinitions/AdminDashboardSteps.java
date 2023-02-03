package stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import pageObjects.AdminDashboard;

public class AdminDashboardSteps {
	
	WebDriver driver;
	
	AdminDashboard ad = new AdminDashboard(HomePageSteps.driver);
	
	Logger logger = HomePageSteps.logger;
	
	// Steps for page details
	@And("admin user expands Accounts module")
	public void admin_user_expands_accounts_module() {
		try {
			ad.clickAccountsModule();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("admin user clicks on Customers sub-module")
	public void admin_user_clicks_on_customers_submodule() {
		try {
			ad.clickCustomersSubModule();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("admin user expands Hotels module")
	public void admin_user_expands_hotels_module() {
		try {
			ad.clickHotelsModule();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("admin user clicks on Hotels sub-module")
	public void admin_user_clicks_on_hotels_submodule() {
		try {
			ad.clickHotelsSubModule();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("admin user clicks on Hotels option")
	public void admin_user_clicks_on_hotels_option() {
		try {
			ad.clickHotelsOption();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("admin user clicks on SEARCH link")
	public void admin_user_clicks_on_search_link() {
		try {
			ad.setFocusOnPage();
			ad.clickSearch();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("admin user enters {string} as search keyword")
	public void admin_user_enters_as_search_keyword(String phrase) {
		try {
			ad.enterSearchPhrase(phrase);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("admin user selects {string} as a field to perform search")
	public void admin_user_selects_as_a_field_to_perform_search(String fieldName) {
		try {
			ad.selectDropdownValue(fieldName);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("admin user clicks on GO button")
	public void admin_user_clicks_on_go_button() {
		try {
			ad.clickGo();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And ("admin user noted down Currency and Balance for record with {string} as {string}")
	public void admin_user_noted_down_currency_and_balance_for_record_with_as(String columnName, String value) {
		try {
			ad.searchResultsInTable(columnName, value);
			// logger.info(ad.currency + " and " + ad.balance);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("admin user noted down Stars and Discount for record with {string} as {string}")
	public void admin_user_noted_down_stars_and_discount_for_record_with_as(String name, String value) {
		try {
			ad.searchResultInHotelTable(name, value);
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@And("user closes the Admin portal and move back to Customer portal")
	public void user_closes_the_admin_portal_and_move_back_to_customer_portal() {
		try {
			ad.switchToMainTab();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
		}
	}

}
