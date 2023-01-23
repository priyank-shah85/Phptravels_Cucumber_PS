package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelSearchResultsPage extends BasePage {

	public HotelSearchResultsPage(WebDriver driver) {
		super(driver);
	}
	
	// Elements for page details
	@FindBy(className = "sec__title_list")
	WebElement pageHeading;
	
	@FindBy(xpath = "//section[@id='data']//ul")
	WebElement hotelListContainer;
	
	// Action methods for page details
	public boolean verifyPageHeading(String expectedHeading) {
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(pageHeading));
		if(pageHeading.getText().contentEquals(expectedHeading)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean listOfhotels() {
		List<WebElement> hotelLists = hotelListContainer.findElements(By.tagName("li"));
		int totalResults = hotelLists.size();
		System.out.println("Total results: " +totalResults);
		int totalResultsWithGap = 0;
		if(totalResults > 0) {
			for(int i=0; i<hotelLists.size(); i++) {
				List<WebElement> hotelLists1 = hotelListContainer.findElements(By.tagName("li"));
				if(hotelLists1.get(i).getAttribute("class").contentEquals("gap")) {
					totalResultsWithGap++;
				}
			}
			System.out.println("Total results with gap class: " +totalResultsWithGap);
			if(totalResultsWithGap == totalResults) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

}
