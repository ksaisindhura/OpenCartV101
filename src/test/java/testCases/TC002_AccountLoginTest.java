package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_AccountLoginTest extends BaseClass{
	@Test(groups={"sanity","master"})
	public void acc_login() {
		
		logger.info("****Starting TC002_AccountLoginTest*****");
	
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAcc();
		hp.clicklogin();		
		logger.info("setting email..");
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		logger.info("setting password");
		lp.setPassword(p.getProperty("password"));
		logger.info("clicking login");
		lp.clickLogin();
		
		MyAccountPage map=new MyAccountPage(driver);
		logger.info("checking if logged in");
		if(map.ismyaccpageexists()) {
			logger.info("login success..");
			Assert.assertTrue(true);
		}
		else {
			logger.info("login failed..");
			Assert.assertFalse(true);
		}
		map.clicklogout();
		logger.info("logged out...");
		}catch(Exception e) {
			Assert.fail();
		}
		
		
		logger.info("****Finished TC002_AccountLoginTest****");
	}
}
