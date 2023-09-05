package SDCAutomation;

import java.util.concurrent.TimeUnit;

//import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import utility.*;
import org.testng.Assert;

import pageObjects.Home_Page;
import pageObjects.LogIn_Page;

public class TestNG 
{
  //public WebDriver driver;
  private static WebDriver driver = null;
  Logger log = Logger.getLogger("devpinoyLogger");
  //private static Logger log = Logger.getLogger(Log.class.getName());
  
//@BeforeMethod
  @Test(priority = 1)
  public void LaunchingHomePage() 
  {
	  	DOMConfigurator.configure("log4j.xml");
	    Log.startTestCase("Selenium_Test_001");
	  	//System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
	  	System.setProperty("webdriver.chrome.driver", Config.ChromeDriverPath);
		driver = new ChromeDriver();
		Log.info("New driver instantiated");
		driver.manage().window().maximize();
		Log.info("Browser Home Page Maximised");
		driver.get(Config.STURL);
		//driver.get("https://www.sdc-service-test.com/"); //****** SYS-TEST URL
		//log.debug("Loading the page");	
		//log.info("Loading the page");
		Log.info("Web application launched");
		//driver.get("https://www.sdc-service-uat.com/"); //****** UAT URL
		//WebDriverWait wait=new WebDriverWait(driver, 20);								
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("Implicit wait applied on the driver for 10 seconds");
		System.out.println("\nStep 1: SDC-SysTest Login Page Launched successfully");
		Reporter.log("Step 1: SDC-SysTest Login Page Launched successfully");
  }
  
  //@Test
  @Test(priority = 2)
  public void Login() throws Exception 
  {	    
	    //driver.findElement(By.xpath("//*[@id='signinLink']")).click();
	  	Home_Page.lnk_SignIn().click();	
	  	Log.info("Click action is perfomred on Sign In link");
		
	  	LogIn_Page.txtbx_Password().sendKeys(Config.Username);
	  	//LogIn_Page.txtbx_UserName(driver).sendKeys("clement.arockiasamy@lmtom.london");
	  	//driver.findElement(By.xpath("//*[@id='Email']")).sendKeys("clement.arockiasamy@lmtom.london"); //Enter UserName
		//log.info("Entering the username");
		Log.info("UserName entered");
		
		LogIn_Page.txtbx_Password().sendKeys(Config.Password);
		//LogIn_Page.txtbx_Password(driver).sendKeys("SDClogin123");
		//driver.findElement(By.xpath("//*[@id='Password']")).sendKeys("SDClogin123$"); //Enter Pin
		//log.info("Entering the password");
		Log.info("Password entered");
		
		LogIn_Page.btn_Login().click();
		//driver.findElement(By.xpath("//*[@id='login']")).click();
		Log.info("Clicking the sign-in button");
		
		Assert.assertTrue(driver.findElement(By.xpath(" //*[@id=\"signoutForm\"]/ul/li[2]/a")).isDisplayed());
		System.out.println("\nStep 2: SDC-SysTest Login successfully completed");
		Reporter.log("Step 2: SDC-SysTest Login successfully completed");
  }
  
  //@AfterMethod
  @Test(priority = 3)
  public void SignOut() 
  {
	  driver.findElement(By.xpath(" //*[@id=\"signoutForm\"]/ul/li[2]/a")).click();
	  System.out.println("\nStep 3: Page Sign out successfull");
	  Reporter.log("Step 3: SDC-SysTest Page Signed out successfully");
	  //driver.quit();
	  Log.info("Test Finished");
	  Log.endTestCase("Selenium_Test_001");
  }

}
