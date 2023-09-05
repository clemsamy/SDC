package SDCAutomation;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import pageObjects.SignedIn_Page;

import utility.COMPAREEXCEL;
import utility.FluentWaitNew;
import utility.HighLight;
import utility.ReadExcelFile;
import utility.Utils;
import utility.WriteExcelFile;


@SuppressWarnings("unused")
public class SDCSysTestLogin 
{
	//@Test(enabled = true)
	@Test
	//public void main(String[] args) throws IOException
	public void fnSDCSysTestLogin() throws IOException, InterruptedException
	{
		//***Step 1 - Launch
		//*** Execute Test in Chrome******
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.sdc-service-test.com/"); //****** SYS-TEST URL
		//driver.get("https://www.sdc-service-uat.com/"); //****** UAT URL
		WebDriverWait wait=new WebDriverWait(driver, 20);								
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.SECONDS);		
		System.out.println("\nStep 1: SDC-SysTest Home Page Launched successfully");
		driver.findElement(By.xpath("//*[@id='signinLink']")).click();
		
		//***Step 2 - Login
		driver.findElement(By.xpath("//*[@id='Email']")).sendKeys("clement.arockiasamy@lmtom.london"); //Enter UserName
		driver.findElement(By.xpath("//*[@id='Password']")).sendKeys("SDClogin123$"); //Enter Pin
		driver.findElement(By.xpath("//*[@id='login']")).click();
		
		Thread.sleep(2000);
		
		//Utils.waitForElement(SignedIn_Page.btn_AcceptCookies());
	    //SignedIn_Page.btn_AcceptCookies().click();	     
		
		System.out.println("\nStep 2: SDC-SysTest Login completed successfully");
		//Utils.waitForElement(SignedIn_Page.btn_AcceptCookies());
        //SignedIn_Page.btn_AcceptCookies().click();        
		
		//***Step 3 - Upload Document Tab
		driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[1]/a")).click();  //***-- SYSTEST Upload button
				
		driver.findElement(By.xpath("//*[@id='CarrierReference']")).sendKeys("SKIP###-Auto-01-TEST1");
		driver.findElement(By.xpath("//*[@id='file']")).sendKeys("C:\\TEMP\\MRCs\\MRC2.pdf");
		driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();   //**** SYSTEST Upload button
		//driver.findElement(By.xpath("/html/body/div[2]/div/form/div/table/tbody/tr[7]/td[2]/button")).click();  //***-- UAT Upload button
		
		//WebElement dynamicElement = (new WebDriverWait(driver, 10))
			//	  .until(ExpectedConditions.presenceOfElementLocated(By.id("uploadDocument")));
		//System.out.println("Test Line 1");
		
		WebElement dynamicElement = (new WebDriverWait(driver, 10))
			  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='uploadDocument']/div/div")));
		
		//System.out.println("Test Line 2");
		
		//**** Click the Cookie ACCEPT button
		//driver.findElement(By.xpath("/html/body/div[1]/div/a[1]")).click(); //**** Click the Cookie ACCEPT button
		//driver.findElement(By.xpath("//a[@class='button allow']/span[text()='Accept']")).click();
		//driver.findElement(By.xpath("//a[@class='cc_btn cc_btn_accept_all']/span[text()='Accept']")).click();             ******* should click after upload
		//**** Click the Cookie ACCEPT button
		
		//System.out.println("Test Line 3");
		
		String ResponseText = driver.findElement(By.xpath("//*[@id='uploadDocument']/div/div")).getText();
		String ExtractedText = ResponseText.substring(2,ResponseText.length()-31);
		String RequestID = ResponseText.substring(49,ResponseText.length()-1);
		//System.out.println("Extracted Text="+ExtractedText);     ** To display the response text
		//System.out.println("Request ID="+RequestID);    ** To display the response ID
		
		
		if(ExtractedText.equals("Request submitted successfully"))
		{
			System.out.println("\nStep 3: SDC-SysTest MRC document upload completed successfully ");
			System.out.println(ResponseText.substring(2));
			Thread.sleep(2000);
			//Utils.waitForElement(SignedIn_Page.btn_AcceptCookies());
		    //SignedIn_Page.btn_AcceptCookies().click();	     
			
		}
		else
		{
			System.out.println("\nStep 3: SDC-SysTest document upload NOT completed");
			System.out.println(ResponseText.substring(2));
		}		
		//***Step 4 - View Transaction
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/a")).click();
		System.out.println("\nStep 4: View Transaction Page displayed successfully");

		//********* Wait until file is processed***********************
		String TranStatus = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/div[2]/div/div[1]/div[2]/div[1]")).getText();
		//System.out.println("Transaction Status:"+TranStatus);
		FluentWaitNew objWait = new FluentWaitNew();	
		
		long startTime = System.currentTimeMillis();
		int flag = 0;
		
		while(!(TranStatus.substring(8)).equals("Processed"))
		{
		   if(TranStatus.substring(8).equals("Processed"))
		   {
			   System.out.println("The uploaded file processed");
			   break;
		   }
		   else
		   {
			   if (flag == 0) { System.out.println("The uploaded file is being processed in Kofax..."); }
			   flag = 1;
			   //******* Fluent Wait Refresh page***************
			   //FluentWaitNew objWait = new FluentWaitNew();
			   WebElement objStatus = objWait.fluentWaitMethod(By.xpath("/html/body/div[2]/div/ul/li[1]/div[2]/div/div[1]/div[2]/div[1]"),driver);
			   TranStatus = objStatus.getText();
			   driver.navigate().refresh();
			   //******* Fluent Wait Refresh page***************
		   }					
		}
		//********* Wait until file is processed***********************
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = (stopTime - startTime) / 60000;
	    
	    System.out.println("Total time taken for the file processing: "+ elapsedTime + " min(s)");  
					
		String handle= driver.getWindowHandle();
		//System.out.println("Current Window Handle=" + handle);
		 
		//***Step 5 - View Details of Transaction
		driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/div[2]/div/div[2]/div[2]/a")).click();
		System.out.println("\nStep 5: Transaction Details Page displayed successfully");
				
		// Store and Print the name of all the windows open	              
		Set handles = driver.getWindowHandles();
		
		//System.out.println(handles);
		// Pass a window handle to the other window
		 
		for (String handle1 : driver.getWindowHandles()) 
		{
		    //System.out.println("New Window Handle= " + handle1);
		    driver.switchTo().window(handle1);
		}
			
		//Method - 2 : Extract Data from table - Working
		WebElement statusTable = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/table"));															  
		String FieldValueText = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/table")).getText();
		
		///html/body/div[3]/div/div[1]/table/tbody
		///html/body/div[3]/div/div[1]/table
		
		//System.out.println("Field Value = "+FieldValueText);   ************** Printing the copied fields
		//System.out.println("\nStep 6: Transaction Details Page Copied successfully");
		List<WebElement> allRows = statusTable.findElements(By.tagName("tr"));
		int rowSize = allRows.size();
		//System.out.println("Number of Rows: " + rowSize);
		System.out.println("Copying the Fields from the Page to the Excel File...");
		System.out.println("\nStep 6: Transaction Details Page Copied successfully");
		//Method - 2 : Extract Data from table - Working
		
		//*** Method 1 --- to write excel File	
		WriteExcelFile objExcelFile1 = new WriteExcelFile();
        //objExcelFile1.writeExcelRow(System.getProperty("user.dir")+"\\src\\testData","SDC-Login.xlsx","LoginName",allRows);
		objExcelFile1.writeExcelRow("C:\\TEMP","SDC-ST-Fields.xlsx","STFields",allRows);
		    
        //***Step 7 - Put Fields on Page copied to excel
        System.out.println("\nStep 7: Fields on Page Written into Excel File successfully");    
        
        //**** COMPARE TWO EXCEL Files(ST & UAT on page fields files)*************** WORKING
         //COMPAREEXCEL objExcelFile2 = new COMPAREEXCEL();
         //objExcelFile2.compareExcel("C:\\TEMP","SDC-ST-Fields.xlsx","SDC-UAT-Fields.xlsx");
        
         //System.out.println("Step 8: Excel comparision successfully completed");    
       //**** COMPARE TWO EXCEL Files*************** WORKING
	}	
}
