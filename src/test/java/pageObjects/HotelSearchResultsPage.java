package pageObjects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class HotelSearchResultsPage extends BasePage {

	public HotelSearchResultsPage(WebDriver driver) {
		super(driver);
	}
	
	// Initializing wait globally
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
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
	
	public boolean verifyHotelName(String desiredName) {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(hotelListContainer));
		List<WebElement> hotelLists1 = hotelListContainer.findElements(By.tagName("li"));
		int totalResults = hotelLists1.size();
		boolean results = false;
		if(totalResults > 0) {
			for(int i=0; i<hotelLists1.size(); i++) {
				List<WebElement> hotelLists = hotelListContainer.findElements(By.tagName("li"));
				//WebElement hotelName = hotelLists.get(i);
				//System.out.println("Value of h3: " +hotelName.findElement(By.tagName("h3")).getText());
				if(hotelLists.get(i).findElement(By.tagName("h3")).getText().contains(desiredName)) {
					results = true;
					break;
				}
				else {
					continue;
				}
			}
		}
		else {
			results = false;
		}
		return results;
	}
	
	public boolean verifyHotelMainImage(String name) throws MalformedURLException, IOException {
		List<WebElement> hotelLists1 = hotelListContainer.findElements(By.tagName("li"));
		boolean results = false;
		for(int i=0; i<hotelLists1.size(); i++) {
			List<WebElement> hotelLists = hotelListContainer.findElements(By.tagName("li"));
			//System.out.println("Hotel Name is: " + hotelLists.get(i).findElement(By.tagName("h3")).getText());
			if(hotelLists.get(i).findElement(By.tagName("h3")).getText().contains(name)) {
				
				// Formatting hotel name in lower case to use it in the xpath values
				String hotel_name_lower_case = name.toLowerCase();
				
				// First downloading the image from web location and store in local machine
				String webImageLoc = hotelLists.get(i).findElement
						(By.xpath("//li[@id='" + hotel_name_lower_case + "']//img[@alt='thumbnail']")).getAttribute("data-src");
				//System.out.println("URL is: " + webImageLoc);
				BufferedImage buffimg =ImageIO.read(new URL(webImageLoc));
				File outputFile = new File(System.getProperty("user.dir")+"\\Screenshots\\expected.png");
				ImageIO.write(buffimg, "png", outputFile);
				
				// Taking screenshot of image element from search results
				WebElement thumbnail = hotelLists.get(i).findElement(By.xpath("//li[@id='" + hotel_name_lower_case + "']//img[@alt='thumbnail']"));
				
				Rectangle rect = thumbnail.getRect();
			    int deltaY = rect.y + rect.height;
			    new Actions(driver).scrollByAmount(0, deltaY).perform();
				
			    Screenshot shot = new AShot().takeScreenshot(driver, thumbnail);
				File screenshotFile = new File(System.getProperty("user.dir")+"\\Screenshots\\actual.png");
				ImageIO.write(shot.getImage(), "png", screenshotFile);
				
				// Comparing both images
				BufferedImage expectedImage = ImageIO.read(outputFile); // Getting expected image
				BufferedImage actualImage = shot.getImage(); // Getting actual image
				
				ImageDiffer imgDiff = new ImageDiffer();
				ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage); // Storing diff result
				if(diff.hasDiff()) {
					System.out.println("Images are not same.");
					results = false;
				}
				else {
					System.out.println("Images are same.");
					results = true;
				}
				break;
			}
			else {
				continue;
			}
		}
		return results;
	}
	
	public boolean verifyHotelStars(String Stars, String name) {
		List<WebElement> hotelLists1 = hotelListContainer.findElements(By.tagName("li"));
		boolean results = false;
		for(int i=0; i<hotelLists1.size(); i++) {
			List<WebElement> hotelLists = hotelListContainer.findElements(By.tagName("li"));
			//System.out.println("Hotel Name is: " + hotelLists.get(i).findElement(By.tagName("h3")).getText());
			if(hotelLists.get(i).findElement(By.tagName("h3")).getText().contains(name)) {
				// Formatting hotel name in lower case to use it in the xpath values
				String hotel_name_lower_case = name.toLowerCase();
				
				WebElement stars = hotelLists.get(i).findElement(By.xpath("//li[@id='" + hotel_name_lower_case + "']//span[@class='review__text']"));
				List<WebElement> starLists = stars.findElements(By.tagName("div"));
				int actualStars = starLists.size();
				int expectedStars = Integer.parseInt(Stars);
				//System.out.println("Stars: " + actualStars);
				if(actualStars == expectedStars) {
					results = true;
				}
				else {
					results = false;
				}
				break;
			}
			else {
				continue;
			}
		}
		return results;
	}
	
	public boolean verifyHotelPrice(String price, String stay_night, String name) {
		List<WebElement> hotelLists1 = hotelListContainer.findElements(By.tagName("li"));
		boolean results = false;
		for(int i=0; i<hotelLists1.size(); i++) {
			List<WebElement> hotelLists = hotelListContainer.findElements(By.tagName("li"));
			//System.out.println("Hotel Name is: " + hotelLists.get(i).findElement(By.tagName("h3")).getText());
			if(hotelLists.get(i).findElement(By.tagName("h3")).getText().contains(name)) {
				// Logic to compare the starting price
				WebElement startingPrice = hotelLists.get(i).findElement(By.className("price__num"));
				String actualPrice = startingPrice.findElement(By.tagName("small")).getText() + " " +
						startingPrice.findElement(By.tagName("strong")).getText();
				boolean comparePrice = actualPrice.contentEquals(price);
				
				// Logic to compare the total nights
				WebElement cardPrice = hotelLists.get(i).findElement(By.className("card-price"));
				List<WebElement> nights = cardPrice.findElements(By.tagName("p"));
				boolean compareNights = false;
				for(int j=0; j<nights.size(); j++) {
					List<WebElement> nights1 = cardPrice.findElements(By.tagName("p"));
					if(nights1.get(j).getText().contentEquals(stay_night)) {
						compareNights = true;
					}
				}
				//System.out.println(comparePrice + " " + compareNights);
				// Comparing price and nights with expected data
				if(comparePrice && compareNights) {
					results = true;
				}
				else {
					results = false;
				}
				break;
			}
			else {
				continue;
			}
		}
		return results;
	}
	
	public void clickDetails(String name) {
		List<WebElement> hotelLists1 = hotelListContainer.findElements(By.tagName("li"));
		for(int i=0; i<hotelLists1.size(); i++) {
			List<WebElement> hotelLists = hotelListContainer.findElements(By.tagName("li"));
			//System.out.println("Hotel Name is: " + hotelLists.get(i).findElement(By.tagName("h3")).getText());
			if(hotelLists.get(i).findElement(By.tagName("h3")).getText().contains(name)) {
				WebElement cardPrice = hotelLists.get(i).findElement(By.className("card-price"));

				// Converting the hotel name to lower case
				String hotel_name = "//li[@id='" + name.toLowerCase() + "']//span[@class='ladda-label'][normalize-space()='Details']";
				
				// Getting element for Details button
				WebElement btnDetails = wait.until(ExpectedConditions.elementToBeClickable(cardPrice.findElement(By.xpath(hotel_name))));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", btnDetails);
				break;
			}
			else {
				continue;
			}
		}
	}
	
}
