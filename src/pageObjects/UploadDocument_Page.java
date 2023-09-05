package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;
//import utility.Variables;

public class UploadDocument_Page extends BaseClass
{	
      private static WebElement element = null;
          
	  public UploadDocument_Page(WebDriver driver)
	  {		
	  	super(driver);
	  }    
	 
	public static WebElement txtbx_CarrierReference()
	{	     
		 /*element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[1]/a")); 
	     return element; */
		 try{
			 	element = driver.findElement(By.xpath("//*[@id='CarrierReference']"));
			 	Log.info("--Carrier Reference textbox is found on the Upload Document page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Carrier Reference textbox is not found on the Upload Document page");
	    		throw(e);
	    	}
	    	return element;
	}
	public static WebElement txtbx_FileName()
	{	     
		 /*element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[1]/a")); 
	     return element; */
		 try{
			 	element = driver.findElement(By.xpath("//*[@id='file']"));
			 	Log.info("--FileName textbox is found on the Upload Document page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--FileName textbox is not found on the Upload Document page");
	    		throw(e);
	    	}
	    	return element;
	}
	public static WebElement btn_UploadFile()
	{	     
		 /*element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[1]/a")); 
	     return element; */
		 try{
			 	element = driver.findElement(By.xpath("//*[@id='btnSubmit']"));
			 	Log.info("--Upload button is found on the Upload Document page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Upload button is not found on the Upload Document page");
	    		throw(e);
	    	}
	    	return element;
	}
	
	public static WebElement lbl_UploadedResponse()
	{	     
		 /*element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[1]/a")); 
	     return element; */
		 //String strUploadResponse; 
		 try{
			 	element = driver.findElement(By.xpath("//*[@id='uploadDocument']/div/div"));
			 	//Variables.strResponseText = "//*[@id='uploadDocument']/div/div";  //Copied and used in Action fn./
			 	Log.info("--Upload response text label is found on the Upload Document page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Upload response text label is not found on the Upload Document page");
	    		throw(e);
	    	}
	    	return element;
	}
}
