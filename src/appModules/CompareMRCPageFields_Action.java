package appModules;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
//import org.testng.Reporter;

import pageObjects.BaseClass;
import utility.Config;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;
import utility.Variables;

public class CompareMRCPageFields_Action extends BaseClass
{
	public static int NoOfRecords;	
	
	public CompareMRCPageFields_Action(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public static void Execute(String filePath,String fileName1,String fileName2) throws IOException
	{
	      try {
		       // get input excel files
		       //System.out.println("Welcome Excel World");
	    	   BaseClass.bResult = false;
		       File file1 =    new File(filePath+"\\"+fileName1);
		       FileInputStream excellFile1 = new FileInputStream(file1);
		       File file2 =    new File(filePath+"\\"+fileName2);
		       FileInputStream excellFile2 = new FileInputStream(file2);		       
		       //Variables.notequalcolorlist = null;
		       //Variables.duplicatecolorlist = null;		       
		       
	          // Create Workbook instance holding reference to .xlsx file
	          XSSFWorkbook workbook1 = new XSSFWorkbook(excellFile1);
	          XSSFWorkbook workbook2 = new XSSFWorkbook(excellFile2);

	          // Get first/desired sheet from the workbook
	          XSSFSheet sheet1 = workbook1.getSheetAt(0);
	          XSSFSheet sheet2 = workbook2.getSheetAt(0);
	          
	          Variables.OutputBuffer = Variables.OutputBuffer + "\n=============================================";
      	      Variables.OutputBuffer = Variables.OutputBuffer + "\nST Vs UAT On Page Fields Comparision Results";
      	      Variables.OutputBuffer = Variables.OutputBuffer + "\n=============================================";
      	      //Variables.OutputBuffer = Variables.OutputBuffer+"\nThe the number of records in ST " + sheet1.getSheetName() +":" + (lastRow1);
      	      //Variables.OutputBuffer = Variables.OutputBuffer+"\nThe the number of records in UAT " + sheet2.getSheetName() +":" + (lastRow2)+"\n";
      
      	      // Compare sheets
      	      //if(ExcelUtils.compareTwoSheets_NEW(sheet1, sheet2)) {
      	      if(ExcelUtils.compareTwoSheets(sheet1, sheet2)) {
	              //System.out.println("\n\nThe two excel sheets are Equal");
	        	  Log.info("The two excel sheets are Equal.");
	        	  //Reporter.log("Comparison Result: The two excel sheets are Equal.");
	        	  Variables.OutputBuffer = Variables.OutputBuffer + "\nThe two excel files are Equal.";
	        	  BaseClass.bResult = true;
	        	  //Utils.writeFileCompareOutput("\nThe two excel sheets are Equal");
	          } else {
	              //System.err.println("\n\nThe two excel sheets are Not Equal");
	        	  Log.info("The two excel sheets are Not Equal.");
	        	  //Reporter.log("Comparison Result: The two excel sheets are Not Equal.");
	        	  Variables.OutputBuffer = Variables.OutputBuffer+"\nThe two excel files are Not Equal.";
	        	  BaseClass.bResult = false;
	          }
	          Variables.OutputBuffer = Variables.OutputBuffer +"\nNot equal rows: "+Variables.notequalcolorlist.size();
	          Variables.OutputBuffer = Variables.OutputBuffer +"\nMissing (or) Duplicate rows: "+Variables.duplicatecolorlist.size();              
       
	          Utils.writeFileCompareOutput(Config.Path_MRCFieldOutput,"OnPage-CSVs-STVsUAT-"+Config.File_CompareOutput,Variables.OutputBuffer);
	          //*****Pass the ST or UAT file based on the number of file records
	          if(Variables.strWorkbook.equals("ST"))
	        	  ExcelUtils.colorExcelRow(workbook1,fileName1);
	          else if(Variables.strWorkbook.equals("UAT"))
	        	  ExcelUtils.colorExcelRow(workbook2,fileName2);
	          //close files
	          workbook1.close();
	          workbook2.close();
	          excellFile1.close();
	          excellFile2.close();

	      } catch (Exception e) 
	      {
	          e.printStackTrace();
	      }
	  }
}
