package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Scrap 
{
	public static WebDriver Launch()
	{
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless"); 
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.setHeadless(true);
        //chromeOptions.setExperimentalOption("excludeSwitches", "disable-popup-blocking");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--no-sandbox");        
        //chromeOptions.setUnhandledPromptBehaviour(null);
        
        /*chromeOptions.AddUserProfilePreference("download.default_directory", DownloadPath);
        chromeOptions.AddUserProfilePreference("disable-popup-blocking", "true");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.AddUserProfilePreference("safebrowsing.enabled", "true");*/
        
        WebDriver driver = new ChromeDriver(chromeOptions);
        //chromeDriver.Manage().Window.Size = new Size(1920, 1080);
       return driver;
	}
}
