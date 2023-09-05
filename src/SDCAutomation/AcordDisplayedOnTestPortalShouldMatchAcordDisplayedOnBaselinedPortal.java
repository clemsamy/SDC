package SDCAutomation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AcordDisplayedOnTestPortalShouldMatchAcordDisplayedOnBaselinedPortal 
{
	public static void fnAcordDisplayedOnTestPortalShouldMatchAcordDisplayedOnBaselinedPortal() throws IOException
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		/*WebDriver driver1 = new ChromeDriver();
		String strSTURL= "https://www.sdc-service-test.com/" ;
		String strUATURL= "https://www.sdc-service-uat.com/" ;*/
		
		//**** Temporary XML file download & debug 
		String strSTURL= "https://www.sdc-service-test.com/" ;
		//String strSTURL2= "https://www.sdc-service-test.com/DataAdmin/TransactionDetails/ca4af3c2-b6d7-4f5a-8cbc-053161fdb7da" ;
		//String strSTURL2= "https://www.sdc-service-test.com/DataAdmin/TransactionDetails/bdd5c4c5-f563-433f-9954-ebec7eb3ae0d" ;
		String strSTURL2= "https://www.sdc-service-test.com/DataAdmin/TransactionDetails/7202321e-2511-4ef1-95da-b15ac044f95d" ;  //Endorsement + 1 MRC tab
		//String strSTURL2= "https://www.sdc-service-test.com/DataAdmin/TransactionDetails/0e32f272-3343-4905-834f-83c9a2f0eeee" ;    //No tab XML
				
		//**** Temporary CSV file download & debug
		
		SDCPortalOPs objSDC = new SDCPortalOPs();
		
		//**** Debug without uploading file
		objSDC.fnSDCPortalLogin(driver,strSTURL); //****** SYS-TEST URL staring from home page and with file processing...
		driver.get(strSTURL2); //****** SYS-TEST URL mainly for the uploaded file page only*/		
		objSDC.fnCopySTPageXMLtoExcel(driver);
		//objSDC.fnDownloadandCountCSVFile(driver);
		//**** Debug without uploading file
						
		//*********ST Instance
		/*objSDC.fnSDCPortalLogin(driver,strSTURL);
		objSDC.fnUploadDocument(driver);
		objSDC.fnViewTransaction(driver);
		objSDC.fnCopySTPageFieldstoExcel(driver);*/
		//*********ST Instance
		
		//*********UAT Instance
/*		objSDC.fnSDCPortalLogin(driver1,strUATURL);
		objSDC.fnUploadDocument(driver1);
		objSDC.fnViewTransaction(driver1);
		objSDC.fnCopyUATPageFieldstoExcel(driver1);
*/		//*********UAT Instance
				
		//objSDC.fnCompareExcelFiles();
						
	}
}
