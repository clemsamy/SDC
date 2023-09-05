package testCases;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import utility.Config;
import utility.Log;
import utility.Utils;
import utility.Variables;
import appModules.CompareMRCXML_Action;



public class AcordDisplayedOnTestPortalShouldMatchAcordDisplayedOnBaselinedPortal
{
	public WebDriver driver;
	private String sTestCaseName;
	
	@BeforeMethod
	public void beforeMethod() throws Exception
	{
	 	DOMConfigurator.configure("log4j.xml");		  
	  	sTestCaseName = this.toString();
	  	sTestCaseName = Utils.getTestCaseName(this.toString());
		Log.startTestCase(sTestCaseName);
		Utils.startTimer();
	}

	@Test (priority = 1)
	@Parameters({"MRCType"})
	public void Execute(String MRCType) throws Exception
    {
		   Variables.OutputBuffer=""; 
		   int i;
		   
		   try
		   { 
			   Utils.setMRCType(MRCType);
			   if (Variables.DocumentType.equals(Config.MRC_DocumentType_EWSS))
			   {
				    boolean failed = false;
		    	    String errmessage="";				
			        try{     
			    	    Variables.OutputBuffer = Variables.OutputBuffer + "\n====================================================";
				        Variables.OutputBuffer = Variables.OutputBuffer + "\nDocument Type: "+Config.MRC_DocumentType_EWSS;			        
				        Variables.OutputBuffer = Variables.OutputBuffer + "\nPart-1: ST Vs UAT Endorsement XML Comparison Results";
				        Variables.OutputBuffer = Variables.OutputBuffer + "\n====================================================";      	    
			      	    		      	    
					    CompareMRCXML_Action.Execute(Config.Path_XMLFileOutput,"ST-Page-"+Config.File_XMLEndorsement,"UAT-Page-"+Config.File_XMLEndorsement);
					    Utils.writeFileCompareOutput(Config.Path_XMLFileOutput,"OnPage-XMLs-STVsUAT-"+Config.File_XMLCompareOutput,Variables.OutputBuffer);
						if(BaseClass.bResult==true)
						{
							//ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
							Reporter.log("ST Vs UAT Endorsement XML(Part-1) verified successfully");
							Log.info("ST Vs UAT Endorsement XML(Part-1) verified successfully");
						}
						else
						{
							Reporter.log("ST Vs UAT Endorsement XML(Part-1) Comparision Failed. Please see the comparision output error log file for more info");
							Log.error("ST Vs UAT Endorsement XML(Part-1) Comparision Failed. Please see the comparision output error log file for more info");
							failed = true;
							throw new Exception("Test Case Failed because of Endorsement XML(Part-1) verification");
						}    
			         }catch (Exception e){
					          sTestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
							  Log.error(e.getMessage());
							  errmessage = errmessage + " & "+e.getMessage();
							  //Log.info("I am in inner catch");
							  //throw (e);
							  }				      
					
				    Variables.OutputBuffer = Variables.OutputBuffer + "\n======================================================";
		      	    Variables.OutputBuffer = Variables.OutputBuffer + "\nPart-2: ST Vs UAT MRC SECTION-1 XML Comparison Results";
		      	    Variables.OutputBuffer = Variables.OutputBuffer + "\n======================================================";     	    
	      		      	
				    CompareMRCXML_Action.Execute(Config.Path_XMLFileOutput,"ST-Page-"+Config.File_XMLSection1,"UAT-Page-"+Config.File_XMLSection1);
				    Utils.writeFileCompareOutput(Config.Path_XMLFileOutput,"OnPage-XMLs-STVsUAT-"+Config.File_XMLCompareOutput,Variables.OutputBuffer);
					if(BaseClass.bResult==true)
					{
						//ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
						Reporter.log("ST Vs UAT Section-1 XML(Part-2) verified successfully");
						Log.info("ST Vs UAT Section-1 XML(Part-2) verified successfully");
					}
					else
					{
						Reporter.log("ST Vs UAT Section-1 XML(Part-2) Comparision Failed. Please see the comparision output error log file for more info");
						Log.error("ST Vs UAT Section-1 XML(Part-2) Comparision Failed. Please see the comparision output error log file for more info");
						errmessage = errmessage + " & "+"Test Case Failed because of Section-1 XML(Part-2) verification";
						failed = true;
						//throw new Exception("Test Case Failed because of Section-1 XML(Part-2) verification");						
					}
					if(failed)
						throw new Exception(errmessage);				
			       
			   }
			   if (Variables.DocumentType.equals(Config.MRC_DocumentType_EO))
			   {
				    Variables.OutputBuffer = Variables.OutputBuffer + "\n=================================================";
			        Variables.OutputBuffer = Variables.OutputBuffer + "\nDocument Type: "+Config.MRC_DocumentType_EO;			        
			        Variables.OutputBuffer = Variables.OutputBuffer + "\nST Vs UAT Endorsement-ONLY XML Comparison Results";
			        Variables.OutputBuffer = Variables.OutputBuffer + "\n=================================================";      	    
		      	     
				    CompareMRCXML_Action.Execute(Config.Path_XMLFileOutput,"ST-Page-"+Config.File_XMLDirectEndorsement,"UAT-Page-"+Config.File_XMLDirectEndorsement);
				    Utils.writeFileCompareOutput(Config.Path_XMLFileOutput,"OnPage-XMLs-STVsUAT-"+Config.File_XMLCompareOutput,Variables.OutputBuffer);
					if(BaseClass.bResult==true)
					{
						//ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
						Reporter.log("ST Vs UAT Endorsement-ONLY XML verified successfully");
						Log.info("ST Vs UAT Endorsement-ONLY XML verified successfully");
					}
					else
					{
						Reporter.log("ST Vs UAT Endorsement-ONLY XML Comparision Failed. Please see the comparision output error log file for more info");
						Log.error("ST Vs UAT Endorsement-ONLY XML Comparision Failed. Please see the comparision output error log file for more info");
						throw new Exception("Test Case Failed because of Endorsement-ONLY verification");
					}			
				}
			    if (Variables.DocumentType.equals(Config.MRC_DocumentType_SS))
			    {
				    Variables.OutputBuffer = Variables.OutputBuffer + "\n====================================================";
			        Variables.OutputBuffer = Variables.OutputBuffer + "\nDocument Type: "+Config.MRC_DocumentType_SS;			        
			        Variables.OutputBuffer = Variables.OutputBuffer + "\nST Vs UAT Single Section MRC XML Comparison Results";
			        Variables.OutputBuffer = Variables.OutputBuffer + "\n====================================================";      	    
		      	     
				    CompareMRCXML_Action.Execute(Config.Path_XMLFileOutput,"ST-Page-"+Config.File_XMLDirectMRC,"UAT-Page-"+Config.File_XMLDirectMRC);
				    Utils.writeFileCompareOutput(Config.Path_XMLFileOutput,"OnPage-XMLs-STVsUAT-"+Config.File_XMLCompareOutput,Variables.OutputBuffer);
					if(BaseClass.bResult==true)
					{
						//ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
						Reporter.log("ST Vs UAT Single Section MRC XML verified successfully");
						Log.info("ST Vs UAT Single Section MRC XML verified successfully");
					}
					else
					{
						Reporter.log("ST Vs UAT Single Section MRC XML Comparision Failed. Please see the comparision output error log file for more info");
						Log.error("ST Vs UAT Single Section MRC XML Comparision Failed. Please see the comparision output error log file for more info");
						throw new Exception("Test Case Failed because of Single Section MRC verification");
					}			
				}			
			    //if (Variables.DocumentType.equals(Config.MRC_DocumentType_MS))
			    if (Variables.DocumentType.equals(Config.MRC_DocumentType_MS) || Variables.DocumentType.equals(Config.MRC_DocumentType_MSB))
			    {	
				    boolean failed = false;
				    String errmessage="";
				    for (i=1;i<=Variables.intNumOfMRCSections;i++)
				    {
					    try{
					    	Variables.OutputBuffer = Variables.OutputBuffer + "\n==============================================================";
				      	    Variables.OutputBuffer = Variables.OutputBuffer + "\nST Vs UAT MRC SECTION-"+i+" XML Comparison Results";
				      	    Variables.OutputBuffer = Variables.OutputBuffer + "\n==============================================================";     	    
			      		      	
						    CompareMRCXML_Action.Execute(Config.Path_XMLFileOutput,"ST-Page-"+i+"-"+Config.File_XMLMRCSection,"UAT-Page-"+i+"-"+Config.File_XMLMRCSection);
						    Utils.writeFileCompareOutput(Config.Path_XMLFileOutput,"OnPage-XMLs-STVsUAT-"+Config.File_XMLCompareOutput,Variables.OutputBuffer);
							if(BaseClass.bResult==true)
							{
								//ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
								Reporter.log("ST Vs UAT Section-"+i+" XML verified successfully");
								Log.info("ST Vs UAT Section-"+i+" XML verified successfully");
							}
							else
							{
								Reporter.log("ST Vs UAT Section-"+i+" XML Comparision Failed. Please see the comparision output error log file for more info");
								Log.error("ST Vs UAT Section-"+i+" XML Comparision Failed. Please see the comparision output error log file for more info");								
								//Log.error("Test Case Failed because of Section-"+i+" XML verification");
								failed = true;
								throw new Exception("Test Case Failed because of Section-"+i+" XML verification");
							}
					    }
					    catch (Exception e){
					          sTestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
							  Log.error(e.getMessage());
							  errmessage = errmessage + " & "+e.getMessage();
							  //Log.info("I am in inner catch");
							  //throw (e);
					    }
					}
				    if (failed){
				    	throw new Exception(errmessage);
				    }
				   
			    }
		        // Need to write a code for different MRC types  - "Single Section MRC(no xml)" , "Endorsement & Single Section MRC(2 tabs)"
		        //Utils.writeFileCompareOutput(Config.Path_XMLFileOutput,"OnPage-"+Config.File_XMLCompareOutput,Variables.OutputBuffer);	   
		  }
		   
		  catch (Exception e)
		  {		
			  sTestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			  //ExcelUtils.setCellData("Fail", iTestCaseRow, Constant.Col_Result);
			  //Utils.takeScreenshot(driver, sTestCaseName);
			  Log.error(e.getMessage());
			  //Log.info("I am in outer catch");
			  throw (e);
		  }		
    }
	
	@AfterMethod
	public void afterMethod() 
	{	    
	    //Log.info("Performance: The total time taken to complete the test is: "+Utils.stopTimer()+ " min(s)");  
		Log.endTestCase(sTestCaseName);
	    //Utils.sendEmailWithAttachment();
	    // driver.close();
	}

	
}
