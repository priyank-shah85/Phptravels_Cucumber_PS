package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	// Elements for page details
	@FindBy(xpath = "//h5[@class='modal-title title']")
	WebElement msgPageHeading;
	
	@FindBy(xpath = "//input[@type='email' and @name='email']")
	WebElement txtEmail;
	
	@FindBy(name = "password")
	WebElement txtPassword;
	
	@FindBy(xpath = "//span[text()='Login']")
	WebElement btnLogin;
	
	@FindBy(id = "rememberchb")
	WebElement chkboxRememberMe;
	
	@FindBy(xpath = "//i[@title='Go top']")
	WebElement btnGoTop;
	
	@FindBy(className = "term-box")
	WebElement msgTermsFooter;
	
	// Elements for wrong credentials
	@FindBy(xpath = "//div[@class='message']")
	WebElement msgWrongCredentials;
	
	// Elements for reset password
	@FindBy(xpath = "//label[@for='rememberchb' and @data-bs-toggle='modal']")
	WebElement lnkResetPassword;
	
	@FindBy(xpath = "//input[@class='form-control' and @type='text']")
	WebElement txtResetPasswordEmail;
	
	@FindBy(xpath = "//span[text()='Reset']")
	WebElement btnReset;
	
	@FindBy(className = "btn-close")
	WebElement btnResetPasswordClose;
	
	@FindBy(xpath = "//div[@class='reset-success alert alert-success']")
	WebElement msgResetPasswordSuccess;
	
	// Elements for header links
	@FindBy(className = "main-menu-content")
	WebElement mainMenuContainer;
	
	// Action methods for page details
	public boolean checkPageHeading(String expectedHeading) {
		if(msgPageHeading.getText().equals(expectedHeading))
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public void enterEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void enterPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
	public boolean checkRememberMe() {
		if(chkboxRememberMe.isSelected())
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public void scrollToEndOfPage() {
		Rectangle rect = msgTermsFooter.getRect();
	    int deltaY = rect.y + rect.height;
	    new Actions(driver)
	      .scrollByAmount(0, deltaY)
	      .perform();
		
		//Actions action = new Actions(driver);
		//action.scrollToElement(msgTermsFooter).perform();
	}
	
	public boolean elementGoTopVisible() {
		if(btnGoTop.isDisplayed())
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public void clickGoTop() {
		btnGoTop.click();
	}
	
	public void scrollToTopOfPage() {
		Actions action = new Actions(driver);
		action.scrollToElement(mainMenuContainer).perform();
	}
	
	public boolean elementMainMenupVisible() {
		if(mainMenuContainer.isDisplayed())
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	// Action methods for wrong credentials
	public boolean validateWrongCredentials(String expectedWarning) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		WebElement msgActualWarning = wait.until(ExpectedConditions.elementToBeClickable(msgWrongCredentials));
		String actualWarning = msgActualWarning.getText();
		// System.out.println(actualWarning);
		if(actualWarning.equals(expectedWarning))
		{
			return true;
		} else
		{ 
			return false;
		}
	}
	
	// Action methods for reset password
	public void clickResetPassword() {
		lnkResetPassword.click();
	}
	
	public void clickResetPasswordClose() {
		btnResetPasswordClose.click();
	}
	
	public boolean checkResetPasswordPlaceholder(String expectedtxt) {
		if(txtResetPasswordEmail.getAttribute("placeholder").equals(expectedtxt))
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public void enterResetPasswordEmail(String email) {
		txtResetPasswordEmail.sendKeys(email);
	}
	
	public void clickReset() {
		btnReset.click();
	}
	
	public boolean validateResetPasswordSuccessInfo(String expectedInfo) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		WebElement msgActualInfo = wait.until(ExpectedConditions.elementToBeClickable(msgResetPasswordSuccess));
		String actualInfo = msgActualInfo.getText();
		// System.out.println(actualInfo);
		if(actualInfo.equals(expectedInfo))
		{
			return true;
		} else
		{ 
			return false;
		}
	}
	
	// Action methods for header links
	public boolean verifyHeaderLink(String linkName) {
		boolean b = false;
		List<WebElement> headerLinks = mainMenuContainer.findElements(By.tagName("a"));
		for(int i=0; i<headerLinks.size(); i++)
		{
			if(headerLinks.get(i).getText().equals(linkName))
			{
				b = true;
				break;
			}
		}
		return b;
	}
}
