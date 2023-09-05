package appModules;

//import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;

//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import pageObjects.BaseClass;
//import utility.Config;
//import utility.ExcelUtils;
import utility.Log;
import utility.Utils;
import utility.Variables;
import utility.XMLUtils;

public class CompareMRCXML_Action extends BaseClass
{
	public static int NoOfRecords;	
	
	public CompareMRCXML_Action(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public static void Execute(String filePath,String filename1,String filename2) throws IOException
	{
	      try {
		       
	    	  /*Variables.OutputBuffer="";	          
	          Variables.OutputBuffer = Variables.OutputBuffer + "\n=============================================";
      	      Variables.OutputBuffer = Variables.OutputBuffer + "\nST Vs UAT Endorsement XML Comparison Results";
      	      Variables.OutputBuffer = Variables.OutputBuffer + "\n=============================================";*/ 
      		  if ((Utils.isFileExist(filePath+filename1)) && (Utils.isFileExist(filePath+filename2)))
      		  {	    	  
		          if(XMLUtils.CompareXMLFiles(filePath+filename1,filePath+filename2)) {
		              //System.out.println("\n\nThe two excel sheets are Equal");
		        	  Log.info("Comparison Result: The two XML Files are Equal.");
		        	  Reporter.log("\nComparison Result: The two XML Files are Equal.");
		        	  Variables.OutputBuffer = Variables.OutputBuffer + "\nThe two XML Files are Equal.\n";
		        	  BaseClass.bResult = true;
		        	  //Utils.writeFileCompareOutput("\nThe two excel sheets are Equal");
		          } else {
		              //System.err.println("\n\nThe two excel sheets are Not Equal");
		        	  Log.error("Comparison Result: The two XML Files are Not Equal.");
		        	  Reporter.log("\nComparison Result: The two XML Files are Not Equal.");
		        	  Variables.OutputBuffer = Variables.OutputBuffer+"\n\nThe two XML Files are Not Equal.\n";
		        	  BaseClass.bResult = false;
		          }
      		  }
      		  else
      		  {
      			  //System.out.println("File 1 = "+filePath+filename1);
      			  //System.out.println("File 2 = "+filePath+filename2);
      		  	  Log.error("Comparison Result: The comparable XML File(s) Not Found.Please check whether the processed document types are same in both environment (OR) All the files are generated with expected naming standards.");
	        	  Reporter.log("\nComparison Result: The comparable XML Files are Not Found.Please check whether the processed document types are same in both environment (OR) All the files are generated with expected naming standards.");
	        	  Variables.OutputBuffer = Variables.OutputBuffer+"\n\nThe comparable XML Files are Not Found.Please check whether the processed document types are same in both environment (OR) All the files are generated with expected naming standards.";
	        	  BaseClass.bResult = false;	       
      		  }	          
		          //Utils.writeFileCompareOutput(Config.Path_XMLFileOutput,"OnPage-"+Config.File_XMLCompareOutput,Variables.OutputBuffer);
	  
	  
	      } catch (Exception e) 
	      {
	          e.printStackTrace();
	      }
	  }
}
