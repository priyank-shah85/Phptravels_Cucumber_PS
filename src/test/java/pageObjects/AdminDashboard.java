package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.ScenarioContext;
import enums.Context;

public class AdminDashboard extends BasePage{

	public AdminDashboard(WebDriver driver) {
		super(driver);
	}
	
	ScenarioContext sc = new ScenarioContext();
	
	// Initializing explicit wait globally
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
	// Declare global variable
	public static String currency=null;
	public static String balance=null;
	
	// Elements for page details
	@FindBy(xpath = "//div[@class='text-muted']")
	WebElement descDashboard;
	
	@FindBy(xpath = "(//a[@data-bs-toggle='collapse'] [@aria-controls='collapseLayouts'])[1]")
	WebElement accountsModule;
	
	@FindBy(linkText = "Customers")
	WebElement customersSubModule;
	
	@FindBy(xpath = "//body/div[@id='layoutDrawer']/div[@id='layoutDrawer_nav']/nav[@id='drawerAccordion']/div[@class='drawer-menu']/div[@class='nav']/a[10]")
	WebElement hotelsModule;
	
	@FindBy(xpath = "//nav[@id='drawerAccordionPages']//a[@data-bs-target=\"#Hotels\"]")
	WebElement hotelsSubModule;
	
	@FindBy(linkText = "Hotels")
	WebElement hotelsOption;
	
	@FindBy(className = "clearfix")
	WebElement tableActionBar;
	
	@FindBy(xpath = "//a[@class='xcrud-search-toggle btn btn-default mdc-ripple-upgraded']")
	WebElement lnkSearch;
	
	@FindBy(name = "phrase")
	WebElement txtSearchPhrase;
	
	@FindBy(name = "column")
	WebElement dropdownField;
	
	@FindBy(xpath = "//a[@class='xcrud-action btn btn-primary mdc-ripple-upgraded']")
	WebElement btnGo;
	
	// Elements for working with table
	@FindBy(tagName = "table")
	WebElement tableSearchResults;
	
	// Action methods for page details
	public boolean verifyDashboardPage() {
		WebElement desc = wait.until(ExpectedConditions.elementToBeClickable(descDashboard));
		String expectedTxt = "Sales overview & summary";
		String actualTxt = desc.getText();
		if(actualTxt.equals(expectedTxt)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void clickAccountsModule() {
		accountsModule.click();
	}
	
	public void clickCustomersSubModule() {
		customersSubModule.click();
	}
	
	public void clickHotelsModule() {
		hotelsModule.click();
	}
	
	public void clickHotelsSubModule() {
		hotelsSubModule.click();
	}
	
	public void clickHotelsOption() {
		hotelsOption.click();
	}
	
	public void setFocusOnPage() {
		new Actions(driver).click(tableActionBar).perform();
	}
	
	public void clickSearch() {
		WebElement search = wait.until(ExpectedConditions.elementToBeClickable(lnkSearch));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", search);
	}
	
	public void enterSearchPhrase(String phrase) {
		txtSearchPhrase.sendKeys(phrase);
	}
	
	public void selectDropdownValue(String fieldName) {
		Select select = new Select(dropdownField);
		select.selectByVisibleText(fieldName);
	}
	
	public void clickGo() {
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnGo));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", btn);
	}
	
	public void searchResultsInTable(String columnName, String value) throws InterruptedException {
		// Let's get header first
		List<String> allHeaderNames = new ArrayList<String>();
		Thread.sleep(2000);
		String headerLoc = "//table[@class='xcrud-list table table-hover table-striped table-sm table-hover']//thead//tr//th";
		List<WebElement> allHeadersEle1 = driver.findElements(By.xpath(headerLoc));
		for(int k=0; k<allHeadersEle1.size(); k++) {
			List<WebElement> allHeadersEle = 
					driver.findElements(By.xpath("//table[@class='xcrud-list table table-hover table-striped table-sm table-hover']//thead//tr//th"));
			String headerName = allHeadersEle.get(k).getText();
			allHeaderNames.add(headerName);
		}
		// System.out.println(allHeaderNames);
		 
		// Each row will be a key value pair. So we will use LinkedHashMap so that order can be retained.
		// All maps will be added to a list.
		// List<LinkedHashMap<String, String>> allTableData = new ArrayList<LinkedHashMap<String, String>>();
		 
		// Get total rows count
		String rowLoc = "//table[@class='xcrud-list table table-hover table-striped table-sm table-hover']//tbody//tr";
		List<WebElement> allRowsEle1 = driver.findElements(By.xpath(rowLoc));
		// Starting from 2 as first row is header. Remember xpath index starts from 1
		for (int i = 1; i <= allRowsEle1.size(); i++) {
			List<WebElement> allRowsEle = 
					driver.findElements(By.xpath("//table[@class='xcrud-list table table-hover table-striped table-sm table-hover']//tbody//tr"));
			allRowsEle1 = allRowsEle;
			// Getting specific row with each iteration
			String specificRowLoc = "//table[@class='xcrud-list table table-hover table-striped table-sm table-hover']//tbody//tr[" + i + "]";
			// Locating only cells of specific row.
			List<WebElement> allColumnsEle1 = driver.findElement(By.xpath(specificRowLoc)).findElements(By.tagName("td"));
			// Creating a map to store key-value pair data. It will be created for each iteration of row.
			LinkedHashMap<String, String> eachRowData = new LinkedHashMap<>();
			// Iterating each cell
			for (int j = 0; j < allColumnsEle1.size(); j++) {
				List<WebElement> allColumnsEle = driver.findElement
						(By.xpath("//table[@class='xcrud-list table table-hover table-striped table-sm table-hover']//tbody//tr[" + i + "]"))
						.findElements(By.tagName("td"));
				// Getting cell value
				String cellValue = allColumnsEle.get(j).getText();
				// We will put in to map with header name and value with iteration
				// Get jth index value from allHeaderNames and jth cell value of row
				eachRowData.put(allHeaderNames.get(j), cellValue);
				
				if(allHeaderNames.get(j).equals(columnName) && cellValue.equals(value)) {
					balance = allColumnsEle.get(j+1).getText();
					currency = allColumnsEle.get(j+2).getText();
					// System.out.println("Currency is: " + currency + " & Balance is: " + balance + ".");
					break;
				}
				else {
					continue;
				}
			}
			// System.out.println(eachRowData);
			// After iterating row completely, add in to list.
			// allTableData.add(eachRowData);
		 }
	}
	
	public void switchToMainTab() {
		String currentWindow = driver.getWindowHandle();
		
		for(String windowHandle : driver.getWindowHandles()) {
			if(!currentWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
			}
		}
	}
	
	public void searchResultInHotelTable(String columnName, String value) throws InterruptedException {
		// Let's get header first
		List<String> allHeaderNames = new ArrayList<String>();
		Thread.sleep(2000);
		String headerLoc = "//table[@class='xcrud-list table table-hover table-striped table-sm table-hover']//thead//tr//th";
		List<WebElement> allHeadersEle1 = driver.findElements(By.xpath(headerLoc));
		for(int k=0; k<allHeadersEle1.size(); k++) {
			List<WebElement> allHeadersEle = 
					driver.findElements(By.xpath("//table[@class='xcrud-list table table-hover table-striped table-sm table-hover']//thead//tr//th"));
			String headerName = allHeadersEle.get(k).getText();
			allHeaderNames.add(headerName);
		}
		// System.out.println(allHeaderNames);
		 
		// Each row will be a key value pair. So we will use LinkedHashMap so that order can be retained.
		// All maps will be added to a list.
		// List<LinkedHashMap<String, String>> allTableData = new ArrayList<LinkedHashMap<String, String>>();
		 
		// Get total rows count
		String rowLoc = "//table[@class='xcrud-list table table-hover table-striped table-sm table-hover']//tbody//tr";
		List<WebElement> allRowsEle1 = driver.findElements(By.xpath(rowLoc));
		// Starting from 2 as first row is header. Remember xpath index starts from 1
		for (int i = 1; i <= allRowsEle1.size(); i++) {
			List<WebElement> allRowsEle = 
					driver.findElements(By.xpath("//table[@class='xcrud-list table table-hover table-striped table-sm table-hover']//tbody//tr"));
			allRowsEle1 = allRowsEle;
			// Getting specific row with each iteration
			String specificRowLoc = "//table[@class='xcrud-list table table-hover table-striped table-sm table-hover']//tbody//tr[" + i + "]";
			// Locating only cells of specific row.
			List<WebElement> allColumnsEle1 = driver.findElement(By.xpath(specificRowLoc)).findElements(By.tagName("td"));
			// Creating a map to store key-value pair data. It will be created for each iteration of row.
			LinkedHashMap<String, String> eachRowData = new LinkedHashMap<>();
			// Iterating each cell
			for (int j = 0; j < allColumnsEle1.size(); j++) {
				List<WebElement> allColumnsEle = driver.findElement
						(By.xpath("//table[@class='xcrud-list table table-hover table-striped table-sm table-hover']//tbody//tr[" + i + "]"))
						.findElements(By.tagName("td"));
				String cellValue = null;
				
				// Getting cell value based on Column Name
				switch(allHeaderNames.get(j)) {
				
				case "Name":
					cellValue = allColumnsEle.get(j).findElement(By.tagName("a")).getText();
					break;
					
				case "Stars":
					List<WebElement> totalStars = allColumnsEle.get(j).findElements(By.cssSelector(".fa.fa-star"));
					cellValue = String.valueOf(totalStars.size());
					break;
					
				case "Discount":
					WebElement discount = allColumnsEle.get(j).findElement(By.tagName("input"));
					cellValue = discount.getAttribute("value");
					break;
					
				default:
					cellValue = allColumnsEle.get(j).getText();
					break;
				}
				
				// We will put in to map with header name and value with iteration
				// Get jth index value from allHeaderNames and jth cell value of row
				eachRowData.put(allHeaderNames.get(j), cellValue);
				
				// Storing value of Stars and Discount columns in global Context variables
				if(allHeaderNames.get(j).equals(columnName) && cellValue.equals(value)) {
					List<WebElement> totalStars1 = allColumnsEle.get(j+3).findElements(By.cssSelector(".fa.fa-star"));
					String cellValue1 = String.valueOf(totalStars1.size());
					sc.setContext(Context.HOTEL_STARS, cellValue1);
					sc.setContext(Context.HOTEL_DISCOUNT, 
							allColumnsEle.get(j+8).findElement(By.tagName("input")).getAttribute("value"));
					System.out.println("Stars: " + sc.getContext(Context.HOTEL_STARS) + 
							" & Discount: " + sc.getContext(Context.HOTEL_DISCOUNT) + ".");
					break;
				}
				else {
					continue;
				}
			}
			
			// System.out.println(eachRowData);
			// After iterating row completely, add in to list.
			// allTableData.add(eachRowData);
		 }
	}

}
