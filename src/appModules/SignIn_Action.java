package appModules; 
//import org.openqa.selenium.WebDriver; 
//import org.apache.log4j.Logger;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pageObjects.BaseClass;
import pageObjects.Home_Page; 
import pageObjects.LogIn_Page;
import pageObjects.SignedIn_Page;
//import utility.Config;
import utility.Log;
//import utility.ExcelUtils;
import utility.Utils;
 
public class SignIn_Action 
{	
	//static Logger log = Logger.getLogger("devpinoyLogger");
     //public static void Execute(WebDriver driver,String sUsername, String sPassword)
	 //public static void Execute(int iTestCaseRow) throws Exception 
	 public static void Execute(String Username, String Password,String Env) throws Exception
     {
         WebElement element;
         //String sTestCaseName;
		 //Home_Page.lnk_SignIn().click();
        
        //String sUserName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_UserName);
        //String sPassword = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Password);
        
        LogIn_Page.txtbx_UserName().sendKeys(Username); 
        LogIn_Page.txtbx_Password().sendKeys(Password); 
        LogIn_Page.btn_Login().click();
        Thread.sleep(100);
        
        /*element = SignedIn_Page.btn_AcceptCookies();
        Utils.waitForElement(element);
        Thread.sleep(300);
        
        element.click(); 
        
        //Thread.sleep(200);
        if(!SignedIn_Page.verify_AcceptNotice().isEmpty())    	   	
    		SignedIn_Page.btn_AcceptNotice().click(); */
  
        element = SignedIn_Page.lnk_Constituents();
        element.click();
        //Utils.waitForElement(SignedIn_Page.lnk_UploadDocument());
        
        if (element.isEnabled())
        {
        	System.out.println("Step 1: UoS-"+Env+" Portal Home Page Launched Successfully - PASSED");
        	Reporter.log("Step 1: UoS-"+Env+" Portal Home Page Launched Successfully - PASSED");
        	Log.info("Step 1: SDC-"+Env+" Portal Home Page Launched Successfully - PASSED");
        	System.out.println("Step 2: UoS-"+Env+" Sign-In completed successfully - PASSED");
        	Reporter.log("Step 2: UoS-"+Env+" Sign-In completed successfully - PASSED");
            Log.info("Step 2: UoS-"+Env+" Sign-In completed successfully - PASSED");         
        	BaseClass.bResult=true;
        	//sTestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			//Utils.takeScreenshot(driver, sTestCaseName);
		
        }        	
        else
        {
        	//Reporter.log("Step 1: SDC-"+Env+" Portal Home Page NOT Launched");
        	//Log.error("Step 1: SDC-"+Env+" Portal Home Page NOT Launched");         
        	System.out.println("Step 2: UoS-"+Env+" Sign-In NOT completed");
        	Reporter.log("Step 2: UoS-"+Env+" Sign-In NOT completed");
            Log.error("Step 2: UoS-"+Env+" Sign-In NOT completed");         
        	BaseClass.bResult=false;        
        }        
     }
 }