package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;

public class Home_Page extends BaseClass
{
  private static WebElement element = null;
       
  public Home_Page(WebDriver driver)
  {
  	super(driver);
  }    
 
 public static WebElement lnk_SignIn() throws Exception
 {
	 try{
		 	element = driver.findElement(By.xpath("//*[@id='signinLink']")); 
		 	Log.info("--Sign-In link is found on the Home Page");
        }
	    catch (Exception e)
        {
    		Log.error("--Sign-In link is not found on the Home Page");
    		throw(e);
    	}
    	return element;	 
 } 
 public static WebElement txt_PageHeading(WebDriver driver)
 {
    
	/*element = driver.findElement(By.xpath("/html/body/div[2]/div/div/h1"));    
    return element;*/
    
    try{
    	 	element = driver.findElement(By.xpath("/html/body/div[2]/div/div/h1"));  
    	 	Log.info("--PageHeading is found on the Home Page");
       }
	    catch (Exception e)
       {
	    	Log.error("--PageHeading is not found on the Home Page");
	    	throw(e);
   		}
   		return element;	 
 } 
 public static WebElement txt_ReleaseVersion(WebDriver driver)
 {
     element = driver.findElement(By.xpath("/html/body/div[2]/div/footer/p/text()"));
     return element; 
 }
 
 public static WebElement lnk_LeftCornerHeading(WebDriver driver)
 {
    element = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/a"));    
    return element; 
 }
 
}