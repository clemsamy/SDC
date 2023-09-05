package appModules;

/*import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;*/

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import pageObjects.BaseClass;
//import pageObjects.Home_Page;
//import pageObjects.LogIn_Page;
import pageObjects.SignedIn_Page;
import pageObjects.UploadDocument_Page;
import utility.Config;
import utility.Utils;
//import utility.Variables;
//import utility.ExcelUtils;
import utility.Log;

public class UploadDocument_Action extends BaseClass
{
	
	public UploadDocument_Action(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//public static void Execute(int iTestCaseRow) throws Exception
	public static void Execute() throws Exception
    {
   	   //SignedIn_Page.lnk_UploadDocument().click();
	   UploadDocument_Page.txtbx_CarrierReference().sendKeys(Config.CarrierReference);
	   UploadDocument_Page.txtbx_FileName().sendKeys(Config.InputMRCFile);
	   UploadDocument_Page.btn_UploadFile().click();
	   String sTestCaseName;
	   
	   /*WebElement dynamicElement = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(Variables.strResponseText)));*/
	   
	   /*WebElement dynamicElement = (new WebDriverWait(driver, 10))
						  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='uploadDocument']/div/div")));    // Wait & verify for the upload response text displayed after upload*/
	   
	   //SignedIn_Page.btn_AcceptCookies().click();
	   
	   String ResponseText = UploadDocument_Page.lbl_UploadedResponse().getText(); 
			   //driver.findElement(By.xpath("//*[@id='uploadDocument']/div/div")).getText();
	   String ExtractedText = ResponseText.substring(2,ResponseText.length()-31);
	   //String RequestID = ResponseText.substring(49,ResponseText.length()-1);

		if(ExtractedText.equals("Request submitted successfully"))
		{
			//System.out.println("\nStep 3: SDC-SysTest MRC document upload completed successfully ");
			System.out.println("Step 3: MRC document upload completed successfully - PASSED");
			Log.info("Step 3: MRC document upload completed successfully - PASSED");
			Log.info(ResponseText.substring(2));
			Reporter.log("Step 3: MRC document upload completed successfully - PASSED");
			BaseClass.bResult = true;
			sTestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			Utils.takeScreenshot(driver, sTestCaseName);
		}
		else
		{
			//System.out.println("\nStep 3: SDC-SysTest document upload NOT completed");
			//System.out.println(ResponseText.substring(2));
			System.out.println("Step 3: MRC document upload NOT completed");
			Log.error("Step 3: MRC document upload NOT completed");
			Reporter.log("Step 3: MRC document upload NOT completed");
			BaseClass.bResult = false;
		}
			
	}    
}
