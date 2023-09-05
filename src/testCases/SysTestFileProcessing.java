//**** Trying to execute the Data driven test using Excel sheet - WORKING as default. but failing when no TC name found on the excel sheet and the current row returns as 0

package testCases;

import java.io.IOException;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import utility.Config;
//import utility.ExcelUtils;
import utility.Log;
import utility.Utils;
import Scrap.CopyMRCPageXMLOLD_Action;
import Scrap.ExportPageFieldsToCSVOLD_Action;
import appModules.CompareMRCPageFields_Action;
import appModules.CompareMRCXML_Action;
import appModules.CopyMRCPageFields_Action;
import appModules.CopyMRCPageXML_Action;
import appModules.ExportPageFieldsToCSV_Action;
import appModules.CompareDownloadedCSVandPageFields_Action;
import appModules.ExportPageXMLsToXML_Action;
//import appModules.CompareMRCFields_Action;
//import appModules.CheckOut_Action;
//import appModules.Confirmation_Action;
//import appModules.PaymentDetails_Action;
//import appModules.ProductSelect_Action;
import appModules.SignIn_Action;
import appModules.UploadDocument_Action;
import appModules.ViewTransactions_Action;
import appModules.ExtractEmailArtifacts_Action;
//import appModules.Verification_Action;

public class SysTestFileProcessing
{
	public WebDriver driver;
	private String sTestCaseName;
	//private int iTestCaseRow;
	//Logger log = Logger.getLogger("devpinoyLogger");
	  //private static Logger log = Logger.getLogger(Log.class.getName());
	
 @BeforeMethod
 //@Parameters({"Browser","STURL",})
 @Parameters({"Browser","Environment",})
  //public void beforeMethod(String Browser, String URL) throws Exception
  public void beforeMethod(String Browser, String Env) throws Exception
  {
	  	DOMConfigurator.configure("log4j.xml");
	  	
	  	sTestCaseName = this.toString();
	  	sTestCaseName = Utils.getTestCaseName(this.toString());
	  	//System.out.println("TC name THIS= "+ this.toString());
	  	//System.out.println("TC name= "+ sTestCaseName);
	  	
	  	Utils.deleteFolder(Config.DIR_Child);  //***** To delete the Output File folder
	  	Utils.maintainDirectoryStructure();	  	
		Log.startTestCase(sTestCaseName);
		//Log.info("The test started at: "+Utils.statTimer());
		Utils.startTimer();
				
		//ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
		//iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
		
		//driver = Utils.OpenBrowser(iTestCaseRow);
		if (Env.equalsIgnoreCase("ST"))
			driver = Utils.OpenBrowser(Browser,Config.STURL);               //URL Passing from Config file
		else if(Env.equalsIgnoreCase("UAT"))
			driver = Utils.OpenBrowser(Browser,Config.UATURL);              //URL Passing from Config file
		new BaseClass(driver);	
		
		/*String temp = "C:\\SDC_Automation_Outputs\\OutputFiles-SSEWP\\temp";
    	String[] ss = temp.split("\\");
    	String[] dd = ss[2].split("_");
    	String ChildDIR = dd[0];
    	System.out.println("\nString OP File= "+ChildDIR);*/   
					
  }
  
  // This is the starting of the Main Test Case
  @Test (priority = 1)
  @Parameters({"Username","STPassword","Environment"})
  public void SysTestMRCFileProcessing(String Username,String Password, String Env) throws Exception 
  {
	  try
	  {  	
		  	//SignIn_Action.Execute(Username,Password,Env);                 //Passing from XML file.
		  	SignIn_Action.Execute(Config.Username,Config.Password,Env);     //Passing from config file.
		  	//UploadDocument_Action.Execute();
		  	
			/*ViewTransactions_Action.Execute();			
			CopyMRCPageFields_Action.Execute(Env);			
			ExportPageFieldsToCSV_Action.Execute(Env);
			CopyMRCPageXML_Action.Execute(Env);   			//**** Need to add "Multi Section Binding Authority" MRC type
			ExportPageXMLsToXML_Action.Execute(Env);		//**** Need to add "Multi Section Binding Authority" MRC type
			ExtractEmailArtifacts_Action.Execute(Env);*/
			
			if(BaseClass.bResult==true)
			{
				//ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
				System.out.println("Final: UoS-SysTest completed successfully");
				Reporter.log("Final: UoS-SysTest completed successfully");
				Log.info("Final: UoS-SysTest completed successfully");
			}
			else
			{
				System.out.println("Final: UoS-SysTest processing interuppted");
				Reporter.log("Final: UoS-SysTest processing interuppted");
				Log.info("Final: UoS-SysTest processing interuppted");
				throw new Exception("Final: Test Case Failed because of file processing problem");
			}
	  }
	  catch (Exception e)
	  {		
		  sTestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		  //ExcelUtils.setCellData("Fail", iTestCaseRow, Constant.Col_Result);
		  Utils.takeScreenshot(driver, sTestCaseName);
		  System.out.println(e.getMessage());
		  Log.error(e.getMessage());
		  throw (e);
	  }		
  } 
  
  
  // Its time to close & finish the test case		
  @AfterMethod
  public void afterMethod() throws IOException 
  {
	  // Printing logs to end the test case
	  System.out.println("Performance: The total time taken to complete the test is: "+Utils.stopTimer()+ " min(s)");
	  Log.info("Performance: The total time taken to complete the test is: "+Utils.stopTimer()+ " min(s)");
	  Log.endTestCase(sTestCaseName);  
	    
	  //Utils.createZipFile(Config.Path_ZipFilesFolder, Config.ZipFileName);
	  //Utils.sendEmailWithAttachment();
	  // Closing the opened driver
	  // driver.close();
  }

}

