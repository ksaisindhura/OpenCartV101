package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"master","regression"})
	void acc_reg() {
		
		logger.info("**** Starting TC001_AccountRegistrationTest ****");
		HomePage hp=new HomePage(driver);
		hp.clickMyAcc();
		logger.info("Clicked on My Account...");
		hp.clickRegister();
		logger.info("Clicked on Register Account...");
		
		RegisterPage rp=new RegisterPage(driver);
		
		logger.info("Providing User Details...");
		rp.setFirstname(ranAlpha());
		rp.setLastname(ranAlpha());
		rp.setEmail(ranAlpha()+"@gmail.com");
		rp.setTelephone(ranNumber());
		
		String pwd=ranAlphaNumber();
		rp.setPassword(pwd);
		rp.setcnfPassword(pwd);
		
		rp.setPrivacypolicy();
		rp.clickContinue();
		
		String cnfmsg=rp.getConfirmationMsg();
		logger.info("Validating expected message...");
		if(cnfmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		logger.info("**** Finished TC001_AccountRegistrationTest ****");
		}
}
