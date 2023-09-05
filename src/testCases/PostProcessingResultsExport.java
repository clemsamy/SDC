//**** Trying to execute the Data driven test using Excel sheet - WORKING as default. but failing when no TC name found on the excel sheet and the current row returns as 0

package testCases;

import java.io.IOException;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import utility.Config;
//import utility.ExcelUtils;
import utility.Log;
import utility.Utils;


//import appModules.Verification_Action;

public class PostProcessingResultsExport
{
	@Test (priority = 1)
	public void PostProcessingOutputResultsExport() throws IOException 
	{	
	 
	}
	
	@AfterSuite
	public void postProcess() throws IOException 
	{
		//***Copy results in to one output file. Zip and send email after test finished
		if(Utils.CopyReportFiles())
			Log.info("--Report files and log files copied to output directory");
		else
			Log.error("--Report files and log files not copied to output directory");
		
		Utils.createZipFile(Config.Path_ZipFilesFolder, Config.ZipFileName);
	    //Utils.sendEmailWithAttachment();     //***Make it works in the report level.
	
	    //******Copy results in to one output file. Zip and send email after test finished   
	
	}
}
