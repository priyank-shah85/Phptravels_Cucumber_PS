package stepDefinitions;

import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;

public class HomePageSteps {
	
	static WebDriver driver;
	HomePage hp;

	static Logger logger; // for logging
	ResourceBundle rb; // for reading properties file
	String br; // to store browser name

	@Before // Junit hook - executes once before starting
	public void setup()
	{
		// for logging
		logger = LogManager.getLogger(this.getClass());
		// Reading config.properties (for browser)
		rb = ResourceBundle.getBundle("config");
		br = rb.getString("browser");
		
		if (br.equals("chrome")) {
			ChromeOptions coptions = new ChromeOptions();
			coptions.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
			driver = new ChromeDriver(coptions);
		} else if (br.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (br.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@After // Junit hook - executes once at end
	public void tearDown(Scenario scenario) {
		System.out.println("Scenario status ======>" + scenario.getStatus());
		if (scenario.isFailed()) {

			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		driver.quit();
	}
	
	// Background steps
	@Given("user navigates to {string}")
	public void user_navigates_to(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@When("user validates the page heading as {string}")
	public void user_validates_the_page_heading_as(String expectedHeading) {
		hp = new HomePage(driver);
		try
		{
			boolean comparePageHeading = hp.checkPageHeading(expectedHeading);
			Assert.assertTrue("User has redirected to wrong page.", comparePageHeading);
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	// Scenarios steps
	@And("user enters Email as {string}")
	public void user_enters_email_as(String email) {
		hp = new HomePage(driver);
		try
		{
			hp.enterEmail(email);
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@And("user enters Password as {string}")
	public void user_enters_password_as(String pwd) {
		hp = new HomePage(driver);
		try
		{
			hp.enterPassword(pwd);
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@And("user clicks on Login button")
	public void user_clicks_on_login_button() {
		hp = new HomePage(driver);
		try
		{
			hp.clickLogin();
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@Then("user will see validation as {string}")
	public void user_will_see_validation_as(String expectedWarning) {
		hp = new HomePage(driver);
		try
		{
			boolean checkWarning = hp.validateWrongCredentials(expectedWarning);
			Assert.assertTrue("Incorrect validation message.", checkWarning);
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@And("user clicks on Reset Password link")
	public void user_clicks_on_reset_password_link() {
		hp = new HomePage(driver);
		try
		{
			hp.clickResetPassword();
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@And("user closes the pop up")
	public void user_closes_the_pop_up() {
		hp = new HomePage(driver);
		try
		{
			hp.clickResetPasswordClose();
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@Then("user will see Remember Me checkbox as unchecked")
	public void user_will_see_remember_me_checkbox_as_unchecked() {
		hp = new HomePage(driver);
		try
		{
			boolean checkRememberMe = hp.checkRememberMe();
			Assert.assertFalse("Remember Me checkbox is still checked after closing Reset Password pop up.", checkRememberMe);
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@And("user will see placeholder in Email field as {string}")
	public void user_will_see_placeholder_in_email_field_as(String expectedtxt) {
		hp = new HomePage(driver);
		try
		{
			boolean checkPlaceholder = hp.checkResetPasswordPlaceholder(expectedtxt);
			Assert.assertTrue("Incorrect or missing placeholder for Email field in Reset Password pop up.", checkPlaceholder);
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@And("user enters the email as {string}")
	public void user_enters_the_email_as(String email) {
		hp = new HomePage(driver);
		try
		{
			hp.enterResetPasswordEmail(email);
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@And("user clicks on Reset button")
	public void user_clicks_on_reset_button() {
		hp = new HomePage(driver);
		try
		{
			hp.clickReset();
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@Then("user will see confirmation as {string}")
	public void user_will_see_confirmation_as(String expectedInfo) {
		hp = new HomePage(driver);
		try
		{
			boolean checkInfo = hp.validateResetPasswordSuccessInfo(expectedInfo);
			Assert.assertTrue("Incorrect validation message.", checkInfo);
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@Then("user will see the {string} on header")
	public void user_will_see_the_on_header(String linkName) {
		hp = new HomePage(driver);
		try
		{
			boolean checkLinkName = hp.verifyHeaderLink(linkName);
			Assert.assertTrue(linkName + " is not present in header section.", checkLinkName);
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@And("user scrolls till Go top button is visible")
	public void user_scrolls_till_go_top_button_is_visible() {
		hp = new HomePage(driver);
		try
		{
			hp.scrollToEndOfPage();
			Assert.assertTrue("Did not scroll till end of the page.", hp.elementGoTopVisible());
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@And("user clicks on Go top button")
	public void user_clicks_on_go_top_button() {
		hp = new HomePage(driver);
		try
		{
			boolean goTopVisible = hp.elementGoTopVisible();
			if(goTopVisible)
			{
				hp.clickGoTop();
			} else
			{
				logger.error("Go Top button is not available on page.");
			}
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}
	
	@Then("user scrolls till top of the page")
	public void user_scrolls_till_top_of_the_page() {
		hp = new HomePage(driver);
		try
		{
			hp.scrollToTopOfPage();
			Assert.assertTrue("Did not scroll to top of the page.", hp.elementMainMenupVisible());
		} catch(Exception e)
		{
			logger.info(e.getMessage());
		}
	}

}
