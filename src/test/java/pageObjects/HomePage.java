package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	//constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}

	//Locators
	@FindBy(xpath="//a[@title='My Account']") WebElement myAcc;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement register;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement login;
	
	//ActionMethods
	public void clickMyAcc() {
		myAcc.click();
	}
	
	public void clickRegister() {
		register.click();
	}
	
	public void clicklogin() {
		login.click();
	}
}
