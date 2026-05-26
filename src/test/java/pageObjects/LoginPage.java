package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	//constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//input[@id='input-email']") WebElement xemail;
	@FindBy(xpath="//input[@value='Login']") WebElement xloginbtn;
	@FindBy(xpath="//input[@id='input-password']") WebElement xpwd;
	
	//ActionMethods
	public void setEmail(String mail) {
		xemail.sendKeys(mail);
	}
	
	public void setPassword(String pwd) {
		xpwd.sendKeys(pwd);
	}
	
	public void clickLogin() {
		xloginbtn.click();
	}
}
