package appModules;

import java.util.List;
/*import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import pageObjects.BaseClass;
import pageObjects.TransactionDetails_Page;
import utility.Config;
//import utility.ConvertStringToXML;
//import utility.FluentWaitNew;
import utility.Log;
import utility.Utils;
import utility.Variables;
//import utility.WriteExcelFile;
import utility.XMLUtils;


public class CopyMRCPageXML_Action extends BaseClass
{
	public CopyMRCPageXML_Action(WebDriver driver) 
	{
		super(driver);
		
		// TODO Auto-generated constructor stub
	}
	public static void Execute(String Env) throws Exception
    {
		//int XMLType=0;
		//WebElement element;
		String OutputFile,XMLFieldValueText="",XMLMRCSection_1FieldValueText;
		int i;
		
		try
		{
			if (Variables.DocumentType.equals(Config.MRC_DocumentType_EWSS))
			{
				TransactionDetails_Page.tab_EndorsementXMLTab1().click();
				Thread.sleep(50);
				//System.out.println("I am in Endorsement tab 1");			
				XMLFieldValueText = TransactionDetails_Page.tbl_EndorsementsXMLTable1().getText();
				//System.out.println("\nxmlEndorse= "+XMLEndorsementFieldValueText);				
				if (Env.equalsIgnoreCase("ST"))
					OutputFile = Config.Path_XMLFileOutput+"ST-Page-"+Config.File_XMLEndorsement;
				else
					OutputFile = Config.Path_XMLFileOutput+"UAT-Page-"+Config.File_XMLEndorsement;				
				XMLUtils.convertStringToXML(XMLFieldValueText,OutputFile);
				if(Utils.isFileExist(OutputFile))
				{					
					System.out.println("Step 7: Endorsement XML copied successfully - PASSED");
					Log.info("Step 7: Endorsement XML copied successfully - PASSED");
					Reporter.log("Step 7: Endorsement XML copied successfully - PASSED");
					BaseClass.bResult=true;
				}
				else
				{
					System.out.println("Step 7: Endorsement XML NOT copied");
					Log.error("Step 7: Endorsement XML NOT copied");
					Reporter.log("Step 7: Endorsement XML NOT copied");					
					throw new Exception("Step 7: Endorsement XML NOT copied");					
				}				
							
				XMLMRCSection_1FieldValueText = "";
				TransactionDetails_Page.tab_MRCXMLTab2().click();	
				Thread.sleep(300);
				XMLMRCSection_1FieldValueText = TransactionDetails_Page.tbl_MRCXMLTable2().getText();
				//System.out.println("\nxmlSec= "+XMLEndorsementFieldValueText);				
				if (Env.equalsIgnoreCase("ST"))
					OutputFile = Config.Path_XMLFileOutput+"ST-Page-"+Config.File_XMLSection1;
				else
					OutputFile = Config.Path_XMLFileOutput+"UAT-Page-"+Config.File_XMLSection1;				
				XMLUtils.convertStringToXML(XMLMRCSection_1FieldValueText,OutputFile);
				if(Utils.isFileExist(OutputFile))
				{
					System.out.println("Step 7a: MRC Section-1 XML copied successfully - PASSED");
					Log.info("Step 7a: MRC Section-1 XML copied successfully - PASSED");
					Reporter.log("Step 7a: MRC Section-1 XML copied successfully - PASSED");
					BaseClass.bResult=true;
				}
				else
				{
					System.out.println("Step 7a: MRC Section-1 XML NOT copied");
					Log.error("Step 7a: MRC Section-1 XML NOT copied");
					Reporter.log("Step 7a: MRC Section-1 XML NOT copied");
					throw new Exception("Step 7a: MRC Section-1 XML NOT copied");					
				}				
				Thread.sleep(500);				
			}
			//Need to write code for different MRC Types...
			//***Endorsement Option ONLY
			if (Variables.DocumentType.equals(Config.MRC_DocumentType_EO))
			{
				//TransactionDetails_Page.tab_EndorsementXMLTab1().click();
				Thread.sleep(50);
				//System.out.println("I am in Endorsement tab");			
				XMLFieldValueText = TransactionDetails_Page.tbl_EndorsementXMLonlyTable().getText();
				//System.out.println("\nxmlEndorse= "+XMLEndorsementFieldValueText);				
				if (Env.equalsIgnoreCase("ST"))
					OutputFile = Config.Path_XMLFileOutput+"ST-Page-"+Config.File_XMLDirectEndorsement;
				else
					OutputFile = Config.Path_XMLFileOutput+"UAT-Page-"+Config.File_XMLDirectEndorsement;				
				XMLUtils.convertStringToXML(XMLFieldValueText,OutputFile);
				if(Utils.isFileExist(OutputFile))
				{
					System.out.println("Step 7: Endorsement-ONLY XML copied successfully - PASSED");
					Log.info("Step 7: Endorsement-ONLY XML copied successfully - PASSED");
					Reporter.log("Step 7: Endorsement-ONLY XML copied successfully - PASSED");
					BaseClass.bResult=true;
				}
				else
				{
					System.out.println("Step 7: Endorsement-ONLY XML NOT copied");
					Log.error("Step 7: Endorsement-ONLY XML NOT copied");
					Reporter.log("Step 7: Endorsement-ONLY XML NOT copied");					
					throw new Exception("Step 7: Endorsement-ONLY XML NOT copied");					
				}
				//Thread.sleep(500);				
			}
			if (Variables.DocumentType.equals(Config.MRC_DocumentType_SS))
			{
				//TransactionDetails_Page.tab_EndorsementXMLTab1().click();
				//try{
				Thread.sleep(50);
				//System.out.println("I am in Endorsement tab");			
				XMLFieldValueText = TransactionDetails_Page.tbl_SingleSectionMRCXMLonlyTable().getText();
				//System.out.println("\nxmlEndorse= "+XMLEndorsementFieldValueText);				
				if (Env.equalsIgnoreCase("ST"))
					OutputFile = Config.Path_XMLFileOutput+"ST-Page-"+Config.File_XMLDirectMRC;
				else
					OutputFile = Config.Path_XMLFileOutput+"UAT-Page-"+Config.File_XMLDirectMRC;				
				XMLUtils.convertStringToXML(XMLFieldValueText,OutputFile);
				if(Utils.isFileExist(OutputFile))
				{
					System.out.println("Step 7: Single Section MRC XML copied successfully - PASSED");
					Log.info("Step 7: Single Section MRC XML copied successfully - PASSED");
					Reporter.log("Step 7: Single Section MRC XML copied successfully - PASSED");
					BaseClass.bResult=true;
				}
				else
				{
					System.out.println("Step 7: Single Section MRC XML NOT copied");
					Log.error("Step 7: Single Section MRC XML NOT copied");
					Reporter.log("Step 7: Single Section MRC XML NOT copied");					
					throw new Exception("Step 7: Single Section MRC XML NOT copied");					
				}
				//Thread.sleep(500);
				//}catch(Exception e){
					//throw (e);}
			}
			//if (Variables.DocumentType.equals(Config.MRC_DocumentType_MS))
			if (Variables.DocumentType.equals(Config.MRC_DocumentType_MS) || Variables.DocumentType.equals(Config.MRC_DocumentType_MSB))
			{
				//TransactionDetails_Page.tab_EndorsementXMLTab1().click();				
				Thread.sleep(50);
				List <WebElement> NumofTabs = TransactionDetails_Page.tab_DynamicMRCTabs().findElements(By.xpath(TransactionDetails_Page.DynamicMRCTabsXpath));
				TransactionDetails_Page.tbl_DynamicMRCTables();
				Variables.intNumOfMRCSections = NumofTabs.size(); 
				
				//System.out.println("number of Tabs= "+NumofTabs.size());
				for (i=1;i<=NumofTabs.size();i++)
				{
				    String xpath = TransactionDetails_Page.DynamicMRCSection_TabXpath_Part1+i+TransactionDetails_Page.DynamicMRCSection_TabXpath_Part2;
					//System.out.println("tab xpath= "+xpath);
					TransactionDetails_Page.tab_FindDynamicMRCTabs(xpath,i).click();
					Thread.sleep(300);					
					xpath = TransactionDetails_Page.DynamicMRCSection_TableXpath_Part1+i+TransactionDetails_Page.DynamicMRCSection_TableXpath_Part2;
					//System.out.println("table xpath= "+xpath);	
				
					XMLFieldValueText = TransactionDetails_Page.tbl_FindDynamicMRCTables(xpath,i).getText();
					//System.out.println("\nxmlEndorse= "+XMLEndorsementFieldValueText);				
					if (Env.equalsIgnoreCase("ST"))
						OutputFile = Config.Path_XMLFileOutput+"ST-Page-"+i+"-"+Config.File_XMLMRCSection;
					else
						OutputFile = Config.Path_XMLFileOutput+"UAT-Page-"+i+"-"+Config.File_XMLMRCSection;
					XMLUtils.convertStringToXML(XMLFieldValueText,OutputFile);
					if(Utils.isFileExist(OutputFile))
					{
						System.out.println("Step 7: MRC Section-"+i+" XML copied successfully - PASSED");
						Log.info("Step 7: MRC Section-"+i+" XML copied successfully - PASSED");
						Reporter.log("Step 7: MRC Section-"+i+" XML copied successfully - PASSED");
						BaseClass.bResult=true;
					}
					else
					{
						System.out.println("Step 7: MRC Section-"+i+" XML NOT copied");
						Log.error("Step 7: MRC Section-"+i+" XML NOT copied");
						Reporter.log("Step 7: MRC Section-"+i+" XML NOT copied");					
						throw new Exception("Step 7: MRC Section-"+i+" XML NOT copied");					
					}
					Thread.sleep(300);
				}				
				
			}		
			
		}		
		catch(Exception e) 
		{
			System.out.println("Step 7: On-Page XML could not be copied. Reason: " + e.toString());
			Log.error("Step 7: On-Page XML could not be copied. Reason: " + e.toString());
			BaseClass.bResult =false;
			throw (e);
		}		
		
    }
}
