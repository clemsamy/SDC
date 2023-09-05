package SDCAutomation;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FieldsOnPageShouldMatchTheFieldsInDownloadedFile 
{

	public static void fnFieldsOnPageShouldMatchTheFieldsInDownloadedFile() throws IOException
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\Clem\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
				
	
		//**** Temporary CSV file download & debug 
		String strSTURL= "https://www.sdc-service-test.com/" ;
		//String strSTURL2= "https://www.sdc-service-test.com/DataAdmin/TransactionDetails/ca4af3c2-b6d7-4f5a-8cbc-053161fdb7da" ;
		//String strSTURL2= "https://www.sdc-service-test.com/DataAdmin/TransactionDetails/bdd5c4c5-f563-433f-9954-ebec7eb3ae0d" ;
		String strSTURL2= "https://www.sdc-service-test.com/DataAdmin/TransactionDetails/7202321e-2511-4ef1-95da-b15ac044f95d" ;
		//**** Temporary CSV file download & debug
		
		SDCPortalOPs objSDC = new SDCPortalOPs();
		
		//*********ST Instance
		/*objSDC.fnSDCPortalLogin(driver,strSTURL); //****** SYS-TEST URL staring from home page and with file processing...
		objSDC.fnUploadDocument(driver);
		objSDC.fnViewTransaction(driver);
		objSDC.fnCopySTPageFieldstoExcel(driver);
		objSDC.fnDownloadandCountCSVFile(driver);*/
		//*********ST Instance
		
		//***Debug without uploading file
		objSDC.fnSDCPortalLogin(driver,strSTURL); //****** SYS-TEST URL staring from home page and with file processing...
		driver.get(strSTURL2); //****** SYS-TEST URL mainly for the uploaded file page only*/		
		objSDC.fnCopySTPageFieldstoExcel(driver);
		//objSDC.fnDownloadandCountCSVFile(driver);
		//***Debug without uploading file
		
		//*********UAT Instance
/*		objSDC.fnSDCPortalLogin(driver1,strUATURL);
		objSDC.fnUploadDocument(driver1);
		objSDC.fnViewTransaction(driver1);
		objSDC.fnCopyUATPageFieldstoExcel(driver1);
*/		//*********UAT Instance
				
		//objSDC.fnCompareExcelFiles();					
	}
}
