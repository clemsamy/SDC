package appModules;

/*import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;*/

//import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
//import org.openqa.selenium.interactions.Actions;
//import org.testng.Reporter;

import pageObjects.BaseClass;
import pageObjects.TransactionDetails_Page;
import utility.*;


public class ExtractEmailArtifacts_Action extends BaseClass
{
	public ExtractEmailArtifacts_Action(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public static void Execute(String Env) throws Exception
    {
		String EmailedXML,EmailedCSVFields,query="",OutputFile,OutputCSVFile,OutputTxtFile,TransactionID="";
		int i;
		
		try{
			if (Env.equalsIgnoreCase("UAT"))  //*** VPN is connected to access UAT DB only.
			{
				//Thread.sleep(1000);
				//if(SQLUtils.connectUATVPN_UsingBatchFile())       //*** VPN Connection for UAT DB using batch file
				if(SQLUtils.connectUATVPN())                        //*** VPN Connection for UAT DB direct
				{
					System.out.println("--UAT SQL DB connected successfully");
					Log.info("--UAT SQL DB connected successfully");
				}
				else
				{
					System.out.println("--UAT SQL DB Not connected");
					Log.info("--UAT SQL DB Not connected");
					throw new Exception("--Emailed MRC-Artefacts NOT extracted");
				}
				Thread.sleep(1000);
			}			
			query = "Select TransactionID from [SDC_DATA].[SDCR].[Transaction] where RequestID = '"+Variables.RequestID+"'";
			TransactionID = SQLUtils.executeSQLQuery(query,Env);
			
			query = "SELECT [Detail] FROM [SDC_DATA].[SDCR].[Artefacts] Where TransactionID ='"+TransactionID+"' And ArtefactName = '"+Config.DBCSVFieldText+"'";
			EmailedCSVFields = SQLUtils.executeSQLQuery(query,Env);
						
			if (Env.equalsIgnoreCase("ST"))
			{
				OutputTxtFile = Config.Path_MRCFieldOutput+"Emailed-ST-"+Config.File_TXTEmailExtractFields;
				OutputCSVFile = Config.Path_MRCFieldOutput+"Converted-Emailed-ST-"+Config.File_CSVEmailExtractFields;
			}
			else
			{
				OutputTxtFile = Config.Path_MRCFieldOutput+"Emailed-UAT-"+Config.File_TXTEmailExtractFields;	
				OutputCSVFile = Config.Path_MRCFieldOutput+"Converted-Emailed-UAT-"+Config.File_CSVEmailExtractFields;
			}
			
			FileUtils.writeStringToFile(new File(OutputTxtFile), EmailedCSVFields);  //Creating a txt file by extracting the 
			if(Utils.convertTextFileToCSV(OutputTxtFile,OutputCSVFile))
			{
				System.out.println("Step 9: Emailed-MRC Fields extracted successfully - PASSED");
				Log.info("Step 9: Emailed-MRC Fields extracted successfully - PASSED");
				Reporter.log("Step 9: Emailed-MRC Fields extracted successfully - PASSED");
				BaseClass.bResult=true;
			}
			else
			{
				System.out.println("Step 9: Emailed-MRC Fields NOT extracted");
				Log.error("Step 9: Emailed-MRC Fields NOT extracted");
				Reporter.log("Step 9: Emailed-MRC Fields NOT extracted");
				//SQLUtils.disconnectUATVPN();
				throw new Exception("Step 9: Emailed-MRC Fields NOT extracted");					
			}				
		
			if (Variables.DocumentType.equals(Config.MRC_DocumentType_EWSS))
			{			
				query = "SELECT [Detail] FROM [SDC_DATA].[SDCR].[Artefacts] Where TransactionID ='"+TransactionID+"' And ArtefactName = '"+Config.DBAcordEndorsementXML+"'";
				//System.out.println("query= " +query );
				EmailedXML = SQLUtils.executeSQLQuery(query,Env);			
			
				if (Env.equalsIgnoreCase("ST"))
					OutputFile = Config.Path_XMLFileOutput+"Emailed-ST-"+Config.File_XMLEndorsement;
				else
					OutputFile = Config.Path_XMLFileOutput+"Emailed-UAT-"+Config.File_XMLEndorsement;	
				
				XMLUtils.convertStringToXML(EmailedXML,OutputFile);				
				if(Utils.isFileExist(OutputFile))
				{
					System.out.println("Step 10: Emailed-Endorsement XML extracted successfully - PASSED");
					Log.info("Step 10: Emailed-Endorsement XML extracted successfully - PASSED");
					Reporter.log("Step 10: Emailed-Endorsement XML extracted successfully - PASSED");
					BaseClass.bResult=true;
				}
				else
				{
					System.out.println("Step 10: Emailed-Endorsement XML NOT extracted");
					Log.error("Step 10: Emailed-Endorsement XML NOT extracted");
					Reporter.log("Step 10: Emailed-Endorsement XML NOT extracted");					
					throw new Exception("Step 10: Emailed-Endorsement XML NOT extracted");					
				}				
				
				EmailedXML="";
				query = "SELECT [Detail] FROM [SDC_DATA].[SDCR].[Artefacts] Where TransactionID ='"+TransactionID+"' And ArtefactName = '"+Config.DBMRCXML+"'";
				EmailedXML = SQLUtils.executeSQLQuery(query,Env);
	
				if (Env.equalsIgnoreCase("ST"))
					OutputFile = Config.Path_XMLFileOutput+"Emailed-ST-"+Config.File_XMLSection1;
				else
					OutputFile = Config.Path_XMLFileOutput+"Emailed-UAT-"+Config.File_XMLSection1;	
				
				XMLUtils.convertStringToXML(EmailedXML,OutputFile);				
				if(Utils.isFileExist(OutputFile))
				{
					System.out.println("Step 10a: Emailed-MRC Section-1 XML extracted successfully - PASSED");
					Log.info("Step 10a: Emailed-MRC Section-1 XML extracted successfully - PASSED");
					Reporter.log("Step 10a: Emailed-MRC Section-1 extracted successfully - PASSED");
					BaseClass.bResult=true;
				}
				else
				{
					System.out.println("Step 10a: Emailed-MRC Section-1 XML NOT extracted");
					Log.error("Step 10a: Emailed-MRC Section-1 XML NOT extracted");
					Reporter.log("Step 10a: Emailed-MRC Section-1 XML NOT extracted");	
					//SQLUtils.disconnectUATVPN();
					throw new Exception("Step 10a: Emailed-MRC Section-1 XML NOT extracted");					
				}	
			}	
			if (Variables.DocumentType.equals(Config.MRC_DocumentType_EO))
			{			
				query = "SELECT [Detail] FROM [SDC_DATA].[SDCR].[Artefacts] Where TransactionID ='"+TransactionID+"' And ArtefactName = '"+Config.DBAcordEndorsementXML+"'";				
				EmailedXML = SQLUtils.executeSQLQuery(query,Env);			
			
				if (Env.equalsIgnoreCase("ST"))
					OutputFile = Config.Path_XMLFileOutput+"Emailed-ST-"+Config.File_XMLDirectEndorsement;
				else
					OutputFile = Config.Path_XMLFileOutput+"Emailed-UAT-"+Config.File_XMLDirectEndorsement;	
				
				XMLUtils.convertStringToXML(EmailedXML,OutputFile);				
				if(Utils.isFileExist(OutputFile))
				{
					System.out.println("Step 10: Emailed-Endorsement-ONLY XML extracted successfully - PASSED");
					Log.info("Step 10: Emailed-Endorsement-ONLY XML extracted successfully - PASSED");
					Reporter.log("Step 10: Emailed-Endorsement-ONLY XML extracted successfully - PASSED");
					BaseClass.bResult=true;
				}
				else
				{
					System.out.println("Step 10: Emailed-Endorsement-ONLY XML NOT extracted");
					Log.error("Step 10: Emailed-Endorsement-ONLY XML NOT extracted");
					Reporter.log("Step 10: Emailed-Endorsement-ONLY XML NOT extracted");
					//SQLUtils.disconnectUATVPN();
					throw new Exception("Step 10: Emailed-Endorsement-ONLY XML NOT extracted");
				}				
				//Thread.sleep(300);									
			}
			if (Variables.DocumentType.equals(Config.MRC_DocumentType_SS))
			{			
				query = "SELECT [Detail] FROM [SDC_DATA].[SDCR].[Artefacts] Where TransactionID ='"+TransactionID+"' And ArtefactName = '"+Config.DBMRCXML+"'";				
				EmailedXML = SQLUtils.executeSQLQuery(query,Env);			
			
				if (Env.equalsIgnoreCase("ST"))
					OutputFile = Config.Path_XMLFileOutput+"Emailed-ST-"+Config.File_XMLDirectMRC;
				else
					OutputFile = Config.Path_XMLFileOutput+"Emailed-UAT-"+Config.File_XMLDirectMRC;	
				
				XMLUtils.convertStringToXML(EmailedXML,OutputFile);				
				if(Utils.isFileExist(OutputFile))
				{
					System.out.println("Step 10: Emailed-Single Section MRC XML extracted successfully - PASSED");
					Log.info("Step 10: Emailed-Single Section MRC XML extracted successfully - PASSED");
					Reporter.log("Step 10: Emailed-Single Section MRC XML extracted successfully - PASSED");
					BaseClass.bResult=true;
				}
				else
				{
					System.out.println("Step 10: Emailed-Single Section MRC XML NOT extracted");
					Log.error("Step 10: Emailed-Single Section MRC XML NOT extracted");
					Reporter.log("Step 10: Emailed-Single Section MRC XML NOT extracted");
					//SQLUtils.disconnectUATVPN();
					throw new Exception("Step 10: Emailed-Single Section MRC XML NOT extracted");
				}				
				//Thread.sleep(300);									
			}
			//if (Variables.DocumentType.equals(Config.MRC_DocumentType_MS))
			if (Variables.DocumentType.equals(Config.MRC_DocumentType_MS) || Variables.DocumentType.equals(Config.MRC_DocumentType_MSB))
			{							
				List <WebElement> NumofTabs = TransactionDetails_Page.tab_DynamicMRCTabs().findElements(By.xpath(TransactionDetails_Page.DynamicMRCTabsXpath));
				
				for (i=1;i<=NumofTabs.size();i++)
				{
					Thread.sleep(50);
					query = "SELECT [Detail] FROM [SDC_DATA].[SDCR].[Artefacts] Where TransactionID ='"+TransactionID+"' And ArtefactName = '"+Config.DBMRCXML+"  "+i+"'";				
					EmailedXML = SQLUtils.executeSQLQuery(query,Env);			
				
					if (Env.equalsIgnoreCase("ST"))
						OutputFile = Config.Path_XMLFileOutput+"Emailed-ST-"+i+"-"+Config.File_XMLMRCSection;
					else
						OutputFile = Config.Path_XMLFileOutput+"Emailed-UAT-"+i+"-"+Config.File_XMLMRCSection;
					
					XMLUtils.convertStringToXML(EmailedXML,OutputFile);
					if(Utils.isFileExist(OutputFile))
					{
						System.out.println("Step 10a: Emailed-MRC Section-"+i+" XML extracted successfully - PASSED");
						Log.info("Step 10a: Emailed-MRC Section-"+i+" XML extracted successfully - PASSED");
						Reporter.log("Step 10a: Emailed-MRC Section-"+i+" XML extracted successfully - PASSED");
						BaseClass.bResult=true;
					}
					else
					{
						System.out.println("Step 10a: Emailed-MRC Section-"+i+" XML NOT extracted");
						Log.error("Step 10a: Emailed-MRC Section-"+i+" XML NOT extracted");
						Reporter.log("Step 10a: Emailed-MRC Section-"+i+" XML NOT extracted");
						//SQLUtils.disconnectUATVPN();
						throw new Exception("Step 10a: Emailed-MRC Section-"+i+" XML NOT extracted");
					}
				
				}				
				
			}
			SQLUtils.disconnectUATVPN();
		}
		catch(Exception e)
		{
			System.out.println("Step 9: Emailed MRC-Artefacts NOT extracted");
			Log.error("Step 9: Emailed MRC-Artefacts NOT extracted");
			Reporter.log("Step 9: EEmailed MRC-Artefacts NOT extracted");			
			BaseClass.bResult=false;
			SQLUtils.disconnectUATVPN();
			throw (e);			
		}		
		
	}	
	
}