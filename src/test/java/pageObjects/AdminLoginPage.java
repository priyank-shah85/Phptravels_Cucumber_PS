package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage extends BasePage{

	public AdminLoginPage(WebDriver driver) {
		super(driver);
	}
	
	// Elements for page details
	@FindBy(xpath = "//input[@type='text']")
	WebElement txtEmail;
	
	@FindBy(name = "password")
	WebElement txtPassword;
	
	@FindBy(xpath = "//span[contains(text(),'Login')]")
	WebElement btnLogin;
	
	// Action methods for page details
	public void openNewTab(String url) {
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(url);
	}
	
	public void loginAsAdmin(String email, String pwd) {
		txtEmail.sendKeys(email);
		txtPassword.sendKeys(pwd);
		btnLogin.click();
	}

}
