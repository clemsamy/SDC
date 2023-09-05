package appModules;

//import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import pageObjects.BaseClass;
//import pageObjects.Home_Page;
//import pageObjects.LogIn_Page;
import pageObjects.ViewTransactions_Page;
//import utility.Constant;
//import utility.ExcelUtils;
import utility.FluentWaitNew;
import utility.Log;
import utility.Utils;
import utility.Variables;

public class ViewTransactions_Action extends BaseClass
{
	public ViewTransactions_Action(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	//public static void Execute(int iTestCaseRow) throws Exception
	public static void Execute() throws Exception
    {
       ViewTransactions_Page.lnk_ViewTransactions().click();
       //********* Wait until file is processed***********************
     	String TranStatus = ViewTransactions_Page.lbl_TransactionStatus().getText();
     	Variables.DocumentType = "";
     	//System.out.println("Transaction Status:"+TranStatus);
     	FluentWaitNew objWait = new FluentWaitNew();     		
     	long FileProcess_startTime = System.currentTimeMillis();
     	int flag = 0;
     	String sTestCaseName;
     	
     	while((TranStatus.substring(8)).equals("InProcessing"))
     	{
     	  	   if (flag == 0) 
     		   { 
     			  System.out.println("The uploaded file is being processed in Kofax...");
     			  Log.info("The uploaded file is being processed in Kofax...");     			   
     		   }
     		   flag = 1;
     			   //******* Fluent Wait Refresh page***************
     			   //FluentWaitNew objWait = new FluentWaitNew();
     		   //WebElement objStatus = objWait.fluentWaitMethod(By.xpath("/html/body/div[2]/div/ul/li[1]/div[2]/div/div[1]/div[2]/div[1]"),driver);  //Checking Transaction Status object 
     		   WebElement objStatus = objWait.fluentWaitMethod(By.xpath(ViewTransactions_Page.TransactionStatusXpath),driver);  //Checking Transaction Status object
     		   //WebElement objStatus = objWait.fluentWaitMethod(ViewTransactions_Page.lbl_TransactionStatus(),driver);  //Checking Transaction Status object
     		   TranStatus = objStatus.getText();
     		   driver.navigate().refresh();
     		   //******* Fluent Wait Refresh page***************
     	}
     	//********* Wait until file is processed***********************
     	
     	if(TranStatus.substring(8).equals("Processed"))
   	    {
	   		Variables.DocumentType = ViewTransactions_Page.lbl_DocumentType().getText();
	   		Variables.DocumentType = Variables.DocumentType.substring(15);
	   			   		
	   		Variables.RequestID = ViewTransactions_Page.lbl_RequestID().getText();
	   		Variables.RequestID = Variables.RequestID.substring(12);
	   		Log.info("--The uploaded file Request ID: "+Variables.RequestID);
	   		Log.info("--The uploaded file Document Type: "+Variables.DocumentType+ " and is processed in Kofax");
	   		System.out.println("--The uploaded file Document Type: "+Variables.DocumentType+ " and is processed in Kofax");
	   		//System.out.println("Document Type:"+Variables.DocumentType);
	   		//System.out.println("New Document Type:"+Variables.DocumentType.substring(15));
	   		  	
	     	long FileProcess_stopTime = System.currentTimeMillis();
	     	long FileProcess_elapsedTime = (FileProcess_stopTime - FileProcess_startTime) / 60000;
	     	   
	     	//System.out.println("Total time taken for the file processing: "+ elapsedTime + " min(s)");
	     	Log.info("--Total time taken for the file processing is: "+ FileProcess_elapsedTime + " min(s)");
	     	System.out.println("--Total time taken for the file processing is: "+ FileProcess_elapsedTime + " min(s)");
	     	//String handle= driver.getWindowHandle();
			//System.out.println("Current Window Handle=" + handle);
			 
			//***Step 5 - View Details of Transaction
	     	Thread.sleep(20);     	
	     	ViewTransactions_Page.btn_ViewDetails().click();
	     	
			System.out.println("Step 4: File Processed and Transaction Details Page displayed successfully - PASSED");
			Reporter.log("Step 4: File Processed and Transaction Details Page displayed successfully - PASSED");
			Log.info("Step 4: File Processed and Transaction Details Page displayed successfully - PASSED");
			// Store and Print the name of all the windows open	              
			//Set handles = driver.getWindowHandles();
			//System.out.println(handles);
			// Pass a window handle to the other window
			for (String handle1 : driver.getWindowHandles()) 
			{
			    //System.out.println("New Window Handle= " + handle1);
			    driver.switchTo().window(handle1);
			}
			Thread.sleep(1000);
			BaseClass.bResult = true;
			sTestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			Utils.takeScreenshot(driver, sTestCaseName);
		
   	    }
     	else
	   	{
			 System.out.println("Step 4:The uploaded file has been Rejected(or)Not Processed by Kofax");
			 Log.error("Step 4:The uploaded file has been Rejected(or)Not Processed by Kofax");
			 BaseClass.bResult = false;
	   	}
       
    }
}
