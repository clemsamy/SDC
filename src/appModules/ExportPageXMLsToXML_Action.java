package appModules;

/*import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;*/

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
//import org.openqa.selenium.interactions.Actions;
//import org.testng.Reporter;

import pageObjects.BaseClass;
import pageObjects.TransactionDetails_Page;
import utility.*;


public class ExportPageXMLsToXML_Action extends BaseClass
{
	public ExportPageXMLsToXML_Action(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public static void Execute(String Env) throws Exception
    {
		//StringSelection ss;
		WebElement element;
		int i;
		//Robot robot;	
		try{
			if (Variables.DocumentType.equals(Config.MRC_DocumentType_EWSS))
			{			
				TransactionDetails_Page.tab_EndorsementXMLTab1().click();
				Thread.sleep(300);
				element = TransactionDetails_Page.btn_ExportToXMLEndorsement();
				Thread.sleep(300);
				if(Utils.downloadWebPageFiles(element,Env,Config.Path_XMLFileOutput,Config.File_XMLEndorsement))
				{
					System.out.println("Step 8: Endorsement XML exported successfully - PASSED");
					Log.info("Step 8: Endorsement XML exported successfully - PASSED");
					Reporter.log("Step 8: Endorsement XML exported successfully - PASSED");
					BaseClass.bResult=true;
				}
				else
				{
					System.out.println("Step 8: Endorsement XML NOT exported");
					Log.error("Step 8: Endorsement XML NOT exported");
					Reporter.log("Step 8: Endorsement XML NOT exported");
					throw new Exception("Step 8: Endorsement XML NOT exported");
				}				
				Thread.sleep(300);
									
				TransactionDetails_Page.tab_MRCXMLTab2().click();
				Thread.sleep(300);
				element = TransactionDetails_Page.btn_ExportToXMLMRCSection1();
				Thread.sleep(300);
				if(Utils.downloadWebPageFiles(element,Env,Config.Path_XMLFileOutput,Config.File_XMLSection1))
				{
					System.out.println("Step 8a: MRC Section-1 XML exported successfully - PASSED");
					Log.info("Step 8a: MRC Section-1 XML exported successfully - PASSED");
					Reporter.log("Step 8a: MRC Section-1 XML exported successfully - PASSED");
					BaseClass.bResult=true;
				}
				else
				{
					System.out.println("Step 8a: MRC Section-1 XML NOT exported");
					Log.error("Step 8a: MRC Section-1 XML NOT exported");
					Reporter.log("Step 8a: MRC Section-1 XML NOT exported");
					throw new Exception("Step 8a: MRC Section-1 XML NOT exported");
				}		
				Thread.sleep(300);
				//BaseClass.bResult=true;	
			}	
			if (Variables.DocumentType.equals(Config.MRC_DocumentType_EO))
			{			
				//TransactionDetails_Page.tab_EndorsementXMLTab1().click();
				//Thread.sleep(300);
				element = TransactionDetails_Page.btn_ExportToXMLEndorsementOnly();
				Thread.sleep(300);
				if(Utils.downloadWebPageFiles(element,Env,Config.Path_XMLFileOutput,Config.File_XMLDirectEndorsement))
				{
					System.out.println("Step 8: Endorsement-ONLY XML exported successfully - PASSED");
					Log.info("Step 8: Endorsement-ONLY XML exported successfully - PASSED");
					Reporter.log("Step 8: Endorsement-ONLY XML exported successfully - PASSED");
					BaseClass.bResult=true;
				}
				else
				{
					System.out.println("Step 8: Endorsement-ONLY XML NOT exported");
					Log.error("Step 8: Endorsement-ONLY XML NOT exported");
					Reporter.log("Step 8: Endorsement-ONLY XML NOT exported");
					throw new Exception("Step 8: Endorsement-ONLY XML NOT exported");
				}				
				//Thread.sleep(300);									
			}
			if (Variables.DocumentType.equals(Config.MRC_DocumentType_SS))
			{			
				//TransactionDetails_Page.tab_EndorsementXMLTab1().click();
				//Thread.sleep(300);
				element = TransactionDetails_Page.btn_ExportToXMLSingleSectionMRC();
				Thread.sleep(300);
				if(Utils.downloadWebPageFiles(element,Env,Config.Path_XMLFileOutput,Config.File_XMLDirectMRC))
				{
					System.out.println("Step 8: Single Section MRC XML exported successfully - PASSED");
					Log.info("Step 8: Single Section MRC XML exported successfully - PASSED");
					Reporter.log("Step 8: Single Section MRC XML exported successfully - PASSED");
					BaseClass.bResult=true;
				}
				else
				{
					System.out.println("Step 8: Single Section MRC XML NOT exported");
					Log.error("Step 8: Single Section MRC XML NOT exported");
					Reporter.log("Step 8: Single Section MRC XML NOT exported");
					throw new Exception("Step 8: Single Section MRC XML NOT exported");
				}				
				//Thread.sleep(300);									
			}
			//if (Variables.DocumentType.equals(Config.MRC_DocumentType_MS))
			if (Variables.DocumentType.equals(Config.MRC_DocumentType_MS) || Variables.DocumentType.equals(Config.MRC_DocumentType_MSB))
			{							
				Thread.sleep(50);
				List <WebElement> NumofTabs = TransactionDetails_Page.tab_DynamicMRCTabs().findElements(By.xpath(TransactionDetails_Page.DynamicMRCTabsXpath));
				TransactionDetails_Page.btn_DynamicExportToXML();
				Variables.intNumOfMRCSections = NumofTabs.size();
				
				//System.out.println("number of Tabs= "+NumofTabs.size());
				for (i=1;i<=NumofTabs.size();i++)
				{
					String xpath = TransactionDetails_Page.DynamicMRCSection_TabXpath_Part1+i+TransactionDetails_Page.DynamicMRCSection_TabXpath_Part2;
					TransactionDetails_Page.tab_FindDynamicMRCTabs(xpath,i).click();
					Thread.sleep(300);
					
					xpath = TransactionDetails_Page.DynamicMRCSection_ExportXpath_Part1+i+TransactionDetails_Page.DynamicMRCSection_ExportXpath_Part2;					
					element = TransactionDetails_Page.btn_FindDynamicExportToXML(xpath,i);
					Thread.sleep(500);
					if(Utils.downloadWebPageFiles(element,Env,Config.Path_XMLFileOutput,i+"-"+Config.File_XMLMRCSection))
					{
						System.out.println("Step 8a: MRC Section-"+i+" XML exported successfully - PASSED");
						Log.info("Step 8a: MRC Section-"+i+" XML exported successfully - PASSED");
						Reporter.log("Step 8a: MRC Section-"+i+" XML exported successfully - PASSED");
						BaseClass.bResult=true;
					}
					else
					{
						System.out.println("Step 8a: MRC Section-"+i+" XML NOT exported");
						Log.error("Step 8a: MRC Section-"+i+" XML NOT exported");
						Reporter.log("Step 8a: MRC Section-"+i+" XML NOT exported");
						throw new Exception("Step 8a: MRC Section-"+i+" XML NOT exported");
					}		
					Thread.sleep(500);
				
				}				
				
			}	
		}
		catch(Exception e)
		{
			System.out.println("Step 8: XMLs NOT exported successfully");
			Log.error("Step 8: XMLs NOT exported successfully");
			Reporter.log("Step 8: XMLs NOT exported successfully");			
			BaseClass.bResult=false;		
			throw (e);			
		}
		
		
	}	
}

