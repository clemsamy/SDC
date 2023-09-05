package SDCAutomation;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.nio.file.StandardCopyOption;
import java.lang.InterruptedException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import javax.xml.parsers.ParserConfigurationException;

import utility.COMPAREEXCEL;
import utility.FluentWaitNew;
import utility.HighLight;
import utility.ReadExcelFile;
import utility.WriteExcelFile;
import utility.ConvertStringToXML;

@SuppressWarnings("unused")
public class SDCPortalOPs 
{
	
	public void fnSDCPortalLogin(WebDriver driver, String strURL) throws IOException
	{
			try
			{
					//***Step 1 - Launch
				//*** Execute Test in Chrome******
				System.setProperty("webdriver.chrome.driver", "C:\\Clem\\Selenium\\chromedriver_win32\\chromedriver.exe");
				//WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(strURL); //****** SYS-TEST URL
				//driver.get("https://www.sdc-service-test.com/"); //****** SYS-TEST URL
				//driver.get("https://www.sdc-service-uat.com/"); //****** UAT URL
				WebDriverWait wait=new WebDriverWait(driver, 20);								
				driver.manage().timeouts().implicitlyWait(20000, TimeUnit.SECONDS);		
				System.out.println("Step 1: SDC-SysTest Login Page Launched successfully");
				driver.findElement(By.xpath("//*[@id='signinLink']")).click();
				
				//***Step 2 - Login
				driver.findElement(By.xpath("//*[@id='Email']")).sendKeys("clement.arockiasamy@lmtom.london"); //Enter UserName
				//driver.findElement(By.xpath("//*[@id='Password']")).sendKeys("SDClogin123$"); //Enter Pin
				if (strURL.equals("https://www.sdc-service-test.com/"))
				{
					driver.findElement(By.xpath("//*[@id='Password']")).sendKeys("SDClogin123$"); //Enter ST password
				}
				else
				{
					driver.findElement(By.xpath("//*[@id='Password']")).sendKeys("SDClogin321$"); //Enter UAT Password
				}
				driver.findElement(By.xpath("//*[@id='login']")).click();
				
				System.out.println("Step 2: SDC-SysTest Login successfully completed");
			}
			catch (Exception e) {
		          e.printStackTrace();
		      } 
			
	}
	public void fnUploadDocument(WebDriver driver) throws IOException
	{
			try
			{
				//WebDriver driver = new ChromeDriver();
				//***Step 3 - Upload Document Tab
				driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/ul/li[1]/a")).click();  //***-- SYSTEST Upload button
						
				driver.findElement(By.xpath("//*[@id='CarrierReference']")).sendKeys("SKIP###-Auto-01-TEST1");
				driver.findElement(By.xpath("//*[@id='file']")).sendKeys("C:\\Clem\\MRC2.pdf");
				driver.findElement(By.xpath("//*[@id='btnSubmit']")).click();   //**** SYSTEST Upload button
				WebElement dynamicElement = (new WebDriverWait(driver, 10))
						  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='uploadDocument']/div/div")));
				String ResponseText = driver.findElement(By.xpath("//*[@id='uploadDocument']/div/div")).getText();
				String ExtractedText = ResponseText.substring(2,ResponseText.length()-31);
				String RequestID = ResponseText.substring(49,ResponseText.length()-1);
				if(ExtractedText.equals("Request submitted successfully"))
				{
					System.out.println("Step 3: SDC-SysTest document upload successfully completed");
					System.out.println(ResponseText.substring(2));
				}
				else
				{
					System.out.println("Step 3: SDC-SysTest document upload NOT completed");
					System.out.println(ResponseText.substring(2));
				}
				//driver.findElement(By.xpath("/html/body/div[1]/div/a[1]")).click();   // click the accept cookie button				 
				//driver.findElement(By.cssSelector("body > div.cc_banner-wrapper > div > a.cc_btn.cc_btn_accept_all")).click();
				
			}
			catch (Exception e) {
		          e.printStackTrace();
		      } 
			
	}
	public void fnViewTransaction(WebDriver driver) throws IOException
	{
			try
			{	
				//WebDriver driver = new ChromeDriver();
				//***Step 4 - View Transaction
				driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/ul/li[3]/a")).click();
				System.out.println("Step 4: View Transaction Page displayed successfully");

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
					   if (flag == 0) { System.out.println("The uploaded file still in processing..."); }
					   flag = 1;
					   //******* Fluent Wait Refresh page***************
					   //FluentWaitNew objWait = new FluentWaitNew();
					   WebElement objStatus = objWait.fluentWaitMethod(By.xpath("/html/body/div[2]/div/ul/li[1]/div[2]/div/div[1]/div[2]/div[1]"),driver);
					   TranStatus = objStatus.getText();
					   driver.navigate().refresh();
					   //******* Fluent Wait Refresh page***************
					   
					   
					   //******* Implicit Wait Refresh page***************
					   driver.manage().timeouts().implicitlyWait(9000000, TimeUnit.SECONDS);
					   driver.navigate().refresh();
					   TranStatus = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/div[2]/div/div[1]/div[2]/div[1]")).getText();
					   //******* Implicit Wait Refresh page***************
					   
				   }					
				}
				//********* Wait until file is processed***********************
				long stopTime = System.currentTimeMillis();
			    long elapsedTime = (stopTime - startTime) / 60000;
			    System.out.println("Total time taken for the file processing: "+ elapsedTime + " min(s)");
			    
			    String handle= driver.getWindowHandle();
			    driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/div[2]/div/div[2]/div[2]/a")).click();
				System.out.println("Step 5: Transaction Details Page displayed successfully");
				
				Set handles = driver.getWindowHandles();
				for (String handle1 : driver.getWindowHandles()) 
				{
				    //System.out.println("New Window Handle= " + handle1);
				    driver.switchTo().window(handle1);
				}
							
			}
			catch (Exception e) 
			{
		       e.printStackTrace();
		    } 
			
	}
	public void fnCopySTPageFieldstoExcel_old(WebDriver driver) throws IOException
	{
			try
			{
				//WebDriver driver = new ChromeDriver();
				WebElement statusTable = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/table"));															  
				String FieldValueText = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/table")).getText();
				
				System.out.println("The Fields displayed are...\n" + FieldValueText);
				///html/body/div[2]/div/div[1]/table/tbody
				///html/body/div[2]/div/div[1]/table/tbody/tr[1]
				System.out.println("Step 6: Transaction Details Page Copied successfully");
				List<WebElement> allRows = statusTable.findElements(By.tagName("tr"));
				int rowSize = allRows.size();
				System.out.println("The Fields from the Page are being copied to the Excel File...");
				WriteExcelFile objExcelFile1 = new WriteExcelFile();
				//objExcelFile1.writeExcelRow(System.getProperty("user.dir")+"\\src\\testData","SDC-ST-Fields.xlsx","STFields",allRows);
				objExcelFile1.writeExcelRow("C:\\Clem\\TEMP","SDC-ST-Page-Fields.xlsx","STFields",allRows);
				System.out.println("Step 7: Fields on Page Written into Excel File successfully");   
				
				File myFile = new File("C:\\Clem\\TEMP\\SDC-ST-Page-Fields.xlsx");
				COMPAREEXCEL.convertXLXSFileToCSV(myFile, 0);
				System.out.println("Step 8: The copied page fields Excel file is converted to CSV file for comparision");
			}
			catch (Exception e) {
		          e.printStackTrace();
		      } 
			
	}
	public void fnCopySTPageFieldstoExcel(WebDriver driver) throws IOException
	{
			try
			{
				
				WebElement statusTable = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/table"));
				String FieldValueText = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/table")).getText();
				
				/*WebElement statusTable = driver.findElement(By.tagName("table"));
							
				List<String> columnNames = statusTable.findElements(By.tagName("thread"))   // get table headers
                        .stream()
                        .map(WebElement::getText)        // get the text
                        .map(String::trim)               // trim - no space
                        .collect(Collectors.toList());   // collect to a list*/
				
				System.out.println("The Fields displayed are...\n" + FieldValueText);
				System.out.println("Step 6: Transaction Details Page Copied successfully");
				List<WebElement> allRows = statusTable.findElements(By.tagName("tr"));
				int rowSize = allRows.size();
				System.out.println("The Fields from the Page are being copied to the Excel File...");
				WriteExcelFile objExcelFile1 = new WriteExcelFile();
				//objExcelFile1.writeExcelRow(System.getProperty("user.dir")+"\\src\\testData","SDC-ST-Fields.xlsx","STFields",allRows);
				objExcelFile1.writeExcelRow("C:\\Clem\\TEMP","SDC-ST-Page-Fields.xlsx","STFields",allRows);
				
				System.out.println("Step 7: Fields on Page Written into Excel File successfully");   
				
				/*File myFile = new File("C:\\Clem\\TEMP\\SDC-ST-Page-Fields.xlsx");
				COMPAREEXCEL.convertXLXSFileToCSV(myFile, 0);
				System.out.println("Step 8: The copied page fields Excel file is converted to CSV file for comparison");*/
			}
			catch (Exception e) {
		          e.printStackTrace();
		      } 
			
	}
	
	public void fnCopyUATPageFieldstoExcel(WebDriver driver) throws IOException
	{
			try
			{
				//WebDriver driver = new ChromeDriver();
				WebElement statusTable = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/table"));															  
				String FieldValueText = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/table")).getText();
				System.out.println("Step 6: Transaction Details Page Copied successfully");
				List<WebElement> allRows = statusTable.findElements(By.tagName("tr"));
				int rowSize = allRows.size();
				System.out.println("Copying the Fields from the Page to the Excel File...");
				WriteExcelFile objExcelFile1 = new WriteExcelFile();				
				objExcelFile1.writeExcelRow(System.getProperty("user.dir")+"\\src\\testData","SDC-UAT-Fields.xlsx","UATFields",allRows);
				System.out.println("Step 7: Fields on Page Written into Excel File successfully");    
			}
			catch (Exception e) {
		          e.printStackTrace();
		      } 
			
	}

	public void fnCompareExcelFiles() throws IOException
	{
			try
			{
				 //**** COMPARE TWO EXCEL Files(ST & UAT on page fields files)*************** WORKING
		        COMPAREEXCEL objExcelFile2 = new COMPAREEXCEL();
		        objExcelFile2.compareExcel(System.getProperty("user.dir")+"\\src\\testData","SDC-ST-Fields.xlsx","SDC-UAT-Fields.xlsx");
		        //objExcelFile2.compareExcel("C:\\Clem\\TEMP","SDC-ST-Fields.xlsx","SDC-UAT-Fields.xlsx");		        
		        System.out.println("Step 8: Excel comparision successfully completed");    
		        //**** COMPARE TWO EXCEL Files*************** WORKING
			}
			catch (Exception e) {
		          e.printStackTrace();
		      } 
			
	}
	
	/*public static void copyFileUsingApache(File from, File to) throws IOException
	{ 
		FileUtils.copyFile(from, to); 
	}*/
			
	public void fnDownloadandCountCSVFile(WebDriver driver) throws IOException
	{
			try
			{
				//*** Working Method-1
				/*driver.findElement(By.xpath("/html/body/div[2]/div/table/tbody/tr/td[2]/a")).click();
				System.out.println("Step 8: Transaction Page Fields exported to CSV successfully");	
				//COMPAREEXCEL.CopyMoveFile();
				COMPAREEXCEL.getRecordsCountInCSV("C:\\Clem\\TEMP", "SDC-ST-Extracted-Page-Fields.csv");	*/			
			//*** Working Method-1
				
				//WebElement link = driver.findElement(By.xpath("/html/body/div[2]/div/table/tbody/tr/td[2]/a"));
				//*** Right Click Working
				/*WebElement element = driver.findElement(By.cssSelector("body > div.container.body-content > div > table > tbody > tr > td:nth-child(2) > a"));
				Actions action = new Actions(driver);
				Thread.sleep(5000);
				action.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();*/
				//*** Right Click Working
				
				
				//action.moveToElement(element);
				
				//action.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
				
				//********* Right click and save as clicked working************
				WebElement element = driver.findElement(By.cssSelector("body > div.container.body-content > div > table > tbody > tr > td:nth-child(2) > a"));
				Actions action = new Actions(driver);
				action.contextClick(element).build().perform();
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER); 
				
				Thread.sleep(2000);
				
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_C);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable contents = clipboard.getContents(null);
				String x = (String) contents.getTransferData(DataFlavor.stringFlavor);
				System.out.println("Copied text file name= "+x);
				System.out.println("Copied text file name length= "+x.length());
				//x.substring(arg0)
				
				//********* Right click and save as clicked working************
				
				
				//****Enter File Name and save 
				StringSelection ss = new StringSelection("C:\\Clem\\TEMP\\SDC-ST-Extracted-Page-Fields.csv");
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
				
				//System.out.println("Path Copied");
				//native key strokes for CTRL, V and ENTER keys
				//Robot robot1 = new Robot();

				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
								
				//System.out.println("Path pasted");
							
				System.out.println("Step 8: Transaction Page Fields exported to CSV successfully");	
				//COMPAREEXCEL.CopyMoveFile();
				Thread.sleep(2000);				
				COMPAREEXCEL.getRecordsCountInCSV("C:\\Clem\\TEMP", "SDC-ST-Extracted-Page-Fields.csv");
				Thread.sleep(5000);
				//COMPAREEXCEL.ConvertCSVToEXCEL("C:\\Clem\\TEMP", "SDC-ST-Extracted-Page-Fields.csv");
			  
				
				
				//Action rightclick = action.contextClick(element).build();
				//rightclick.perform();
				//WebElement elementOpen = driver.findElement(By.linkText("Save link as..."));
				//elementOpen.click();
				 //action.sendKeys(Keys.ARROW_DOWN).build().perform();
				 //action.sendKeys(Keys.ARROW_DOWN).build().perform();
				 //action.sendKeys(Keys.ARROW_DOWN).build().perform();
				 //action.sendKeys(Keys.ARROW_DOWN).build().perform();
				 //action.sendKeys(Keys.ENTER).build().perform();
				 
				 //action.contextClick(link).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
				//*** Right Click Working
			}
			catch (Exception e) {
		          e.printStackTrace();
		      }			
	}

	//public void fnCopySTPageXMLtoExcel(WebDriver driver) throws IOException, ParserConfigurationException, SAXException, TransformerException, InterruptedException
	public void fnCopySTPageXMLtoExcel(WebDriver driver) throws IOException
	{
			int XMLType=0;
			try
			{				
				//WebElement XMLTab = driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[2]/a"));
				//XMLTab.click();
				
				
				//String XMLFieldValueText = driver.findElement(By.xpath("//*[@id=\"tabs-2\"]/div[3]/pre/code/span[1]")).getText();				
				//String XMLFieldValueText = driver.findElement(By.xpath("//*[@id=\"tabs-2\"]")).getText();
				//String XMLFieldValueText = driver.findElement(By.xpath("//*[@id=\"tabs-2\"]/div[3]/pre")).getText();
				//if (driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/a")).isDisplayed())
				//if (!driver.findElements(By.xpath("/html/body/div[2]/div/ul/li[1]/a")).isEmpty())
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				try{
				if (driver.findElements(By.xpath("/html/body/div[2]/div/ul/li[1]/a")).size()!=0)
				{
					XMLType=1;
					WebElement XMLTab = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/a"));
					XMLTab.click();
					Thread.sleep(100);
					System.out.println("I am in Endorsement tab 1");
					String XMLEndorsementFieldValueText = driver.findElement(By.xpath("//*[@id=\"tabs-1\"]/div[3]")).getText();
					
					ConvertStringToXML objXML = new ConvertStringToXML();				
					objXML.convertStringToXML(XMLEndorsementFieldValueText,XMLType);					
				}
				} catch(NoSuchElementException e) {
					System.out.println("Impossible to click the tab. Reason: " + e.toString());
				}
				
				//if (driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[2]/a")).isDisplayed())
				//if (!driver.findElements(By.xpath("/html/body/div[2]/div/ul/li[2]/a")).isEmpty())
				try{
				if (driver.findElements(By.xpath("/html/body/div[2]/div/ul/li[2]/a")).size()!=0)
				{
					
					XMLType=2;
					WebElement XMLTab = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[2]/a"));
					XMLTab.click();					
					Thread.sleep(100);
					System.out.println("I am in MRC tab 2");
					String XMLMRCFieldValueSection1 = driver.findElement(By.xpath("//*[@id=\"tabs-2\"]/div[3]")).getText();
					ConvertStringToXML objXML = new ConvertStringToXML();				
					objXML.convertStringToXML(XMLMRCFieldValueSection1,XMLType);					
				}
				} catch(NoSuchElementException e) {
					System.out.println("Impossible to click the tab. Reason: " + e.toString());
				}
				
				//if (driver.findElement(By.xpath("/html/body/div[4]/div/ul/li[3]/a")).isDisplayed())
				//if (!driver.findElements(By.xpath("/html/body/div[4]/div/ul/li[3]/a")).isEmpty())
				try{
				if (driver.findElements(By.xpath("/html/body/div[2]/div/ul/li[3]/a")).size()!=0) 
				{
					XMLType=3;
					WebElement XMLTab = driver.findElement(By.xpath("/html/body/div[2]/div/ul/li[3]/a"));
					XMLTab.click();					
					Thread.sleep(1000);
					System.out.println("I am in MRC tab 3");
					String XMLMRCFieldValueSection2 = driver.findElement(By.xpath("//*[@id=\"tabs-3\"]/div[3]")).getText();
					ConvertStringToXML objXML = new ConvertStringToXML();				
					objXML.convertStringToXML(XMLMRCFieldValueSection2,XMLType);					
				}
				} catch(NoSuchElementException e) {
				    System.out.println("Impossible to click the tab. Reason: " + e.toString());
				}
				
				//No tab XML web page. Not going through if the previous condition fails. Need Fixing.
				if (XMLType==0)
				{
					System.out.println("I am Not in any Tabs");
					String XMLMRCFieldValueDirect = driver.findElement(By.xpath("/html/body/div[2]/div/pre")).getText();
					ConvertStringToXML objXML = new ConvertStringToXML();				
					objXML.convertStringToXML(XMLMRCFieldValueDirect,XMLType);			
				}		
				
				/*String XMLFieldValueText = driver.findElement(By.xpath("//*[@id=\"tabs-2\"]/div[3]")).getText();
			    ConvertStringToXML objXML = new ConvertStringToXML();				
				objXML.convertStringToXML(XMLFieldValueText); */
				
				System.out.println("\nStep 6: MRC Transaction XML Details Copied successfully");				
	}
			catch (Exception e) {
		          //e.printStackTrace();
		      } 
			
	}

}
