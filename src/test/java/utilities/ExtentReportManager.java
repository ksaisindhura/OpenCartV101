package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; //populate common info on the report
	public ExtentTest test; // creating test case entries in the report and update status of the test methods

	String repName;
	
	public void onStart(ITestContext testContext) {
	
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
		//If we hard-code file name, the previous to latest file will be overridden; to avoid this we generate report name with timestamp
		
		sparkReporter.config().setDocumentTitle("Automation Report"); // Title of report 
		sparkReporter.config().setReportName("Functional Testing"); // name of the report
		//sparkReporter.config().setTheme (Theme.DARK);
		sparkReporter.config().setTheme (Theme.STANDARD);

		extent=new ExtentReports();
		
		//combine ui to populate information
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application","OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-Module", "Customers");
		extent.setSystemInfo("Username", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os= testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser= testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess (ITestResult result) {
		test = extent.createTest(result.getName()); // create a new entry in the report
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.PASS, result.getName()+" successfully executed!"); // update status p/f/s
	}
	
	public void onTestFailure (ITestResult result) {
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" failed!");
		test.log(Status.FAIL, "Test Case FAILED because: "+ result.getThrowable().getMessage());
		
		try {
			String impPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(impPath);
		}catch(IOException e1) {
			e1.printStackTrace();
		}
	}

	public void onTestSkipped (ITestResult result) {
	test=extent.createTest(result.getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.SKIP, result.getName()+" skipped.");
	test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish (ITestContext context) {
	//only onFinish Method will update all above in the report
	extent.flush();
	//open the created report automatically
	String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
	File extentReport = new File(pathOfExtentReport);
	
	try {
		Desktop.getDesktop().browse(extentReport.toURI());//opens report
		}catch(IOException e) {
		e.printStackTrace();
		}
	/*
	try {

		URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);

		// Create the email message
		ImageHtmlEmail email= new ImageHtmlEmail();

		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
		email.setSSLOnConnect(true);
		email.setFrom("pavanoltraining@gmail.com"); //Sender
		email.setSubject("Test Results");
		email.setMsg("Please find Attached Report....");
		email.addTo("pavankumar.busyqa@gmail.com"); //Receiver
		email.attach(url, "extent report", "please check report...");
		email.send(); // send the email

		catch(Exception e)
		{

		e.printStackTrace();
		}*/
	}
}
