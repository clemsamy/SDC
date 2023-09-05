package appModules;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import pageObjects.BaseClass;
import pageObjects.TransactionDetails_Page;
import utility.Config;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;
//import utility.WriteExcelFile;


public class CopyMRCPageFields_Action extends BaseClass
{
	public CopyMRCPageFields_Action(WebDriver driver) 
	{
		super(driver);
		
		// TODO Auto-generated constructor stub
	}
	public static boolean Execute(String Env) throws Exception
    {
		String strCreatedFileName,NewCSVConvertedFile;	
		File myXLSXFile;
		WebElement ExtractedDataTable = TransactionDetails_Page.tbl_ExtractedData();
		//String FieldValueText = ExtractedDataTable.getText();
		//System.out.println("The Fields displayed are...\n" + FieldValueText);
		
		//Log.info("Step 4: Transaction Details Page Copied successfully - PASSED");
		//Reporter.log("Step 4: Transaction Details Page Copied successfully - PASSED");
		List<WebElement> allRows = ExtractedDataTable.findElements(By.tagName("tr"));
		//int rowSize = allRows.size();
		Log.info("The Fields & XMLs on the Page are being copied to the Output Files...");
		System.out.println("The Fields & XMLs on the Page are being copied to the Output Files...");
		/*WriteExcelFile objExcelFile1 = new WriteExcelFile();
		//objExcelFile1.writeExcelRow(System.getProperty("user.dir")+"\\src\\testData","SDC-ST-Fields.xlsx","STFields",allRows);
		//objExcelFile1.writeExcelRow("C:\\TEMP","SDC-ST-Page-Fields.xlsx","STFields",allRows);
		if (Env.equalsIgnoreCase("ST"))
			objExcelFile1.writeExcelRow(Config.Path_MRCFieldOutput,Config.File_OutputSTFields,Config.Sheet_OutputFields,allRows);
		else
			objExcelFile1.writeExcelRow(Config.Path_MRCFieldOutput,Config.File_OutputUATFields,Config.Sheet_OutputFields,allRows);*/
		
		if (Env.equalsIgnoreCase("ST"))
		{
			ExcelUtils.writeExcelRow(Config.Path_MRCFieldOutput,Config.File_OutputSTFields,Config.Sheet_OutputFields,allRows);
			strCreatedFileName = Config.Path_MRCFieldOutput+Config.File_OutputSTFields;
  			myXLSXFile = new File(Config.Path_MRCFieldOutput+Config.File_OutputSTFields);
  			NewCSVConvertedFile = Config.Path_MRCFieldOutput+"Converted-"+Config.File_CSVExportSTFields;			
  			Utils.convertXLSXFileToCSV(myXLSXFile,NewCSVConvertedFile);
		}
		else
		{
			ExcelUtils.writeExcelRow(Config.Path_MRCFieldOutput,Config.File_OutputUATFields,Config.Sheet_OutputFields,allRows);
			strCreatedFileName = Config.Path_MRCFieldOutput+Config.File_OutputUATFields;
  			myXLSXFile = new File(Config.Path_MRCFieldOutput+Config.File_OutputUATFields);
  			NewCSVConvertedFile = Config.Path_MRCFieldOutput+"Converted-"+Config.File_CSVExportUATFields;
  			Utils.convertXLSXFileToCSV(myXLSXFile,NewCSVConvertedFile);
		}		
		if (Utils.isFileExist(strCreatedFileName)&& Utils.isFileExist(NewCSVConvertedFile))
		{
			System.out.println("Step 5: Fields on Page written into Excel File and CSV Converted successfully - PASSED");   
			Reporter.log("Step 5: Fields on Page written into Excel File and CSV Converted successfully - PASSED");
			Log.info("Step 5: Fields on Page written into Excel File and CSV Converted successfully - PASSED");
			Thread.sleep(500);
			return BaseClass.bResult = true;
		}
		else
		{
			System.out.println("Step 5: Fields on Page NOT written into Excel File");   
			Reporter.log("Step 5: Fields on Page NOT written into Excel File");
			Log.error("Step 5: Fields on Page NOT written into Excel File");
			Thread.sleep(500);
			return BaseClass.bResult = false;
		}	
	
    }	
}
