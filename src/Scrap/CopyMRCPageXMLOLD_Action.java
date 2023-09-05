package Scrap;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import pageObjects.BaseClass;
import pageObjects.TransactionDetails_Page;
import utility.Config;
import utility.ConvertStringToXML;
import utility.Log;
import utility.WriteExcelFile;
import utility.XMLUtils;


public class CopyMRCPageXMLOLD_Action extends BaseClass
{
	public CopyMRCPageXMLOLD_Action(WebDriver driver) 
	{
		super(driver);
		
		// TODO Auto-generated constructor stub
	}
	public static void Execute(String Env) throws Exception
    {
		int XMLType=0;
		String OutputFile;
		try
		{				
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			try
			{
				if (driver.findElements(By.xpath("/html/body/div[2]/div/ul/li[1]/a")).size()!=0)
				{
					XMLType=1;
					//WebElement XMLTab = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/a"));
					WebElement XMLTab = TransactionDetails_Page.tab_EndorsementXMLTab1();
					XMLTab.click();
					Thread.sleep(100);
					//System.out.println("I am in Endorsement tab 1");
					//String XMLEndorsementFieldValueText = driver.findElement(By.xpath("//*[@id=\"tabs-1\"]/div[3]")).getText();
					String XMLEndorsementFieldValueText = TransactionDetails_Page.tbl_EndorsementsXMLTable1().getText();
					
					//ConvertStringToXML objXML = new ConvertStringToXML();	
					if (Env.equalsIgnoreCase("ST"))
						OutputFile = Config.Path_XMLFileOutput+"ST-Page-"+Config.File_XMLEndorsement;
					else
						OutputFile = Config.Path_XMLFileOutput+"UAT-Page-"+Config.File_XMLEndorsement;
					
					XMLUtils.convertStringToXML(XMLEndorsementFieldValueText,OutputFile);
					
				}
			} 
			catch(NoSuchElementException e) 
			{
				System.out.println("Unable to click the tab. Reason: " + e.toString());
				Log.info("Unable to click the tab. Reason: " + e.toString());
			}
			try
			{
				if (driver.findElements(By.xpath("/html/body/div[2]/div/ul/li[2]/a")).size()!=0)
				{
					
					XMLType=2;
					//WebElement XMLTab = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[2]/a"));
					WebElement XMLTab = TransactionDetails_Page.tab_MRCXMLTab2();
					XMLTab.click();					
					Thread.sleep(100);
					//System.out.println("I am in MRC tab 2");
					//String XMLMRCFieldValueSection1 = driver.findElement(By.xpath("//*[@id=\"tabs-2\"]/div[3]")).getText();
					String XMLMRCFieldValueSection1 = TransactionDetails_Page.tbl_MRCXMLTable2().getText();
					//ConvertStringToXML objXML = new ConvertStringToXML();				
					if (Env.equalsIgnoreCase("ST"))
						OutputFile = Config.Path_XMLFileOutput+"ST-Page-"+Config.File_XMLSection1;
					else
						OutputFile = Config.Path_XMLFileOutput+"UAT-Page-"+Config.File_XMLSection1;
					
					XMLUtils.convertStringToXML(XMLMRCFieldValueSection1,OutputFile);
									
				}
			} 
			catch(NoSuchElementException e) 
			{
				System.out.println("Unable to click the tab. Reason: " + e.toString());
				Log.info("Unable to click the tab. Reason: " + e.toString());
			}
			
			try
			{
				if (driver.findElements(By.xpath("/html/body/div[2]/div/ul/li[3]/a")).size()!=0) 
				{
					XMLType=3;
					//WebElement XMLTab = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[3]/a"));
					WebElement XMLTab = TransactionDetails_Page.tab_MRCXMLTab3();
					XMLTab.click();					
					Thread.sleep(1000);
					//System.out.println("I am in MRC tab 3");
					//String XMLMRCFieldValueSection2 = driver.findElement(By.xpath("//*[@id=\"tabs-3\"]/div[3]")).getText();
					String XMLMRCFieldValueSection2 = TransactionDetails_Page.tbl_MRCXMLTable3().getText();
					//ConvertStringToXML objXML = new ConvertStringToXML();				
					if (Env.equalsIgnoreCase("ST"))
						OutputFile = Config.Path_XMLFileOutput+"ST-Page-"+Config.File_XMLSection2;
					else
						OutputFile = Config.Path_XMLFileOutput+"UAT-Page-"+Config.File_XMLSection2;
					
					XMLUtils.convertStringToXML(XMLMRCFieldValueSection2,OutputFile);
										
				}
			} 
			catch(NoSuchElementException e) 
			{
			    System.out.println("Unable to click the tab. Reason: " + e.toString());
			    Log.info("Unable to click the tab. Reason: " + e.toString());
			}
			
			//No tab XML web page. Not going through if the previous condition fails. Need Fixing.
			if (XMLType==0)
			{
				//System.out.println("I am Not in any Tabs");
				String XMLMRCFieldValueDirect = TransactionDetails_Page.tbl_MRCXMLonlyTable().getText();
				//String XMLMRCFieldValueDirect = driver.findElement(By.xpath("/html/body/div[2]/div/pre")).getText();
				//ConvertStringToXML objXML = new ConvertStringToXML();				
				//objXML.convertStringToXML(XMLMRCFieldValueDirect,XMLType);			
				if (Env.equalsIgnoreCase("ST"))
					OutputFile = Config.Path_XMLFileOutput+"ST-Page-"+Config.File_XMLDirectEndorsement;
				else
					OutputFile = Config.Path_XMLFileOutput+"UAT-Page-"+Config.File_XMLDirectEndorsement;
				
				XMLUtils.convertStringToXML(XMLMRCFieldValueDirect,OutputFile);
				
			}		
			
			/*String XMLFieldValueText = driver.findElement(By.xpath("//*[@id=\"tabs-2\"]/div[3]")).getText();
		    ConvertStringToXML objXML = new ConvertStringToXML();				
			objXML.convertStringToXML(XMLFieldValueText); */
			
			//System.out.println("Step 6: Transaction XML Details Copied successfully");
			Log.info("Step 6: Transaction OnPage XML Details Copied successfully");
		}
		catch (Exception e) 
		{
	          //e.printStackTrace();
	    } 
		Reporter.log("Step 7: Transaction OnPage XML copied to xml file successfully");
    }	
}
