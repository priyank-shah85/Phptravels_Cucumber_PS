package pageObjects;

import java.time.Duration;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class MyProfile extends BasePage {

	public MyProfile(WebDriver driver) {
		super(driver);
	}
	
	// Initializing variables globally
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	Faker faker = new Faker();
	
	public String fName;
	public String lName;
	public String phone;
	public String postalCode;
	public String address1;
	public String address2;
	
	// Elements for page details
	@FindBy(name = "firstname")
	WebElement txtFirstName;
	
	@FindBy(name = "lastname")
	WebElement txtLastName;
	
	@FindBy(name = "phone")
	WebElement txtPhone;
	
	@FindBy(name = "zip")
	WebElement txtPostalCode;
	
	@FindBy(name = "address1")
	WebElement txtAddress1;
	
	@FindBy(name = "address2")
	WebElement txtAddress2;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnUpdateProfile;
	
	@FindBy(xpath = "//div[@class='alert alert-success']")
	WebElement msgUpdateSuccess;
	
	// Actions methods for page details
	public void enterFirstName() {
		WebElement fname = wait.until(ExpectedConditions.elementToBeClickable(txtFirstName));
		fName = faker.name().firstName();
		fname.clear();
		fname.sendKeys(fName);
	}
	
	public void enterLastName() {
		lName = faker.name().lastName();
		txtLastName.clear();
		txtLastName.sendKeys(lName);
	}
	
	public void enterPhone() {
		phone = faker.phoneNumber().cellPhone();
		txtPhone.clear();
		txtPhone.sendKeys(phone);
	}
	
	public void enterPostalCode() {
		postalCode = faker.address().zipCode();
		txtPostalCode.clear();
		txtPostalCode.sendKeys(postalCode);
	}
	
	public void enterAddress() {
		address1 = faker.address().buildingNumber();
		txtAddress1.clear();
		txtAddress1.sendKeys(address1);
	}
	
	public void enterAddress2() {
		address2 = faker.address().secondaryAddress();
		txtAddress2.clear();
		txtAddress2.sendKeys(address2);
	}
	
	public void clickUpdateProfile() {
		Rectangle rect = btnUpdateProfile.getRect();
	    int deltaY = rect.y + rect.height;
	    new Actions(driver)
	      .scrollByAmount(0, deltaY)
	      .perform();
		btnUpdateProfile.click();
	}
	
	public boolean verifyUpdateSuccessMsg(String expectedMsg) {
		WebElement msg = wait.until(ExpectedConditions.elementToBeClickable(msgUpdateSuccess));
		String actualMsg = msg.getText();
		if(actualMsg.contentEquals(expectedMsg)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyFirstName() {
		//System.out.println("Stored first name: "+fName+"|Returned: "+txtFirstName.getAttribute("value"));
		if(txtFirstName.getAttribute("value").contentEquals(fName)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyLastName() {
		//System.out.println("Stored last name: "+lName+"|Returned: "+txtLastName.getAttribute("value"));
		if(txtLastName.getAttribute("value").contentEquals(lName)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyPhone() {
		//System.out.println("Stored phone: "+phone+"|Returned: "+txtPhone.getAttribute("value"));
		if(txtPhone.getAttribute("value").contentEquals(phone)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyPostalCode() {
		//System.out.println("Stored code: "+postalCode+"|Returned: "+txtPostalCode.getAttribute("value"));
		if(txtPostalCode.getAttribute("value").contentEquals(postalCode)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyAddress() {
		//System.out.println("Stored address1: "+address1+"|Returned: "+txtAddress1.getAttribute("value"));
		if(txtAddress1.getAttribute("value").contentEquals(address1)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyAddress2() {
		//System.out.println("Stored address2: "+address2+"|Returned: "+txtAddress2.getAttribute("value"));
		if(txtAddress2.getAttribute("value").contentEquals(address2)) {
			return true;
		}
		else {
			return false;
		}
	}

}
