package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTestCase extends BaseClass {
	
	@Test(groups="datadriven",dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void acc_loginDDT(String email,String pwd,String expres) {
		
		logger.info("****Starting TC003_LoginDataDrivenTestCase*****");
	
		try {
		//Homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAcc();
		hp.clicklogin();		
		logger.info("setting email..");
		
		//Loginpage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		logger.info("setting password");
		lp.setPassword(pwd);
		logger.info("clicking login");
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage map=new MyAccountPage(driver);
		logger.info("checking if logged in");
		boolean targetpage=map.ismyaccpageexists();
		
		if(expres.equalsIgnoreCase("valid")) {
			if(targetpage) {
			logger.info("login success..");
			map.clicklogout();
			logger.info("logged out...");
			Assert.assertTrue(true);
			}
			else {
			logger.info("login failed..");
			Assert.assertFalse(true);
			}
		}else {
			if(targetpage) {
				logger.info("invalid cred- login passed");
				map.clicklogout();
				logger.info("logged out...");
				Assert.assertFalse(true);
			}else {
				logger.info("invalid cred- login failed");
				Assert.assertTrue(true);
			}
		}
		
		}catch(Exception e) {
			Assert.fail();
		}
		
		
		logger.info("****Finished TC003_LoginDataDrivenTestCase****");
	}

}
