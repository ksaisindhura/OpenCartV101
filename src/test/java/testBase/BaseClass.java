package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger; //Log4j
	public Properties p;
	
	@BeforeClass(groups= {"sanity","regression","master"})
	@Parameters({"os","browser"})
	public void setup(String os,String browser) throws IOException {
		
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		//For Remote
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			// The URL will be IP Address of Hub Machine + Hub Port+ /wd/hub //http://192.168.13.1:4444/wd/hub or http://localhost:4444/wd/hub
						
			DesiredCapabilities cap =new DesiredCapabilities();
			//OS
			if(os.equalsIgnoreCase("windows")) {
			    cap.setPlatform(Platform.WIN11); 
			}else if(os.equalsIgnoreCase("linux")) {
				cap.setPlatform (Platform.LINUX);
			}else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform (Platform.MAC);
			}else {
				System.out.println("No matching os");
			}
			
			//Browser
			switch(browser.toLowerCase()) {
			case "chrome":cap.setBrowserName("chrome");break;
			case "edge":cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox":cap.setBrowserName("firefox"); break;
			default:
				System.out.println("No Matching browser"); return;
			}
			//launching the browser
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		//for local execution
		else if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		
		switch(browser.toLowerCase()) {
		case "chrome":driver=new ChromeDriver(); break;
		case "edge":driver=new EdgeDriver(); break;
		case "firefox":driver=new FirefoxDriver(); break;
		default:
			System.out.println("Invalid Browser name.."); return;
		}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));  //reading URL from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"sanity","regression","master"})
	public void tearDown() {
		driver.quit();
	}
	
	public String ranAlpha() {
		String alpha=RandomStringUtils.secure().nextAlphabetic(5);
		return alpha;
	}
	
	public String ranNumber() {
		String num=RandomStringUtils.secure().nextNumeric(10);
		return num;
	}
	
	public String ranAlphaNumber() {
		String str=RandomStringUtils.secure().nextAlphanumeric(6);
		return str;
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp =new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot= (TakesScreenshot) driver; 
		File sourceFile =takesScreenshot.getScreenshotAs (OutputType.FILE);

		String targetFilePath =System.getProperty("user.dir")+"\\screenshots\\" +tname+"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

		}

}
