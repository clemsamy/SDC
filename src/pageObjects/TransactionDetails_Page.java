package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;

public class TransactionDetails_Page extends BaseClass
{
	  private static WebElement element = null;
	  public static String DynamicMRCTabsXpath = "";
	  public static String DynamicMRCSection_TabXpath_Part1="";
	  public static String DynamicMRCSection_TabXpath_Part2="";
	  public static String DynamicMRCSection_TableXpath_Part1="";
	  public static String DynamicMRCSection_TableXpath_Part2="";
	  public static String DynamicMRCSection_ExportXpath_Part1="";
	  public static String DynamicMRCSection_ExportXpath_Part2="";
	  	  
	  public TransactionDetails_Page(WebDriver driver)
	  {
	  	super(driver);
	  }    
	 
	  public static WebElement tbl_ExtractedData()   //MRC Field table
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/table"));
			 	Log.info("--Field Data table is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Field Data table is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement btn_ExportFieldToCSV()   //MRC field export button
	  {	     
		 try{
			 	element = driver.findElement(By.cssSelector("body > div.container.body-content > div > table > tbody > tr > td:nth-child(2) > a"));
			 	Log.info("--Field Export to CSV button is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Field Export to CSV button is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement tab_EndorsementXMLTab1() // Endorsement XML tab
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/a"));
			 	Log.info("--Endorsement XML tab is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Endorsement XML tab is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement tab_MRCXMLTab2()  //MRC XML tab(Section-1)
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[2]/a"));			 	
			 	Log.info("--MRC XML tab 1 is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--MRC XML tab 1 is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement tab_MRCXMLTab3()   //MRC XML tab2(Section-2)
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[3]/a"));
			 	Log.info("--MRC XML tab 2 is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--MRC XML tab 2 is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement tab_DynamicMRCTabs()   //MRC XML tab2(Section-2)
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li"));
			 	DynamicMRCTabsXpath = "/html/body/div[2]/div/ul/li";
			 	DynamicMRCSection_TabXpath_Part1 = "/html/body/div[2]/div/ul/li[";
			 	DynamicMRCSection_TabXpath_Part2 = "]/a";		
			 	Log.info("--Multi Section MRC XML tabs are found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Multi Section MRC XML tabs are not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement tab_FindDynamicMRCTabs(String xpath,int tabNumber)   //MRC XML tab2(Section-2)
	  {	     
		 try{
			 	element = driver.findElement(By.xpath(xpath));
			 	Log.info("--Multi Section MRC XML tab-"+tabNumber+" is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Multi Section MRC XML tab-"+tabNumber+" not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	
	  public static WebElement tbl_DynamicMRCTables()   //MRC XML tab2(Section-2)
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("//*[@id=\"tabs-1\"]/div[3]"));			 	
			 	DynamicMRCSection_TableXpath_Part1 = "//*[@id=\"tabs-";
			 	DynamicMRCSection_TableXpath_Part2 = "\"]/div[3]";		
			 	Log.info("--Multi Section MRC XML field tables are found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Multi Section MRC XML field table are not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement tbl_FindDynamicMRCTables(String xpath,int tabNumber)   //MRC XML tab2(Section-2)
	  {	     
		 try{
			 	element = driver.findElement(By.xpath(xpath));
			 	Log.info("--Multi Section MRC XML table-"+tabNumber+" is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Multi Section MRC XML table-"+tabNumber+" not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement btn_DynamicExportToXML()  //Export MRC Section-1 XML button
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("//*[@id=\"tabs-1\"]/div[2]/a"));
			 	DynamicMRCSection_ExportXpath_Part1 = "//*[@id=\"tabs-";
			 	DynamicMRCSection_ExportXpath_Part2 = "\"]/div[2]/a";			
			 	Log.info("--Multi Section MRC Export to XML buttons are found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Multi Section MRC Export to XML buttons are not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement btn_FindDynamicExportToXML(String xpath,int tabNumber)   //MRC XML tab2(Section-2)
	  {	     
		 try{
			 	element = driver.findElement(By.xpath(xpath));
			 	Log.info("--Multi Section MRC Export to XML button-"+tabNumber+" is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Multi Section MRC Export to XML button-"+tabNumber+" not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }

	
	  public static WebElement tbl_EndorsementsXMLTable1() //Endorsement XML table 
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("//*[@id=\"tabs-1\"]/div[3]"));
			 	Log.info("--Endorsement XML field table is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Endorsement XML field table is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement tbl_MRCXMLTable2()  //MRC XML table (Section-1)
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("//*[@id=\"tabs-2\"]/div[3]"));
			 	Log.info("--MRC XML field table 1 is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--MRC XML field table 1 is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement tbl_MRCXMLTable3()  //MRC XML table (Section-1)
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("//*[@id=\"tabs-3\"]/div[3]"));
			 	Log.info("--MRC XML field table 2 is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--MRC XML field table 2 is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement tbl_MRCXMLonlyTable() //MRC XML only table (Single Section MRC)
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/pre"));		 	
			 	Log.info("--MRC only XML field table is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--MRC only XML field table is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement tbl_EndorsementXMLonlyTable() //Endorsement XML ONLY table (Endorsement MRC)
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/pre"));		 	
			 	Log.info("--Endorsement only XML field table is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Endorsement only XML field table is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement tbl_SingleSectionMRCXMLonlyTable() //Single Section MRC XML ONLY table (Single Section MRC)
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/pre"));		 	
			 	Log.info("--Single Section MRC only XML field table is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Single Section MRC only XML field table is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  
	
	
	  public static WebElement btn_ExportToXMLEndorsement()   //Export Endorsement XML button
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("//*[@id=\"tabs-1\"]/div[2]/a"));			 										   
			 	Log.info("--Endorsement Export to XML button is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Endorsement Export to XML button is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement btn_ExportToXMLMRCSection1()  //Export MRC Section-1 XML button
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("//*[@id=\"tabs-2\"]/div[2]/a"));
			 	Log.info("--MRC Section-1 Export to XML button is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--MRC Section-1 Export to XML button is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement btn_ExportToXMLMRCSection2()   //Export MRC Section-2 XML button
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("//*[@id=\"tabs-3\"]/div[2]/a"));
			 	Log.info("--MRC Section-2 Export to XML button is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--MRC Section-2 Export to XML button is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }  
  
	  public static WebElement btn_ExportToXMLEndorsementOnly()   //Export Endorsement XML ONLY button
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr/td[2]/a"));			 										   
			 	Log.info("--Endorsement only Export to XML button is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Endorsement only Export to XML button is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  public static WebElement btn_ExportToXMLSingleSectionMRC()   //Export Single Section MRC XML ONLY button
	  {	     
		 try{
			 	element = driver.findElement(By.xpath("/html/body/div[2]/div/table[2]/tbody/tr/td[2]/a"));			 										   
			 	Log.info("--Single Section MRC Export to XML button is found on the Transaction Details page");
	        }
		    catch (Exception e)
	        {
	    		Log.error("--Single Section MRC Export to XML button is not found on the Transaction Details page");
	    		throw(e);
	    	}
	    	return element;
	  }
	  
	  
	 
}
