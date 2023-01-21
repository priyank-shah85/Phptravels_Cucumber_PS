package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerDashboard extends BasePage{

	public CustomerDashboard(WebDriver driver) {
		super(driver);
	}
	
	// Initializing wait globally
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
	// Elements for page details
	@FindBy(xpath = "//div[@class='breadcrumb-content']//div[@class='section-heading']//h2")
	WebElement txtWelcomeBack;
	
	@FindBy(xpath = "/html/body/section[1]/div/div[1]/div/div[2]/div[1]/div/div/div[2]/p")
	WebElement txtWalletBalance;
	
	@FindBy(xpath = "/html/body/section[1]/div/div[1]/div/div[2]/div[1]/div/div/div[2]/h4")
	WebElement txtBalanceAmount;
	
	@FindBy(xpath = "//a[@class=' waves-effect' and contains(text(),'My Profile')]")
	WebElement lnkMyProfile;
	
	@FindBy(linkText = "Hotels")
	WebElement lnkHotels;
	
	// Action methods for page details
	public boolean checkPageTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		if(actualTitle.equals(expectedTitle))
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public String verifyWelcomeTxt() {
		WebElement welcomeBack = wait.until(ExpectedConditions.elementToBeClickable(txtWelcomeBack));
		return welcomeBack.getText();
	}
	
	public boolean verifyWalletBalanceFlex(String expectedTxt) {
		WebElement WalletBalance = wait.until(ExpectedConditions.elementToBeClickable(txtWalletBalance));
		String actualTxt = WalletBalance.getText();
		if(actualTxt.equals(expectedTxt)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyWalletBalanceAmount(String currency, String balance) {
		String actualAmount = currency + " " + balance;
		String expectedAmount = txtBalanceAmount.getText();
		//System.out.println("Actual: " + actualAmount + "|Expected: " + expectedAmount);
		if(actualAmount.contentEquals(expectedAmount)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void clickMyProfile() {
		WebElement link = wait.until(ExpectedConditions.elementToBeClickable(lnkMyProfile));
		link.click();
	}
	
	public void clickHotels() {
		WebElement link = wait.until(ExpectedConditions.elementToBeClickable(lnkHotels));
		link.click();
	}

}
