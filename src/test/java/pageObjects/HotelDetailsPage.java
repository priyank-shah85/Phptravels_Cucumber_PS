package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.common.base.CharMatcher;

import cucumber.ScenarioContext;
import enums.Context;

public class HotelDetailsPage extends BasePage {

	public HotelDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	// Elements for page details
	@FindBy(linkText = "Hotel Details")
	WebElement tabHotelDetails;
	
	@FindBy(xpath = "//*[@id=\"fadein\"]/section[1]/div[2]/div/div/div/h3[1]")
	WebElement hotelName;
	
	@FindBy(xpath = "/html[1]/body[1]/section[1]/div[2]/div[1]/div[1]/div[2]/p[1]")
	WebElement hotelDiscount;
	
	// Action methods for page details
	public boolean verifyHotelName(String hotel_name, String tab) {
		boolean results = false;
		
		// First verify correct tab
		if(tabHotelDetails.getText().contentEquals(tab)) {
			//Now verify the hotel name heading
			if(hotelName.getText().contentEquals(hotel_name)) {
				//System.out.println("Tab name: " + tabHotelDetails.getText() + "|Heading: " + hotelName.getText());
				results = true;
			}
			else {
				results = false;
			}
		}
		else {
			results = false;
		}
		return results;
	}
	
	public boolean verifyHotelStars(String tab) {
		boolean results = false;
		
		if(tabHotelDetails.getText().contentEquals(tab)) {	
			// Fetching stars display with hotel name on Hotel Details page
			List<WebElement> stars = hotelName.findElements(By.cssSelector(".stars.la.la-star"));
			int actual_stars = stars.size();
			
			// Fetching stars configured on Admin modoule 
			int expected_stars = (int) (new ScenarioContext().getContext(Context.HOTEL_STARS));
			
			// Comparing both values
			if(actual_stars == expected_stars) {
				results = true;
			}
			else {
				results = false;
			}
		}
		else {
			results = false;
		}
		return results;
	}
	
	public boolean verifyHotelDiscount(String tab) {
		boolean results = false;
		
		if(tabHotelDetails.getText().contentEquals(tab)) {
			// Fetching discount amount display on Hotel Details page
			String discount = CharMatcher.inRange('0', '9').retainFrom(hotelDiscount.getText());
			int actual_discount = Integer.valueOf(discount);
			
			// Fetching discount amount configured on Admin module
			int expected_discount = (int) (new ScenarioContext().getContext(Context.HOTEL_DISCOUNT));
			
			// Comparing both values
			if(actual_discount == expected_discount) {
				results = true;
			}
			else {
				results = false;
			}
		}
		else {
			results = false;
		}
		return results;
	}

}
