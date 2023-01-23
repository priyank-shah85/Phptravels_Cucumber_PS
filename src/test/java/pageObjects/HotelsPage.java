package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelsPage extends BasePage {

	public HotelsPage(WebDriver driver) {
		super(driver);
	}
	
	// Initializing wait globally
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
	// Elements for page details
	@FindBy(xpath = "//h2[@class='text-center']")
	WebElement msgPageHeading;
	
	@FindBy(className = "select2-selection__rendered")
	WebElement txtCityName;
	
	@FindBy(xpath = "//li[@role='alert']")
	WebElement msgCityName_EnterValue;
	
	@FindBy(className = "select2-search__field")
	WebElement txtFillCityName;
	
	@FindBy(xpath = "//ul[@role='listbox']")
	WebElement searchResultClass1;
	
	@FindBy(id = "checkin")
	WebElement dateCheckin;
	
	@FindBy(id = "checkout")
	WebElement dateCheckout;
	
	@FindBy(xpath = "(//div[@class='datepicker dropdown-menu'])[1]")
	WebElement calendarControl_Checkin;
	
	@FindBy(xpath = "(//div[@class='datepicker dropdown-menu'])[2]")
	WebElement calendarControl_Checkout;
	
	@FindBy(xpath = "//a[@role='button']")
	WebElement btnTravellers;
	
	@FindBy(xpath = "//div[@class='dropdown-menu dropdown-menu-wrap']")
	WebElement boxTravellers;
	
	//@FindBy(className = "roomDec")
	//WebElement removeRoom;
	
	@FindBy(className = "roomInc")
	WebElement addRoom;
	
	//@FindBy(xpath = "(//div[@class='qtyDec'])[2]")
	//WebElement removeAdults;
	
	@FindBy(xpath = "(//div[@class='qtyInc'])[2]")
	WebElement addAdults;
	
	//@FindBy(xpath = "(//div[@class='qtyDec'])[3]")
	//WebElement removeChild;
		
	@FindBy(xpath = "(//div[@class='qtyInc'])[3]")
	WebElement addChild;
	
	@FindBy(id = "append")
	WebElement childAges;
	
	@FindBy(id = "nationality")
	WebElement dropdownNationality;
	
	@FindBy(xpath = "//*[local-name()='svg' and @class='c8LPF-icon']/*[local-name()='path']")
	WebElement iconSearch;
	
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
		WebElement heading = wait.until(ExpectedConditions.elementToBeClickable(msgPageHeading));
		String actualHeading = heading.getText();
		if(actualHeading.contentEquals(expectedHeading)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyCityNameTitle(String expectedTitle) {
		if(txtCityName.getAttribute("title").contentEquals(expectedTitle)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void clickCityName() {
		txtCityName.click();
	}
	
	public boolean verifyCityNameValueMsg(String expectedMsg) {
		if(msgCityName_EnterValue.getText().contentEquals(expectedMsg)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void enterCityName(String name) {
		txtFillCityName.sendKeys(name);
	}
	
	public void selectCity(String cityName) {
		WebElement searchResultClass = wait.until(ExpectedConditions.elementToBeClickable(searchResultClass1));
		List<WebElement> searchResults1 = searchResultClass.findElements(By.tagName("li"));
		// Iterating through all results and click on desired one when found
		for(int i=0; i<searchResults1.size(); i++) {
			List<WebElement> searchResults = searchResultClass.findElements(By.tagName("li"));
			//System.out.println("Element is: "+searchResults.get(i)+"|Value is: "+searchResults.get(i).getText());
			if(searchResults.get(i).getText().contentEquals(cityName)) {
				searchResults.get(i).click();
				break;
			}
			else {
				continue;
			}
		}
	}
	
	public void clickCheckinDate() {
		dateCheckin.click();
	}
	
	public void calendarControl_Checkin() {
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(calendarControl_Checkin));
		
		// Extracting next arrow from calendar header
		List<WebElement> headers = calendarControl_Checkin.findElements(
				By.xpath("(//table[contains(@class,' table-condensed')])[1]//thead//tr//th"));
		for(int i=0; i<headers.size(); i++) {
			List<WebElement> headers1 = calendarControl_Checkin.findElements(
					By.xpath("(//table[contains(@class,' table-condensed')])[1]//thead//tr//th"));
			if(headers1.get(i).getAttribute("class").contentEquals("next")) {
				headers1.get(i).click();
				break;
			}
			else {
				continue;
			}
		}
		
		// Extracting dates and selecting 1st date of the month
		List<WebElement> rows = calendarControl_Checkin.findElements(
				By.xpath("(//table[contains(@class,' table-condensed')])[1]//tbody//tr//td"));
		for(int j=0; j<rows.size(); j++) {
			List<WebElement> dates = calendarControl_Checkin.findElements(
					By.xpath("(//table[contains(@class,' table-condensed')])[1]//tbody//tr//td"));
			if(dates.get(j).getText().contentEquals("1")) {
				dates.get(j).click();
				break;
			}
			else {
				continue;
			}
		}
	}
	
	public void clickCheckoutDate() {
		dateCheckout.click();
	}
	
	public void calendarControl_Checkout() {
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(calendarControl_Checkout));
		
		// Extracting dates and selecting 1st date of the month
		List<WebElement> rows = calendarControl_Checkout.findElements(
				By.xpath("(//table[contains(@class,' table-condensed')])[2]//tbody//tr//td"));
		for(int j=0; j<rows.size(); j++) {
			List<WebElement> dates = calendarControl_Checkout.findElements(
					By.xpath("(//table[contains(@class,' table-condensed')])[2]//tbody//tr//td"));
			if(dates.get(j).getText().contentEquals("4")) {
				dates.get(j).click();
				break;
			}
			else {
				continue;
			}
		}
	}
	
	public void clickTravellers() {
		btnTravellers.click();
	}
	
	public void selectRooms(String desiredRooms) {
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(btnTravellers));
		
		// Clicking the add room button till we add the desired rooms
		int defaultRoom = 1; // Initializing default room value
		int room = Integer.parseInt(desiredRooms);
		while(defaultRoom < room) {
			addRoom.click();
			defaultRoom++;
		}
	}
	
	public void selectAdults(String desiredAdults) {
		// Clicking the add button till we add the desired adults
		int defaultAdults = 2;
		int adult = Integer.parseInt(desiredAdults);
		while(defaultAdults < adult) {
			addAdults.click();
			defaultAdults++;
		}
	}
	
	public void selectChildren(String desiredChild) {
		// clicking the add button till we add the desired children
		int defaultChild = 0;
		int child = Integer.parseInt(desiredChild);
		while(defaultChild < child) {
			addChild.click();
			defaultChild++;
		}
	}
	
	public void selectChildAges(String age1, String age2) {
		//WebElement container = wait.until(ExpectedConditions.elementToBeClickable(childAges));
		ArrayList<String> totalChildAges = new ArrayList<>();
		totalChildAges.add(age1);
		totalChildAges.add(age2);
		for(int i=0; i<totalChildAges.size(); i++) {
			List<WebElement> container = childAges.findElements(By.tagName("li"));
			int elementNum = i+1;
			String ageLoc = "/html/body/section[1]/section/div/div/form/div/div/div[3]/div/div/div/div/ol/li[" + elementNum + "]/div/div/div/select";
			WebElement ageDropdown = container.get(i).findElement(By.xpath(ageLoc));
			Select selectAge = new Select(ageDropdown);
			selectAge.selectByVisibleText(totalChildAges.get(i));
		}
	}
	
	public void selectNationality(String desiredNationality) {
		Select selectNationality = new Select(dropdownNationality);
		selectNationality.selectByVisibleText(desiredNationality);
	}
	
	public void clickSearch() {
		Actions search = new Actions(driver);
		search.moveToElement(iconSearch).click().build().perform();
	}

}
