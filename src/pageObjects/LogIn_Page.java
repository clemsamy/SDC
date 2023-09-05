package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;

public class LogIn_Page extends BaseClass
{
    //private static WebElement element = null;
    private static WebElement element = null;
    
    public LogIn_Page(WebDriver driver)
    {
    	super(driver);
    }    
 
 public static WebElement txtbx_UserName() throws Exception
 {
     /*element = driver.findElement(By.xpath("//*[@id='Email']")); 
     return element; */
     
     try{
		 element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/tbody/tr[1]/td[2]/input"));
		
		 Log.info("--UserName textbox is found on the Home Page");
        }
	    catch (Exception e)
        {
    		Log.error("--UserName textbox is not found on the Home Page");
    		throw(e);
    	}
    	return element;	 
 }
 
 
 public static WebElement txtbx_Password() throws Exception
 {
     /*element = driver.findElement(By.xpath("//*[@id='Password']")); 
     return element; */
     
     try{
		 element = driver.findElement(By.xpath("//*[@id=\"splash-login-password\"]"));
		 Log.info("--Password textbox is found on the Home Page");
        }
	    catch (Exception e)
        {
    		Log.error("--Password textbox is not found on the Home Page");
    		throw(e);
    	}
    	return element;	 
 }
 
 
 public static WebElement btn_Login() throws Exception
 {
    /*element = driver.findElement(By.xpath("//*[@id='login']"));    
    return element; */
	 try{
		 element = driver.findElement(By.xpath("//*[@id=\"ext-gen3\"]"));
		 Log.info("--Login button is found on the Home Page");
        }
	    catch (Exception e)
        {
    		Log.error("--Login button is not found on the Home Page");
    		throw(e);
    	}
    	return element;	 
 }
 
 public static WebElement txt_PageHeading(WebDriver driver)
 {
     element = driver.findElement(By.xpath("/html/body/div[2]/div/h2"));
     return element; 
 }
 
 public static WebElement lnk_LeftCornerHeading(WebDriver driver)
 {
    element = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/a")); 
    return element; 
 }
 
 public static WebElement lnk_ForgotPassword(WebDriver driver)
 {
    element = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/form/div[4]/div/a"));
    return element; 
 }
 
}