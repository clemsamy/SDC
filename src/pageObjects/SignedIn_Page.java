package pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/*import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;*/

import utility.Log;

public class SignedIn_Page extends BaseClass
{	
      private static WebElement element = null;
      private static List<WebElement> elements = null;
    
	  public SignedIn_Page(WebDriver driver)
	  {
	  	super(driver);
	  }    
	public static List<WebElement> verify_AcceptNotice()
	{		 
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		elements = driver.findElements(By.xpath("//*[@id=\"btnSubmit\"]"));			 										 
		// 	Log.info("--Accept Privacy Notice button is found on the Signed in page");
		return elements;
	}
	public static WebElement btn_AcceptNotice()
	{
		     
			 /*element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[1]/a")); 
		     return element; */
			 try{
				 	element = driver.findElement(By.xpath("//*[@id=\"ext-gen449\"]"));			 										 
				 	Log.info("--Password expiry button is found on the Signed in page");
		        }
			    //catch (NoSuchElementException e)
			    catch (Exception e)
		        {
		    		Log.error("--Password expiry button button is not found on the Signed in page");
		    		throw(e);
		    		//element = null;
		    	}
		    	return element;
	}
	public static WebElement lnk_Home()
	{
	     
		 /*element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[1]/a")); 
	     return element; */
		 try{
			 	element = driver.findElement(By.xpath("//*[@id=\"ext-gen306\"]"));			 										 
			 	Log.info("--Home link is found on the Signed in page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Home link is not found on the Signed in page");
	    		throw(e);
	    	}
	    	return element;
	}
	
	public static WebElement lnk_Constituents()
	{
	     /*element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[2]/a")); 
	     return element; */
	     
	     try{
	    	 element = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/table/tbody/tr/td[1]/table/tbody/tr/td[4]/table/tbody/tr[2]/td[2]/em/button"));
			 	Log.info("--Constituents link is found on the Signed in page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Constituents link is not found on the Signed in page");
	    		throw(e);
	    	}
	    	return element;
	}
	public static WebElement lnk_ViewTransactions()
	{
	     /*element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[3]/a")); 
	     return element;*/
	     try{
	    	 	element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[3]/a"));
			 	Log.info("--View Transactions link is found on the Signed in page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--View Transactions link is not found on the Signed in page");
	    		throw(e);
	    	}
	    	return element;
	}
	public static WebElement lnk_MIReports()
	{
	     /*element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[4]/a")); 
	     return element; */
	     try{
	    	 	element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[4]/a"));
			 	Log.info("--MI Reports link is found on the Signed in page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--MI Reports link is not found on the Signed in page");
	    		throw(e);
	    	}
	    	return element;	
	}
	public static WebElement lnk_SignOut()
	{
	     /*element = driver.findElement(By.xpath("//*[@id=\"signoutForm\"]/ul/li[2]/a")); 
	     return element;*/
	     try{
	    	 element = driver.findElement(By.xpath("//*[@id=\"signoutForm\"]/ul/li[2]/a"));
			 	Log.info("--Sign-out link is found on the Signed in page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Sign-out link is not found on the Signed in page");
	    		throw(e);
	    	}
	    	return element;	     
	}
	public static WebElement btn_AcceptCookies()
	{
	     /*element = driver.findElement(By.xpath("/html/body/div[1]/div/a[1]")); 
	     return element; */
	     
	     try{
	    	 	String btnLocation = new String("/html/body/div[1]/div/a[1]");
		     //WebDriverWait wait15 = new WebDriverWait(driver, 30);
		        //wait15.until(ExpectedConditions.elementToBeClickable(By.xpath(btnLocation)));
		     //List<WebElement> elList = wait15.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(btnLocation)));
		        //System.out.println("Elements found: " + elList.size());
		        //for(WebElement el : elList) {
		        //System.out.println(el.toString());
		        //}
		        element = driver.findElement(By.xpath(btnLocation));
		        //Thread.sleep(50);
		        Log.info("--Accept Cookies button is found on the Signed in page");
	    	 
	    	 	/*element = driver.findElement(By.xpath("/html/body/div[1]/div/a[1]"));
			 	Log.info("Accept Cookies button is found on the Signed in page");*/
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Accept Cookies button is not found on the Signed in page");
	    		throw(e);
	    	}   
	        
	    	return element;	     
	}		
			
}
