package appModules;

//import java.io.File;
import org.openqa.selenium.WebDriver;
//import org.testng.Reporter;

import pageObjects.BaseClass;
import utility.*;

public class CompareDownloadedCSVFields_Action extends BaseClass
{
	public static int NoOfRecords;	
	
	public CompareDownloadedCSVFields_Action(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public static void Execute() throws Exception
	{			
		    Variables.OutputBuffer = "";
			try
			{		    
			    Variables.OutputBuffer = Variables.OutputBuffer + "\n=====================================================================";
		      	Variables.OutputBuffer = Variables.OutputBuffer + "\nST Downloaded CSV File Vs UAT Downloaded CSV File Comparision Results";
		      	Variables.OutputBuffer = Variables.OutputBuffer + "\n=====================================================================";	      	
		      	
				if(Utils.CompareCSVFiles(Config.Path_MRCFieldOutput+"Exported-"+Config.File_CSVExportSTFields,Config.Path_MRCFieldOutput+"Exported-"+Config.File_CSVExportUATFields ))
	  			  BaseClass.bResult=true;	  			
	  			else
	  				BaseClass.bResult=false;	  			
	  			Utils.writeFileCompareOutput(Config.Path_MRCFieldOutput,"Downloaded-CSVs-STVsUAT-"+Config.File_CompareOutput,Variables.OutputBuffer);	  		
			} catch (Exception e) 
		    {
		        e.printStackTrace();
		    }
	  }
}
