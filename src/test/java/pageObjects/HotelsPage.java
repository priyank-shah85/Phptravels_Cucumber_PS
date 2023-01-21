package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelsPage extends BasePage {

	public HotelsPage(WebDriver driver) {
		super(driver);
	}
	
	// Initializing wait globally
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
	// Elements for page details
	@FindBy(xpath = "//h2[@class='text-center']")
	WebElement txtPageHeading;
	
	// Action methods for page details
	public boolean verifyPageTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		if(actualTitle.contentEquals(expectedTitle)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyPageHeading(String expectedHeading) {
		WebElement heading = wait.until(ExpectedConditions.elementToBeClickable(txtPageHeading));
		String actualHeading = heading.getText();
		if(actualHeading.contentEquals(expectedHeading)) {
			return true;
		}
		else {
			return false;
		}
	}

}
