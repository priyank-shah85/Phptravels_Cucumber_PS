package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelDetailsPage extends BasePage {

	public HotelDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	// Elements for page details
	@FindBy(linkText = "Hotel Details")
	WebElement tabHotelDetails;
	
	@FindBy(xpath = "//*[@id=\"fadein\"]/section[1]/div[2]/div/div/div/h3[1]")
	WebElement hotelName;
	
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

}
