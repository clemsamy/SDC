package SDCAutomation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FieldsDisplayedOnTestPortalShouldMatchTheFieldsDisplayedOnBaselinedPortal 
{
	public static void fnFieldsDisplayedOnTestPortalShouldMatchTheFieldsDisplayedOnBaselinedPortal() throws IOException
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\Clem\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriver driver1 = new ChromeDriver();
		String strSTURL= "https://www.sdc-service-test.com/" ;
		String strUATURL= "https://www.sdc-service-uat.com/" ;
		
		SDCPortalOPs objSDC = new SDCPortalOPs();
		
		//*********ST Instance
		objSDC.fnSDCPortalLogin(driver,strSTURL);
		objSDC.fnUploadDocument(driver);
		objSDC.fnViewTransaction(driver);
		objSDC.fnCopySTPageFieldstoExcel(driver);
		//*********ST Instance
		
		//*********UAT Instance
/*		objSDC.fnSDCPortalLogin(driver1,strUATURL);
		objSDC.fnUploadDocument(driver1);
		objSDC.fnViewTransaction(driver1);
		objSDC.fnCopyUATPageFieldstoExcel(driver1);
*/		//*********UAT Instance
				
		objSDC.fnCompareExcelFiles();
						
	}
	
	 

}
