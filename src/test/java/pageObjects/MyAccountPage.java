package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	
	//Locators
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement myacctext;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement logout;
	//ActionMethods
	public boolean ismyaccpageexists() {
		try {
		return (myacctext.isDisplayed());
		}catch(Exception e) {
			return false;
		}
	}
	
	public void clicklogout() {
		logout.click();
	}

}
