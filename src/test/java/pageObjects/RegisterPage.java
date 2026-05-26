package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{
	//constructor
	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	//Locators
	@FindBy(xpath="//input[@id='input-lastname']") WebElement xlastname;
	@FindBy(xpath="//input[@id='input-firstname']") WebElement xfirstname;
	@FindBy(xpath="//input[@id='input-email']") WebElement xemail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement xtelephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement xpwd;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement xcnfpwd;
	@FindBy(xpath="//input[@name='agree']") WebElement xagree;
	@FindBy(xpath="//input[@value='Continue']") WebElement xcontinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement xsuccessmsg;

	//ActionMethods
	public void setFirstname(String fname) {
		xfirstname.sendKeys(fname);
	}
	
	public void setLastname(String lname) {
		xlastname.sendKeys(lname);
	}
	public void setEmail(String email) {
		xemail.sendKeys(email);
	}
	public void setTelephone(String telephone) {
		xtelephone.sendKeys(telephone);
	}
	
	public void setPassword(String pwd) {
		xpwd.sendKeys(pwd);
	}
	
	public void setcnfPassword(String cnfpwd) {
		xcnfpwd.sendKeys(cnfpwd);
	}
	
	public void setPrivacypolicy() {
		xagree.click();
	}
	
	public void clickContinue() {
		xcontinue.click();
		
		//sol2
		//btnContinue.submit();
		
		//sol3
		//Actions act=new Actions(driver); 
		//act.moveToElement(btnContinue).click().perform();

		//so14
		//JavascriptExecutor js=(JavascriptExecutor)driver; 
		//js.executeScript("arguments[0].click();", btnContinue);

		//Sol 5
		//btnContinue.sendKeys(Keys.RETURN);

		//Sol 6
		//WebDriverWait mywait =new WebDriverWait(driver, Duration.ofSeconds(10)); 
		//mywait.until (ExpectedConditions.elementToBeClickable (btnContinue)).click();
	}
	
	public String getConfirmationMsg() {
		try {
			return(xsuccessmsg.getText());
		} catch(Exception e) {
			return(e.getMessage());
		}
	}
}
