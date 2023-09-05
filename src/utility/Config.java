package utility;

import java.io.File;
import java.util.ArrayList;
 
public class Config 
{
      //public static final String STURL = "https://www.sdc-service-test.com/";
     // public static final String STURL = "https://crm6216cval.sky.blackbaud.com/6216cval/webui/WebShellLogin.aspx?databaseName=6216cval&url=https%3A%2F%2Fcrm6216cval.sky.blackbaud.com%2F6216cval%2Fwebui%2Fwebshellpage.aspx%3Fdatabasename%3D6216cval";   
      public static final String UATURL = "https://www.t.com/";
      public static final String STURL = "https://www.sdc-service-testtt.com/";

      public static final String ChromeDriverPath = "C:\\Selenium\\chromedriver_win32\\chromedriver.exe";
      public static final String FireFoxDriverPath = "C:\\Selenium\\geckodriver-v0.24.0-win64\\geckodriver.exe";
      public static final String DriverPath = "C:\\Selenium\\BrowserDrivers\\";
 
      //public static final String Username = "clement.arockiasamy@lmtom.london";   //"stuser23@test.com";
      //public static final String Password = "SDCLogin321$";        // ST Password
    //  public static final String Username = "clement.se6216CVAL";   //"stuser23@test.com";
    //  public static final String Password = "!ClementOct22!";        // ST Password
 
      public static final String Username = "clement123";   //"stuser23@test.com";
      public static final String Password = "Testing123$!";        // ST Password
 
      
      //public static final String Password = "SDCLogin321$";      // UAT password
      public static final String CarrierReference = "SKIP###-Auto-01-TEST111111";
      public static final String UATVPNConnectionName = "SDC-uattttttt";
      
      /*//***DB Connection Settings - AD windows Authentication with default windows logon
      public static final String DBSTConnectionString = "jdbc:jtds:sqlserver://sdc-sql.sdc-nonprod.net:14333;UseNTLMv2=true;Domain=AD;Trusted_Connection=yes";      //***** WORKING (AD Windows Authentication-No username or password needed)
      public static final String DBUATConnectionString = "jdbc:jtds:sqlserver://sdc-sql.uat.sdc-nonprod.net:1438;UseNTLMv2=true;Domain=AD;Trusted_Connection=yes";  //***** IN Progress (AD Windows Authentication-No username or password needed)
      public static final String DBSTUsername = "";
      public static final String DBSTPassword = "";
      public static final String DBUATUsername = "";
      public static final String DBUATPassword = "";*/   
      
      //***DB Connection Settings - SQL Server Authentication with userId, pwd
      public static final String DBSTConnectionString = "jjjdbc:jtds:sqlserver://sdc-sql.sdc-nonprod.net:14333;UseNTLMv2=trueeeee";      //***** WORKING (AD Windows Authentication-No username or password needed)
      public static final String DBUATConnectionString = "jjjdbc:jtds:sqlserver://sdc-sql.uat.sdc-nonprod.net:1433;UseNTLMv2=trueeeeee";  //***** IN Progress (AD Windows Authentication-No username or password needed)
      
      public static final String DBSTUsername = "SdcAutoTestttttttt";
      public static final String DBSTPassword = "ut*W8K*Qjuuuuuuuuuuuu";
      public static final String DBUATUsername = "SdcAutoTestttttttttt";
      public static final String DBUATPassword = "Cz$663g%r666666666666";
 
      public static final String DBCSVFieldText = "ACORD Plain Text";
      public static final String DBAcordEndorsementXML = "ACORD ENDORSEMENT XML";
      public static final String DBMRCXML = "ACORD XML";  
      public static final String DBConnectionBatchFile = "UATDBConnectionBatch";
      //public static final String DBMRCXML = "ACORD XML  6";      
      //***DB Connection Settings
 
  
      public static final String Path_TestData = "C:\\Automation\\SDC\\src\\testData\\";
      public static final String File_TestData = "TestData.xlsx";
      
      public static final String DIR_InputSource = "C:\\SDC_Automation_Inputs";   	//***len=25
      public static final String DIR_OutputSource = "C:\\SDC_Automation_Outputs";
      //public static final String DIR_Parent = "OutputFiles-SSEWP";													  //*** Change the folder name according to the MRC Types
      public static final String DIR_Parent = "OutputFiles-"+(Config.InputMRCFile.split("\\\\")[3].split("-")[0]);	      //*** Extract the MRC file type and create as an output folder name 		          
      public static final String DIR_Child = DIR_OutputSource+"\\"+DIR_Parent;       	
      public static final String DIR_Fields = DIR_Child+"\\MRCFields";
      public static final String DIR_XML = DIR_Child+"\\XMLs";
      public static final String DIR_Screenshot = DIR_OutputSource+"\\Screenshots";
      public static final String DIR_ZipFiles = DIR_OutputSource+"\\ZipFiles";
      
      //*** HTML Report, Index File and Log File to be copied to one output file.
      //public static final String htmlReportFileSource = "C:\\Automation\\SDC\\test-output\\emailable-report.html";
      public static final String htmlReportFileSource = "C:\\Automation\\SDC\\test-output\\customized-emailable-report.html";
      public static final String htmlReportIndexFileSource = "C:\\Automation\\SDC\\test-output\\index.html";
      public static final String logFileSource = "C:\\Automation\\SDC\\logfile.log";
      //*** HTML Report, Index File and Log File to be copied to one output file.
        
      //***MRC File Types & Inputs - MRC File type(EO,EWSS..) should be added as a "Keyword-"
      public static final String InputMRCFile = DIR_InputSource+"\\SDC_Release_MRCs\\EWSS-MRC.pdf";                           	  //***EWSS Works OK. 
      //public static final String InputMRCFile = DIR_InputSource+"\\SDC_Release_MRCs\\SSEWP-Endorsement_mock_Panels.docx";       //***SS_withPanels WORKS OK    //***len=25+20+
      //public static final String InputMRCFile = DIR_InputSource+"\\SDC_Release_MRCs\\SS-MRC.pdf";      					      //***SS Works OK
      //public static final String InputMRCFile = DIR_InputSource+"\\SDC_Release_MRCs\\EO-Endorsement MRC-Post Bind.pdf";         //***EO Works OK
      //public static final String InputMRCFile = DIR_InputSource+"\\SDC_Release_MRCs\\MS-standard_mock.docx";                    //***MS WORKS OK       
      //public static final String InputMRCFile = DIR_InputSource+"\\SDC_Release_MRCs\\MSB-Binder Mock_final_wodate_11119.doc";   //***MS_withPanels WORKS OK    //***len=25+20+
      
      public static final String Path_MRCFieldOutput = DIR_Child+"\\MRCFields\\";
      public static final String Path_ScreenShot = DIR_OutputSource+"\\Screenshots\\";
      public static final String Path_XMLFileOutput = DIR_Child+"\\XMLs\\";
      public static final String Path_ZipFilesFolder = DIR_Child; 
      public static final String ZipFileName = DIR_ZipFiles+"\\Automation_"+DIR_Parent+".zip";
   
      public static final String File_OutputSTFields = "ST-Page-Fields.xlsx";
      public static final String File_OutputUATFields = "UAT-Page-Fields.xlsx";
      public static final String File_CSVExportSTFields = "ST-Page-Fields-CSV.csv";
      public static final String File_TXTEmailExtractFields = "Page-Fields.txt";
      public static final String File_CSVEmailExtractFields = "Page-Fields-CSV.csv";
      //public static final String File_CSVEmailExtractSTFields = "ST-Email-Fields.txt";
      public static final String File_CSVExportUATFields = "UAT-Page-Fields-CSV.csv";   
      public static final String Sheet_OutputFields = "PageFields";
      public static final String File_CompareOutput = "FieldsCompareOutput.txt";
      
      /*public static final String File_OutputFields = "Page-Fields.xlsx";      
      public static final String File_CSVExportFields = "Page-Fields-CSV.csv";
      //public static final String File_CSVExportUATFields = "UAT-Page-Fields-CSV.csv";   
      public static final String Sheet_OutputFields = "PageFields";
      public static final String File_CompareOutput = "FieldsCompareOutput.txt";*/  
      
      public static final String File_XMLDirectMRC = "MRCSectionONLY-XML.xml";
      public static final String File_XMLDirectEndorsement = "EndorsementONLY-XML.xml";
      public static final String File_XMLEndorsement = "Endorsement-XML.xml";
      public static final String File_XMLMRCSection = "MRCSection-XML.xml";
      public static final String File_XMLSection1 = "MRCSection-1-XML.xml";
      public static final String File_XMLSection2 = "MRCSection-2-XML.xml"; 
      public static final String File_XMLCompareOutput = "XMLCompareOutput.txt";
            
      public static final String MRC_DocumentType_SS = "Single Section MRC";
      public static final String MRC_DocumentType_MS = "Multi Section MRC";
      public static final String MRC_DocumentType_EO = "Endorsement";
      public static final String MRC_DocumentType_EWSS = "Endorsement & Single Section MRC";
      public static final String MRC_DocumentType_MSB = "Multi Section Binding Authority";
      
      /*public static final String File_XMLUATDirect = "UAT-DirectMRCXML.xml";
      public static final String File_XMLUATEndorsement = "UAT-EndorsementXML.xml";
      public static final String File_XMLUATSection1 = "UAT-MRCSection1XML.xml";
      public static final String File_XMLUATSection2 = "UAT-MRCSection2XML.xml";*/
            
      public static final String Email_Sender = "SDCAutomation1@gmail.com";
      public static final String Email_Password = "SDCLogin1233333$";      
      public static final String Email_Recipients = "clement.arockiasamy@lmtom.londonnnnn";
      //public static final String Email_Recipients = "clement.arockiasamy@lmtom.london,clement.sebastin@gmail.com";
      public static final String Email_Subject = "SDC Automation Test Report";
      //public static final String Path_EmailReport = "C:\\TEMP\\OutputFiles\\OutputFiles.7z";
      public static final String File_EmailReport = "Automation_"+DIR_Parent+".7z";     
      public static final String Path_EmailReport2 = "C:\\Automation\\SDC\\test-output\\customized-emailable-report.html";
      public static final String File_EmailReport2 = "Automation_HTML_Test_Report.html";
      //public static final String Path_EmailReport3 = "C:\\Automation\\SDC\\test-output\\index.html";      
      //public static final String File_EmailReport3 = "Automation HTML Index Report.html";
      
            
      //public static final String [] IgnoreFieldlist = {"19.1","19.2","19.3","19.4","19.5","19.6","19.7","19.8"};
      public static final String [] IgnoreFieldlist = {""};
      //public static final String [] IgnoreFieldlist = {"138.1","138.2","138.3","138.4","138.5","138.6"};
      
      public static final String [] IgnoreSectionlist = {"Section 0","Section 1","Section 2","Section 3","Section 4","Section 5","Section 6","Section 7","Section 8","Section 9","Section 10"};
      //public static final String [] IgnoreXMLattributeList = {""};
      public static final String [] IgnoreXMLattributeList = {"CreationDate","SentDate","ServiceProviderReference","SDCVersion","DocumentVersionDtTime","UUId","DocumentId","GroupReference","FileId"};
            
      //Test Data Sheet Columns
      /*public static final int Col_TestCaseName =0;	
      public static final int Col_UserName =1;
      public static final int Col_Password = 2;
      public static final int Col_Browser = 3;
      public static final int Col_ProductType = 4;
      public static final int Col_ProductNumber = 5;
      public static final int Col_FirstName = 6;
      public static final int Col_LastName = 7;
      public static final int Col_Address = 8;
      public static final int Col_City = 9;
      public static final int Col_Country = 10;
      public static final int Col_Phone = 11;
      public static final int Col_Email = 12;
      public static final int Col_Result = 13;*/
      public static final int Total_Rows = 2;
      public static final int Total_Columns = 5;    
 }