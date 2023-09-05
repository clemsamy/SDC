package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;

public class ViewTransactions_Page extends BaseClass
{
	  private static WebElement element = null;
	  public static String TransactionStatusXpath = "";
	  public static String SubmittedTranRow = "3";
    
	  public ViewTransactions_Page(WebDriver driver)
	  {
	  	super(driver);
	  }    
	 
	  public static WebElement lnk_ViewTransactions()
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/a"));
			 	Log.info("--View Transactions link is found on the Signed in page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--View Transactions link is not found on the Signed in page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement lbl_TransactionStatus()
	  {	     
		 /*element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[1]/a")); 
	     return element; */
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/div[2]/div/div[1]/div[2]/div[1]")); //First Tran in the list
			 	TransactionStatusXpath = "/html/body/div[2]/div/ul/li[1]/div[2]/div/div[1]/div[2]/div[1]";
			 	/*element = driver.findElement(By.xpath("//html/body/div[2]/div/ul/li[2]/div[2]/div/div[1]/div[2]/div[1]"));    //Second Tran in the list 
			 	TransactionStatusXpath = "//html/body/div[2]/div/ul/li[2]/div[2]/div/div[1]/div[2]/div[1]";*/
			 	Log.info("--Transaction Status field is found on the View Transaction Page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Transaction Status field is not found on the View Transaction Page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  
	  public static WebElement lbl_DocumentType()
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/div[2]/div/div[1]/div[1]/div[2]"));	//First Tran Doc Type		 										   
			 	//element = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[2]/div[2]/div/div[1]/div[1]/div[2]"));    //Second Tran Doc Type
			 	Log.info("--Document Type field is found on the View Transaction Page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Document Type field is not found on the View Transaction Page");
	    		throw(e);
	    	}
		     
	    	return element;
	  }  
	  public static WebElement lbl_RequestID()
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/div[2]/div/div[1]/div[1]/div[1]"));	//First Tran Doc Type		 										   
			 	//element = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[2]/div[2]/div/div[1]/div[1]/div[1]"));    //Second Tran Doc Type
			 	Log.info("--Document Type field is found on the View Transaction Page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Document Type field is not found on the View Transaction Page");
	    		throw(e);
	    	}
	    	return element;
	  } 
		
	  public static WebElement btn_ViewDetails()
	  {	     
		 /*element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[1]/a")); 
	     return element; */
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/div[2]/div/div[2]/div[2]/a")); // First Tran View details button
				//element = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[2]/div[2]/div/div[2]/div[2]/a"));    //Second Tran View details button
			 	Log.info("--View Details button is found on the View Transaction Page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--View Details button is not found on the View Transaction Page");
	    		throw(e);
	    	}
	    	return element;
	  }
}
