package appModules;

/*import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;*/

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import pageObjects.BaseClass;
import pageObjects.TransactionDetails_Page;
import utility.*;


public class ExportPageFieldsToCSV_Action extends BaseClass
{
	public ExportPageFieldsToCSV_Action(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public static void Execute(String Env) throws Exception
    {
		WebElement element = TransactionDetails_Page.btn_ExportFieldToCSV();		
		if (Utils.downloadWebPageFiles(element,Env,Config.Path_MRCFieldOutput,Config.File_CSVExportSTFields.substring(3)))		
		{
			System.out.println("Step 6: Transaction Page Fields exported to CSV successfully - PASSED");
			Log.info("Step 6: Transaction Page Fields exported to CSV successfully - PASSED");	
			Reporter.log("Step 6: Transaction Page Fields exported to CSV successfully - PASSED");
			BaseClass.bResult = true;
		}
		else
		{
			System.out.println("Step 6: Transaction Page Fields NOT exported to CSV");
			Log.error("Step 6: Transaction Page Fields NOT exported to CSV");	
			Reporter.log("Step 6: Transaction Page Fields NOT exported to CSV");
			BaseClass.bResult = false;
		}
		Thread.sleep(2000);			
	}	
}
