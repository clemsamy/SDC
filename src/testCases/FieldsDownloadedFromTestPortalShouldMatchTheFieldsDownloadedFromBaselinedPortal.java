package testCases;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import utility.*;
import appModules.CompareDownloadedCSVandPageFields_Action;
import appModules.CompareDownloadedCSVFields_Action;

public class FieldsDownloadedFromTestPortalShouldMatchTheFieldsDownloadedFromBaselinedPortal
{
	public WebDriver driver;
	private String sTestCaseName;
	
	@BeforeMethod
	public void beforeMethod() throws Exception
	{
	 	DOMConfigurator.configure("log4j.xml");		  
	  	sTestCaseName = this.toString();
	  	sTestCaseName = Utils.getTestCaseName(this.toString());
		Log.startTestCase(sTestCaseName);		
		Utils.startTimer();
	}
	
	@Test (priority = 1)
	//@Parameters({"Environment"})
	public void Execute() throws Exception
    {
		   try
		   {
			   CompareDownloadedCSVFields_Action.Execute();
				
				if(BaseClass.bResult==true)
				{
					//ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
					Reporter.log("Comparison Result: Downloaded ST CSV file Vs Downloaded UAT CSV file verified successfully");
					Log.info("Comparison Result: Downloaded ST CSV file Vs Downloaded UAT CSV file verified successfully");
				}
				else
				{
					Reporter.log("Comparison Result: Downloaded ST CSV file Vs Downloaded UAT CSV file Comparision Failed. Please see the comparision output error log file for more info");
					Log.error("Comparison Result: Downloaded ST CSV file Vs Downloaded UAT CSV file Comparision Failed. Please see the comparision output error log file for more info");
					throw new Exception("Test Case Failed because of verification");
				}
		  }
		  catch (Exception e)
		  {		
			  sTestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			  //ExcelUtils.setCellData("Fail", iTestCaseRow, Constant.Col_Result);
			  //Utils.takeScreenshot(driver, sTestCaseName);
			  Log.error(e.getMessage());
			  throw (e);
		  }		
    }
	
	@AfterMethod
	public void afterMethod() 
	{	    
	    //Log.info("Performance: The total time taken to complete the test is: "+Utils.stopTimer()+ " min(s)");  
		Log.endTestCase(sTestCaseName);
	    // driver.close();
	}
	
}

