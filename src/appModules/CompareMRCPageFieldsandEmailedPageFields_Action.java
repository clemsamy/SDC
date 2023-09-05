package appModules;

/*import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/
import org.openqa.selenium.WebDriver;
//import org.testng.Reporter;

import pageObjects.BaseClass;
import utility.*;

public class CompareMRCPageFieldsandEmailedPageFields_Action extends BaseClass
{
	public static int NoOfRecords;	
	
	public CompareMRCPageFieldsandEmailedPageFields_Action(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public static void Execute(String Env) throws Exception
	{
			//File myXLSXFile;
			//String NewCSVConvertedFile="";
			//File file;
			//FileInputStream excellFile1 = null;
			Variables.OutputBuffer ="";
	  				
		    try{		    
				    Variables.OutputBuffer = Variables.OutputBuffer + "\n==============================================================";
			      	Variables.OutputBuffer = Variables.OutputBuffer + "\n"+Env+": Emailed CSV File Vs OnPage Fields Comparision Results";
			      	Variables.OutputBuffer = Variables.OutputBuffer + "\n==============================================================";	      	
				    //***DO NOT Change the order of the File comparisons below (Converted-Emailed Vs Converted-OnPage)
					if (Env.equalsIgnoreCase("ST"))
			  		{
			  			//Thread.sleep(5000);
			  			/*myXLSXFile = new File(Config.Path_MRCFieldOutput+Config.File_OutputSTFields);
			  			NewCSVConvertedFile = Config.Path_MRCFieldOutput+"Converted-"+Config.File_CSVExportSTFields;			
			  			Utils.convertXLSXFileToCSV(myXLSXFile,NewCSVConvertedFile);*/
						//if(Utils.CompareCSVFiles(Config.Path_MRCFieldOutput+"Converted-"+Config.File_CSVExportSTFields,Config.Path_MRCFieldOutput+"Converted-Emailed-"+Config.File_CSVExportSTFields ))
						if(Utils.CompareCSVFiles(Config.Path_MRCFieldOutput+"Converted-Emailed-"+Config.File_CSVExportSTFields,Config.Path_MRCFieldOutput+"Converted-"+Config.File_CSVExportSTFields ))
			  				BaseClass.bResult=true;	  			
			  			else
			  				BaseClass.bResult=false;
			  		}
					else if(Env.equalsIgnoreCase("UAT"))
			  		{
			  			//Thread.sleep(5000);
			  			/*myXLSXFile = new File(Config.Path_MRCFieldOutput+Config.File_OutputUATFields);
			  			NewCSVConvertedFile = Config.Path_MRCFieldOutput+"Converted-"+Config.File_CSVExportUATFields;
			  			Utils.convertXLSXFileToCSV(myXLSXFile,NewCSVConvertedFile);*/
			  			if(Utils.CompareCSVFiles(Config.Path_MRCFieldOutput+"Converted-Emailed-"+Config.File_CSVExportUATFields,Config.Path_MRCFieldOutput+"Converted-"+Config.File_CSVExportUATFields ))
			  				BaseClass.bResult=true;
			  			else
			  				BaseClass.bResult=false;			  			
			  		}
					Utils.writeFileCompareOutput(Config.Path_MRCFieldOutput,"Emailed-Vs-OnPage-"+Env+"-"+Config.File_CompareOutput,Variables.OutputBuffer);
					//**** TO colour the excel file(CSV converted) created
					/*Utils.ConvertCSVToEXCEL(NewCSVConvertedFile,Config.Path_MRCFieldOutput+"Converted-"+Env+"-Page-Fields-EXCEL.xlsx");
					file =    new File(Config.Path_MRCFieldOutput+"Converted-"+Env+"-Page-Fields-EXCEL.xlsx");
					excellFile1 = new FileInputStream(file);
					XSSFWorkbook workbook1 = new XSSFWorkbook(excellFile1);			
					ExcelUtils.colorExcelRow(workbook1,"Converted-"+Env+"-Page-Fields-EXCEL.xlsx");*/
					//**** TO colour the excel file(CSV converted) created
			   	} 
		    	catch (Exception e) 
		    	{
		    		e.printStackTrace();
		    	}
	  }
}
