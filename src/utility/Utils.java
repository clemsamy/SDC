package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.apache.commons.io.FileUtils;
//import org.apache.poi.sl.usermodel.Sheet;
//import org.apache.poi.sl.usermodel.ObjectMetaData.Application;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

import com.google.common.io.Files;
//import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import au.com.bytecode.opencsv.CSVReader;

public class Utils 
{
		public static WebDriver driver = null;
		//private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		public static int NoOfRecords;
		
		//public static WebDriver OpenBrowser(int iTestCaseRow,String URL) throws Exception
		public static WebDriver OpenBrowser(String Browser,String URL) throws Exception
		{			
			//String sBrowserName;
			try{
					//sBrowserName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Browser);
					//System.out.println("TC name from excel= "+sBrowserName);
					//System.out.println("TC name after convert= "+sBrowserName.toUpperCase());
				
					String downloadFilepath = Config.DIR_Child;
					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", downloadFilepath);
					chromePrefs.put("safebrowsing.enabled", "true");
					//ChromeOptions options = new ChromeOptions();
					//options.setExperimentalOption("prefs", chromePrefs);
		
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.setExperimentalOption("prefs", chromePrefs);
			        chromeOptions.addArguments("disable-infobars");
			        chromeOptions.addArguments("--disable-popup-blocking");
			        chromeOptions.addArguments("--no-sandbox");  
			        //chromeOptions.AddUserProfilePreference("safebrowsing.enabled", true);
			        
			        //chromeOptions
			        //chromeOptions.addArguments("--headless");
			        //chromeOptions.addArguments("headless");
			        //chromeOptions.setHeadless(true);
			        //HashMap<String, Object> prefs = new HashMap<String, Object>();
			        //prefs.put("profile.default_content_setting_values.cookies", 2);
			        //chromeOptions.setExperimentalOption("prefs", prefs);
			        //chromeOptions.setExperimentalOption("excludeSwitches", "disable-popup-blocking");			            
			        //chromeOptions.setUnhandledPromptBehaviour(null);
					//switch(sBrowserName.toUpperCase())
					switch(Browser.toUpperCase()) 
					{						
						case "CHROME":
							System.setProperty("webdriver.chrome.driver", Config.DriverPath+"chromedriver.exe");
							driver = new ChromeDriver(chromeOptions);
							Log.info("New Chrome driver instantiated");
							break;
						case "FF":
							System.setProperty("webdriver.gecko.driver", Config.DriverPath+"geckodriver.exe");
							driver = new FirefoxDriver();
							Log.info("New FF driver instantiated");
							break;
						case "IE":
							System.setProperty("webdriver.ie.driver", Config.DriverPath+"IEDriverServer.exe");
							driver = new InternetExplorerDriver();
							Log.info("New IE driver instantiated");
							break;
						default:
							//System.out.println("Browser : " + sBrowserName + " is invalid, Launching Chrome as browser of choice..");
							System.out.println("Browser : " + Browser + " is invalid, Launching Chrome as browser of choice..");
							System.setProperty("webdriver.chrome.driver", Config.DriverPath+"chromedriver.exe");
							driver = new ChromeDriver(chromeOptions);
							Log.info("New Chrome driver instantiated");
							break;
					}
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				    Log.info("Implicit wait applied on the driver for 40 seconds");
				    //driver.get(Constant.STURL);
				    driver.get(URL);
				    Log.info("Web Browser launched successfully");
				    Reporter.log("Web Browser launched successfully");
				    Thread.sleep(2000);
					
				}catch (Exception e)
				{
					Log.error("Web Browser NOT launched - Failed");
					Reporter.log("Web Browser NOT launched - Failed");
					Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
				}
				return driver;
		}
	
	 public static String getTestCaseName(String sTestCase)throws Exception{
		String value = sTestCase;
		try{
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");	
			value = value.substring(posi + 1);
			return value;
				}catch (Exception e){
			Log.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
			throw (e);
					}
			}
	
	 public static void mouseHoverAction(WebElement mainElement, String subElement){
		
		 Actions action = new Actions(driver);
         action.moveToElement(mainElement).perform();
         if(subElement.equals("Accessories")){
        	 action.moveToElement(driver.findElement(By.linkText("Accessories")));
        	 Log.info("Accessories link is found under Product Category");
         }
         if(subElement.equals("iMacs")){
        	 action.moveToElement(driver.findElement(By.linkText("iMacs")));
        	 Log.info("iMacs link is found under Product Category");
         }
         if(subElement.equals("iPads")){
        	 action.moveToElement(driver.findElement(By.linkText("iPads")));
        	 Log.info("iPads link is found under Product Category");
         }
         if(subElement.equals("iPhones")){
        	 action.moveToElement(driver.findElement(By.linkText("iPhones")));
        	 Log.info("iPhones link is found under Product Category");
         }
         action.click();
         action.perform();
         Log.info("Click action is performed on the selected Product Type");
	 }
	 public static void waitForElement(WebElement element){
		 
		 WebDriverWait wait = new WebDriverWait(driver, 10);
	     wait.until(ExpectedConditions.elementToBeClickable(element));
	 	}
		
	 public static void takeScreenshot(WebDriver driver, String sTestCaseName) throws Exception
	 {
			try{
				// Create object of SimpleDateFormat class and decide the format
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
				Date date = new Date();
				String date1= dateFormat.format(date);
				//System.out.println("Current date and time is " +date1);
				 
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(Config.Path_ScreenShot + sTestCaseName + "_" + date1 + ".jpg"));
				Log.info("Screenshot taken");
			   } 
			   catch (Exception e)
			   {
				Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "+e.getMessage());
				throw new Exception();
			   }
	}
	 public static void writeFileCompareOutput(String FilePath,String FileName,String txtLine) throws Exception	 
	 {	 
		  //Create File In D: Driver.  
		  String TestFile = FilePath + FileName; 
		  File FC = new File(TestFile);//Created object of java File class.
		  FC.createNewFile();//Create file.
		  
		  //Writing In to file.
		  //Create Object of java FileWriter and BufferedWriter class.
		  FileWriter FW = new FileWriter(TestFile);
		  BufferedWriter BW = new BufferedWriter(FW);
		  BW.write(txtLine); //Writing In To File.
		  BW.newLine();//To write next string on new line.
		  //BW.write("This Is Second Line."); //Writing In To File.
		  BW.close();
		  
		  /*//Reading from file.
		  //Create Object of java FileReader and BufferedReader class.
		  FileReader FR = new FileReader(TestFile);
		  BufferedReader BR = new BufferedReader(FR);
		  String Content = "";
		   //Loop to read all lines one by one from file and print It.
		  while((Content = BR.readLine())!= null)
		  {
		    System.out.println(Content);
		  }*/
	}
	 public static int getRecordsCountInCSV(String downloadPath, String csvFileName) 
	 {
			int lineNumberCount = 0;
			try {
				if (!csvFileName.isEmpty() || csvFileName != null) {
					//String filePath =	downloadPath + System.getProperty("file.separator") + csvFileName;
					String filePath =	downloadPath + csvFileName;
					//System.out.println(filePath);
					File file = new File(filePath);
					if (file.exists()) {
						//System.out.println("File found :" +csvFileName);
						Log.info("File found :" +csvFileName);
						//Reporter.log("File found :" +csvFileName);
						FileReader fr = new FileReader(file);
						LineNumberReader linenumberreader = new LineNumberReader(fr);
						while (linenumberreader.readLine() != null) {
							lineNumberCount++;
						}
						//To remove the header
						lineNumberCount=lineNumberCount-1;
						//System.out.println("Total number of fields in downloaded csv file : " + (lineNumberCount));
						Log.info("Total number of fields in downloaded csv file : " + (lineNumberCount));
						//Reporter.log("Total number of fields in downloaded csv file : " + (lineNumberCount));
						NoOfRecords = lineNumberCount;
						//System.out.println("Total number of fields in downloaded csv file public variable : " + (NoOfRecords));					
						linenumberreader.close();
					} else {
						//System.out.println("File does not exists");
						Log.info("File does not exists");
						//Reporter.log("File does not exists");
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			return lineNumberCount;
	 }	
	 public static void ConvertCSVToEXCEL_OLD(String downloadPath, String csvFileName) 
	 {
				int lineNumberCount = 0;
				CSVReader reader = null;
			    FileOutputStream fileOutputStream = null;
			    
			    //String input_file = "C:\\Clem\\TEMP\\SDC-ST-Extracted-Page-Fileds.csv";
			    //String input_file = downloadPath + "\\" + csvFileName;
			    //System.out.println("FileName Passed = "+input_file); 
			    
			    XSSFWorkbook workBook = new XSSFWorkbook();
			    XSSFSheet sheet = workBook.createSheet("sheet1");		    
			       
			    int RowNum=0;
			    int newrow;
			    //int LoopCount = 0;
			      
			    System.out.println("Converting the CSV file to .xlsx file..."); 
			  	
			    try
			    {
			    if (!csvFileName.isEmpty() || csvFileName != null) 
			    {
					String filePath =	downloadPath + System.getProperty("file.separator") + csvFileName;
					//String filePath =	downloadPath + csvFileName;
					System.out.println(filePath);
					File file = new File(filePath);
				  
					if (file.exists()) 
					{
						 //xls file name
					    String xlsxFileAddress = null;
					    if(file.length() < 5){
					      System.out.println("Incorrect file name!");
					      System.exit(0);
					    } else {
					      xlsxFileAddress = csvFileName+".xlsx";
					    }
					  
						String[] nextLine;
						reader = new CSVReader(new FileReader(file),',');					
						System.out.println("File found :" +csvFileName);
						FileReader fr = new FileReader(file);
						LineNumberReader linenumberreader = new LineNumberReader(fr);
						while (linenumberreader.readLine() != null) 
						{
							System.out.println("Inside Loop:");
							lineNumberCount++;
						 	nextLine = reader.readNext();
						 	XSSFRow currentRow=sheet.createRow(RowNum++);
						    for(int i=0;i<nextLine.length;i++)
						    {
						    	System.out.println("Inside Loop 2:");
						    	currentRow.createCell(i).setCellValue(nextLine[i]);
						        newrow = i; 
						    }
						     
						}
						 fileOutputStream = new FileOutputStream(xlsxFileAddress);
					      workBook.write(fileOutputStream);
						//To remove the header
						lineNumberCount=lineNumberCount-1;
						System.out.println("Total number of fields in downloaded csv file : " + (lineNumberCount));
						NoOfRecords = lineNumberCount;
						System.out.println("Total number of fields in downloaded csv file public variable : " + (NoOfRecords));					
						linenumberreader.close();
					} else {
						System.out.println("File does not exists");
					}
				}
			    }
			    catch (Exception e1) {
				      //e.printStackTrace();
				      System.out.println("\nFile \"" + csvFileName + "\" does not exist!");
				    }
				    finally 
				    {
				      if (reader != null) 
				      {
				        try 
				        {
				          reader.close();
				          fileOutputStream.close();
				          System.out.println("Done");
				          System.out.println("Inside Loop 3:");
				        } catch (IOException e2) 
				        {
				          //e2.printStackTrace();
				          System.out.println("\nFile \"" + csvFileName + "\" can not be closed correctly!");
				        }
				      }
				    }
				    
				   
				    try {
				      System.out.print("\n...press [Enter] key to exit.");
				      System.in.read();
				      System.out.println("Inside Loop 4:");
				    } catch (IOException e3) {
				      // TODO Auto-generated catch block
				      //e3.printStackTrace();
				      System.out.println("Application is not close correctly!");
				    }    
			    	
	  }
	 
	 public static void ConvertCSVToEXCEL(String sourceFileName, String targetFileName) 
	 {
		  BufferedReader br=null;
		  SXSSFWorkbook wb = null;
		  FileOutputStream fileOutputStream  =null;
		  OutputStreamWriter osw = null;
		  PrintWriter printWriter = null;
		  Reader reader = null;
		  try 
		  {
		   String csvFileAddress = sourceFileName;
		   String xlsxFileAddress = targetFileName;
		   wb = new SXSSFWorkbook(100); 
		   wb.setCompressTempFiles(true); // temp files will be gzipped
		   Sheet sheet = wb.createSheet("sheet1");
		   String currentLine=null,fullstring=null;
		   int RowNum=0,j;
		    
		   reader = new InputStreamReader(new FileInputStream(csvFileAddress), "utf-8");
		   br = new BufferedReader(reader);
		 
		   while ((currentLine = br.readLine()) != null) 
		   {
			   //String str[] = currentLine.split("\",\"");
			   String str[] = currentLine.split(",");
			   //fullstring=null;
			   Row currentRow=sheet.createRow(RowNum);
			   for(int i=0;i<str.length;i++)
			   {
			     //System.out.println("col= "+str[i]);
				 if (str[i].startsWith("=")) 
			     {
			    	 				//Cell cell1 = currentRow.createCell(i);
			    	 				//cell1.setCellType(cell1.getCellType().STRING);
			    	 				//cell1.setCellType(currentRow.createCell(i).getCellType().STRING);			                        
			    	 				currentRow.createCell(i).setCellType(currentRow.createCell(i).getCellType().FORMULA);
			    	 				str[i] = str[i].replaceAll("\"", "");
			                        str[i] = str[i].replaceAll("=", "");
			                        currentRow.createCell(i).setCellValue(str[i]);
			                    
			     } 
			     else if (str[i].startsWith("\"")) 
			     {
			                        str[i] = str[i].replaceAll("\"", "");
			                        currentRow.createCell(i).setCellType(currentRow.createCell(i).getCellType().STRING);
			                        //setCellType(currentRow.createCell(i).);
			                        fullstring="";
			                        for(j=4;j<str.length;j++)
			                        	fullstring += str[j]; 	
			                        fullstring = fullstring.replaceAll("\"", "");
			                        currentRow.createCell(i).setCellValue(fullstring);
			                        break;
			     } 
			     else 
			     {
			                        str[i] = str[i].replaceAll("\"", "");
			                        currentRow.createCell(i).setCellType(currentRow.createCell(i).getCellType().STRING);     
			                        //setCellType(currentRow.createCell(i).CELL_TYPE_NUMERIC);
			                        currentRow.createCell(i).setCellValue(str[i]);
			      }     
			   }
			   RowNum++;
		   }
		   fileOutputStream  =  new FileOutputStream(xlsxFileAddress);
		   wb.write(fileOutputStream);
		   osw = new OutputStreamWriter(fileOutputStream , "UTF-8");
		   printWriter = new PrintWriter(osw);		 
		   System.out.println("Done");
		   Log.info("CSV File is converted into XLSX file.");
		  } 
		  catch (Exception ex) 
		  {
			  System.out.println(ex.getMessage()+"Exception in try");
		  }
		  finally
		  { 
			   try 
			   {
				    if(br!=null)
				    	br.close();
				    if(reader!=null)
				    	reader.close();
				    if(fileOutputStream!=null)
				    	fileOutputStream.close(); 
				    if(osw!=null)
				    	osw.close();
				        if(printWriter!=null)
				        	printWriter.close();
			   } 
			   catch (IOException e) 
			   {
				   System.out.println("Error trying to close resources.."+e.getMessage());
			   }
		  }
	}                    
	
	 public static void CSVtoXLSX() throws IOException
	 {
         /* Step -1 : Read input CSV file in Java */
         String inputCSVFile = "C:\\TEMP\\OutputFiles\\MRCFields\\Converted-ST-Page-Fields-CSV.csv";
         CSVReader reader = new CSVReader(new FileReader(inputCSVFile));
         /* Variables to loop through the CSV File */
         String [] nextLine; /* for every line in the file */            
         int lnNum = 0; /* line number */
         /* Step -2 : Define POI Spreadsheet objects */          
         XSSFWorkbook new_workbook = new XSSFWorkbook(); //create a blank workbook object
         XSSFSheet sheet = new_workbook.createSheet("CSV2XLSX");  //create a worksheet with caption score_details
         /* Step -3: Define logical Map to consume CSV file data into excel */
         Map<String, Object[]> excel_data = new HashMap<String, Object[]>(); //create a map and define data
         /* Step -4: Populate data into logical Map */
         while ((nextLine = reader.readNext()) != null) 
         {
                 lnNum++;                        
                 excel_data.put(Integer.toString(lnNum), new Object[] {nextLine[0],nextLine[1],nextLine[2],nextLine[3],nextLine[4]});                        
         }
         /* Step -5: Create Excel Data from the map using POI */
         Set<String> keyset = excel_data.keySet();
         int rownum = 0;
         for (String key : keyset) { //loop through the data and add them to the cell
                 Row row = sheet.createRow(rownum++);
                 Object [] objArr = excel_data.get(key);
                 int cellnum = 0;
                 for (Object obj : objArr) {
                         Cell cell = row.createCell(cellnum++);
                         if(obj instanceof Double)
                                 cell.setCellValue((Double)obj);
                         else
                                 cell.setCellValue((String)obj);
                         }
         }
         /* Write XLS converted CSV file to the output file */
         FileOutputStream output_file = new FileOutputStream(new File("C:\\TEMP\\OutputFiles\\MRCFields\\Converted-ST-Page-Fields.xlsx")); //create XLS file
         new_workbook.write(output_file);//write converted XLS file to output stream
         output_file.close(); //close the file
	 }
		                        
		                        
	 public static void convertXLSXFileToCSV_old(File xlsxFile,String NewCSVFile) throws Exception   //** Need to fix the issue, in the extracted value when . is there in the amount, it doesn't display correct
	  {
	  	  
			  FileInputStream fileInStream = new FileInputStream(xlsxFile);
		      int written=0;
		      int isExists,wholenumber=2;
		      int intFieldNumber = 1;
		      //int count =0;
		      String str998="";
		      String actual2[]={""};
		      String strheading[]={""};
		      String strFieldName = null;
		      //String strheadingtemp=null;

		      // Open the xlsx and get the requested sheet from the workbook
		      XSSFWorkbook workBook = new XSSFWorkbook(fileInStream);
		      XSSFSheet selSheet = workBook.getSheetAt(0);

		      // Iterate through all the rows in the selected sheet
		      Iterator<Row> rowIterator = selSheet.iterator();
		      //BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("C:\\Clem\\TEMP\\SDC-ST-Page-Fields-CSV.csv")));
		      BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(NewCSVFile)));
		      //BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(Constant.Path_OutputFile+"Converted-"+Constant.File_CSVExportSTFields)));
		      //StringBuffer sb = new StringBuffer();
		      bwr.write("Field Number,Field Name,Sub-Field Number,Sub-Field Name,Extracted Value");
		      bwr.write("\n");
		      while (rowIterator.hasNext()) 
		      {
		          Row row = rowIterator.next();
		          
		          // Iterate through all the columns in the row and build ","
		          // separated string
		          Iterator<Cell> cellIterator = row.cellIterator();
		          StringBuffer sb = new StringBuffer();
		     	  while (cellIterator.hasNext()) 
		          {
		          	  Cell cell = cellIterator.next();	   
		        	  //count =0;      	  
		              //System.out.println("Cellval= "+cell.getStringCellValue());
		              if (sb.length() != 0) 
		              {
		            	  //strFieldName = cell.getStringCellValue(); 
		            	  sb.append(",");
		              }
		            switch (cell.getCellType()) {
		              case STRING:
		                  sb.append(cell.getStringCellValue());
		                  break;
		              case NUMERIC:
		                  sb.append(cell.getNumericCellValue());
		                  break;
		              case BOOLEAN:
		                  sb.append(cell.getBooleanCellValue());
		                  break;
		              case BLANK:
		                  sb.append(",");
		                  break;                      
		              default:
		            	  //sb.append(cell.getBooleanCellValue() + ",");
		                  //break;             
		              }
		          }
		     	  //if (count == 1)
		     		 //sb.append(",");
		          //System.out.println(sb.toString());
		          String temp = sb.toString();	          
		          //*****To remove the blank space record or the heading record from the copied file
		          str998="";
		          isExists = temp.indexOf(",");
		          //System.out.println("isExists= " + isExists);
		          //System.out.println("Temp= " + temp);
		          
		          if (isExists != -1)
		          {
		        	  String actual=temp.substring(0, temp.indexOf(",")); 
		        	  strheading = temp.split(",");
		           	  //System.out.println("Actual= " +actual);
		         	  if (actual.equals("64.1")||actual.equals("64.2")||actual.equals("64.3")||actual.equals("64.4")||actual.equals("64.5")||actual.equals("64.6"))
		           	  {  
		           		  written++;               		  
		           	  }
		           	  actual2 = actual.split("\\.");
		        	  //System.out.println("Actual Length= "+ actual2.length);
		        	  wholenumber = actual2.length;
		        	  if (actual2.length > 1)
		        	  {
		        		  str998 = actual2[1];
		        		  intFieldNumber = Integer.valueOf(actual2[0]);
		        		  //System.out.println("FullString= " + actual+" Substring1= "+actual2[0]+" Substring2= "+actual2[1]);
		        	  }
		        	  //System.out.println("Wholenumber or not= "+strheading[0].indexOf("."));
		        	  if (strheading[0].indexOf(".")== -1)  //*** checking it is a decimal number or not
		        	     strFieldName = strheading[1];
			              
		          }
		          else if(isExists == -1 )
		             strFieldName = temp;	        	  
		          	                       
		          if (isExists != -1 && !str998.equals("998") && wholenumber > 1 && (written == 0 || written <= 6))
		          {
		        	  bwr.write(intFieldNumber+","+strFieldName+",");
		        	  bwr.write(temp);
		        	  bwr.write("\n");
		          }                   
		          if (written == 12)
		       		  written =0;               			  
		    	 
		        //*****To remove the blank space record or the heading record from the copied file
		        
		     }
		    //bwr.write("Sub-Field Number,Sub-Field Name,Extracted Value");
		      workBook.close();
			  //flush the stream
		      bwr.flush();
			  //close the stream
			  bwr.close();   
	  }
	 public static void convertXLSXFileToCSV(File xlsxFile,String NewCSVFile) throws Exception   //** FIXED-> in the extracted value when . is there in the amount, it doesn't display correct
	 {	  	  
			  FileInputStream fileInStream = new FileInputStream(xlsxFile);
		      int written=0;
		      int isExists,wholenumber=2;
		      int intFieldNumber = 1;
		      //int count =0;
		      String str998="";
		      String actual2[]={""};
		      String strheading[]={""};
		      String strFieldName = null;
		      //String strheadingtemp=null;

		      // Open the xlsx and get the requested sheet from the workbook
		      XSSFWorkbook workBook = new XSSFWorkbook(fileInStream);
		      XSSFSheet selSheet = workBook.getSheetAt(0);

		      // Iterate through all the rows in the selected sheet
		      Iterator<Row> rowIterator = selSheet.iterator();
		      //BufferedWriter bwr = new BufferedWriter(new FileWriter(new File("C:\\Clem\\TEMP\\SDC-ST-Page-Fields-CSV.csv")));
		      BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(NewCSVFile)));
		      //BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(Constant.Path_OutputFile+"Converted-"+Constant.File_CSVExportSTFields)));
		      //StringBuffer sb = new StringBuffer();
		      bwr.write("Field Number,Field Name,Sub-Field Number,Sub-Field Name,Extracted Value");
		      bwr.write("\n");
		      while (rowIterator.hasNext()) 
		      {
		          Row row = rowIterator.next();
		          
		          // Iterate through all the columns in the row and build "," separated string
		          Iterator<Cell> cellIterator = row.cellIterator();
		          StringBuffer sb = new StringBuffer();
		     	  while (cellIterator.hasNext()) 
		          {
		          	  Cell cell = cellIterator.next();	   
		        	  //count =0;      	  
		              //System.out.println("Cellval= "+cell.getStringCellValue());
		              
		              if (sb.length() != 0) 
		              {
		            	  //strFieldName = cell.getStringCellValue(); 
		            	  sb.append(",");
		              }
		              switch (cell.getCellType()) {
		              case STRING:
		            	  if (cell.getStringCellValue().contains(","))
			            	  sb.append("\""+cell.getStringCellValue()+"\"");
		            	  else
		            		  sb.append(cell.getStringCellValue());
		                  break;
		              case NUMERIC:
		            	  if (cell.getStringCellValue().contains(","))
			            	  sb.append("\""+cell.getNumericCellValue()+"\"");		            	  
		            	  else
		                      sb.append(cell.getNumericCellValue());
		                  break;
		              case BOOLEAN:
		            	  if (cell.getStringCellValue().contains(","))
		            		  sb.append("\""+cell.getBooleanCellValue()+"\"");
		            	  else
		            		  sb.append(cell.getBooleanCellValue());
		                  break;
		              case BLANK:
		                  sb.append(",");
		                  break;                      
		              default:
		            	  //sb.append(cell.getBooleanCellValue() + ",");
		                  //break;             
		              }
		          }
		     	  //if (count == 1)
		     		 //sb.append(",");
		          //System.out.println(sb.toString());
		          String temp = sb.toString();	   
		          
		          //*****To remove the blank space record or the heading record from the copied file
		          str998="";
		          isExists = temp.indexOf(",");
		          //System.out.println("isExists= " + isExists);
		          //System.out.println("Temp= " + temp);
		          
		          if (isExists != -1)
		          {
		        	  String actual=temp.substring(0, temp.indexOf(",")); 
		        	  strheading = temp.split(",");
		           	  //System.out.println("Actual= " +actual);
		        	  //****64.x fields have multi-section values in MS. Need to add if the field number increases..
		         	  if (actual.equals("64.1")||actual.equals("64.2")||actual.equals("64.3")||actual.equals("64.4")||actual.equals("64.5")||actual.equals("64.6")||actual.equals("64.7")||actual.equals("64.8")||actual.equals("64.9"))
		           	  {  
		           		  written++;               		  
		           	  }
		           	  actual2 = actual.split("\\.");
		        	  //System.out.println("Actual Length= "+ actual2.length);
		        	  wholenumber = actual2.length;
		        	  if (actual2.length > 1)
		        	  {
		        		  str998 = actual2[1];
		        		  intFieldNumber = Integer.valueOf(actual2[0]);
		        		  //System.out.println("FullString= " + actual+" Substring1= "+actual2[0]+" Substring2= "+actual2[1]);
		        	  }
		        	  //System.out.println("Wholenumber or not= "+strheading[0].indexOf("."));
		        	  if (strheading[0].indexOf(".")== -1)  //*** checking it is a decimal number or not
		        	     strFieldName = strheading[1];
			              
		          }
		          else if(isExists == -1 )
		             strFieldName = temp;	        	  
		          	                       
		          if (isExists != -1 && !str998.equals("998") && wholenumber > 1 && (written == 0 || written <= 9))   //****Changed written <= 6 to written <= 9
		          {
		        	  bwr.write(intFieldNumber+","+strFieldName+",");
		        	  bwr.write(temp);
		        	  bwr.write("\n");
		        	  written =0;
		          }                   
		          //if (written == 12)
		       		//  written =0;             			  
		    	 
		        //*****To remove the blank space record or the heading record from the copied file
		          //bwr.write(temp);
	        	  //bwr.write("\n");
		     }
		    //bwr.write("Sub-Field Number,Sub-Field Name,Extracted Value");
		      workBook.close();
			  //flush the stream
		      bwr.flush();
			  //close the stream
			  bwr.close();   
	  }	 
	 //public static void CompareCSVFiles(String filename1,String filename2) throws Exception
	 public static boolean CompareCSVFiles_WORKING_ROWBYROW_COMPARE(String filename1,String filename2) throws Exception 
	 { 
			  //List countries = new ArrayList<>(); 
			  int NumberOfDifferences=0,i,j;
			  int mismatch =0,file1count=0,file2count=0,Highestloopcount=0;		
			  Variables.OutputBuffer="";
			  
			  BufferedReader file1tmp = new BufferedReader(new FileReader(filename1));
			  BufferedReader file2tmp = new BufferedReader(new FileReader(filename2));
			 
			  while(file1tmp.readLine()!= null)
			      file1count++;
			  while(file2tmp.readLine()!= null)
			      file2count++;		
			  
			  //System.out.println("Number of records in file1: "+file1count);
			  //System.out.println("Number of records in file2: "+file2count);
			  
			  Variables.OutputBuffer += "\nNumber of records in file: "+ filename1+" = " + file1count;
			  Variables.OutputBuffer += "\nNumber of records in file: "+ filename2+" = " + file2count+"\n";
			  
			  BufferedReader file1 = new BufferedReader(new FileReader(filename1));
			  BufferedReader file2 = new BufferedReader(new FileReader(filename2));
			
			  if (file1count >= file2count)
				  Highestloopcount = file1count;
			  else
				  Highestloopcount = file2count;
			  
			  String file1_line = file1.readLine(); // Reading header, Ignoring in file 1
			  String file2_line = file2.readLine(); // Reading header, Ignoring in file 2
			  
			  //while ((file1_line = file1.readLine()) != null && !file1_line.isEmpty() && (file2_line = file2.readLine())!= null) 
			  for (j=0;j<Highestloopcount;j++)
			  { 
				  mismatch=0;
				  if ((file1_line = file1.readLine())==null)
					  mismatch=1;
				  if ((file2_line = file2.readLine())==null)
					  mismatch=1;
				  
				  if (mismatch==0)
				  {
					  if (!file1_line.equals(file2_line))
					  {
						  mismatch = 0;
						  String[] file1_fields = file1_line.split(",");
						  String[] file2_fields = file2_line.split(",");
						  
						  if (file1_line.charAt(file1_line.length() - 1) == ',')  //*** ignoring the last char (,) in downloaded file
							  mismatch = 0;
						  //System.out.println("\nFile 1 columns found: "+file1_fields.length);
						  //System.out.println("File 2 columns found: "+file2_fields.length);
						  //System.out.println("\nCurrent Row found: "+"\n"+file1_line+'\n'+file2_line);
						  if (file1_fields.length != file2_fields.length)
							  mismatch=1;
						  else
						  {
							  for (i=0;i<file1_fields.length;i++)
							  {
								  //System.out.println("file1_fields= "+file1_fields[i]);							  
								  if (!file1_fields[i].equals(file2_fields[i]))
									  if (file1_line.indexOf("\"") != -1)  //*** ignoring the last char (") in downloaded file
										  mismatch = 0;
								  else		
									  mismatch = 1;	  
							  }
						  }
					  }
				  }  
					  if (mismatch == 1 && (file1_line!=null || file2_line!=null))
					  {
						  //System.out.println("\nDifferences found: "+"\n"+file1_line+'\n'+file2_line);
						  Variables.OutputBuffer += "\nDifferences found: "+"\n"+file1_line+'\n'+file2_line+"\n";
						  NumberOfDifferences++;					  
					  }			  
				  
			  } 
			  file1.close(); 
			  file2.close(); 
			  
			  if (NumberOfDifferences == 0)
			  {
		    	  //System.out.println("No Differences found in two files");
			  	  Variables.OutputBuffer += "No Differences found in two files";
			  	  return true;
			  }
			  else
			  {
				  //System.out.println("\nNumber of Differences found in two files: "+ (NumberOfDifferences));
				  Variables.OutputBuffer += "\nNumber of Differences found in two files = "+ (NumberOfDifferences);
			  	  return false;
			  }
	
			  //Reporter.log("Step 6: ");
			  //Log.info("Step 6: ");
	  }
	 @SuppressWarnings("resource")
	 public static boolean CompareCSVFiles_WORKING_Ver01_NEW(String filename1,String filename2) throws Exception 
	 { 
			  //List countries = new ArrayList<>(); 
			  int NumberOfDifferences=0,i,j; //AnotherFileCount=0,i,j,k;
			  int mismatch =0,file1count=0,file2count=0,RowNum=1;//Highestloopcount=0;		
			  //Variables.OutputBuffer="";
			  BufferedReader file1,file2,file1tmp,file2tmp;
			  String SecondFile,file1_line,file2_line;
			  //Variables.notequalcolorlist=null;
			  //Variables.duplicatecolorlist=null;
			  
			  file1tmp = new BufferedReader(new FileReader(filename1));
			  file2tmp = new BufferedReader(new FileReader(filename2));
			 
			  while(file1tmp.readLine()!= null)
			      file1count++;
			  while(file2tmp.readLine()!= null)
			      file2count++;		
			  
			  if (file1count >= file2count)
			  {
				  //Highestloopcount = file1count;
			      //AnotherFileCount = file2count;
			      file1 = new BufferedReader(new FileReader(filename1));
				  //file2 = new BufferedReader(new FileReader(filename2));
				  SecondFile = filename2;
				  //Variables.strWorkbook = "ST";
			  }
			  else
			  {
				  //Highestloopcount = file2count;
				  //AnotherFileCount = file1count;
				  file1 = new BufferedReader(new FileReader(filename2));
				  //file2 = new BufferedReader(new FileReader(filename1));
				  SecondFile = filename1;
				  //Variables.strWorkbook = "UAT";
			  }			  
		  
			  /*Variables.OutputBuffer += "\nNumber of records in file: "+ filename1+" = " + file1count;       // Full path and file name will be displayed
			  Variables.OutputBuffer += "\nNumber of records in file: "+ filename2+" = " + file2count+"\n";*/  // Full path and file name will be displayed

			  Variables.OutputBuffer += "\nNumber of records in file: "+ filename1.substring(30)+" = " + file1count;       // Only the last file name will be displayed
			  Variables.OutputBuffer += "\nNumber of records in file: "+ filename2.substring(30)+" = " + file2count+"\n";  // Only the last file name will be displayed
			  
			   file1_line = file1.readLine(); // Reading header, Ignoring in file 1
			  //String file2_line = file2.readLine(); // Reading header, Ignoring in file 2
			   ArrayList<String> mylist = new ArrayList<String>();
			  //while ((file1_line = file1.readLine()) != null && !file1_line.isEmpty() && (file2_line = file2.readLine())!= null) 
			  while((file1_line = file1.readLine()) != null)			  
			  { 
				  mismatch=0;
				  RowNum++;
				  String[] file1_fields = file1_line.split(",");
				  file2 = new BufferedReader(new FileReader(SecondFile));
				  file2_line = file2.readLine();  //Ignoring the heading row				  
				  //System.out.println("\nFile 2 Line: "+file2_line);
				  if (!Utils.foundStringInaList(mylist,file1_fields[2]))
					  mylist.add(file1_fields[2]);
				  if (!Utils.foundStringInaArray(Config.IgnoreFieldlist,file1_fields[2]))
				  {
					  while((file2_line = file2.readLine()) != null)
					  //if (mismatch==0)
					  {
						  //System.out.println("\nFile 2 Line: "+file2_line);					  
						  mismatch = 0;
						  String[] file2_fields = file2_line.split(",");
						  
						  if(file1_fields[2].equals(file2_fields[2]))
						  {
							  //Variables.OutputBuffer += "\nField Matched:"+file2_fields[2];
							  if (!file1_line.equals(file2_line))
							  {   
								  //mismatch = 0;					  
								  if (file1_line.charAt(file1_line.length() - 1) == ',')  //*** ignoring the last char (,) in downloaded file
									  mismatch = 0;
								  if (file1_fields.length != file2_fields.length)
									  mismatch=1;
								  else
								  {
									  for (i=0;i<file1_fields.length;i++)
									  {
										  //System.out.println("file1_fields= "+file1_fields[i]);							  
										  if (!file1_fields[i].equals(file2_fields[i]))
											  if (file1_line.indexOf("\"") != -1)  //*** ignoring the last char (") in downloaded file
												  mismatch = 0;
										  else		
											  mismatch = 1;	  
									  }
								  }
							  }
						  }
						  if (mismatch == 1)
						  {
							  //System.out.println("\nDifferences found: "+"\n"+file1_line+'\n'+file2_line);
							  Variables.OutputBuffer += "\nDifferences found: "+"\n"+file1_line+'\n'+file2_line+"\n";
							  Variables.notequalcolorlist.add(String.valueOf(RowNum));
							  Variables.duplicatecolorlist.add(String.valueOf(RowNum));	
							  //System.out.println("\nRownum= "+RowNum);
							 // Variables.notequalcolorlist.add(file1_fields[2]);
							 // Variables.duplicatecolorlist.add(file1_fields[2]);
							  NumberOfDifferences++;					  
						  }		
					  }
				  }
				  
				  //if (mismatch == 1 && (file1_line!=null || file2_line!=null))
				  file2.close();
			  } 
			  file1.close(); 
			  			  
			  if (NumberOfDifferences == 0)
			  {
		    	  //System.out.println("No Differences found in two files");
			  	  Variables.OutputBuffer += "No Differences found in two files";
			  	  return true;
			  }
			  else
			  {
				  //System.out.println("\nNumber of Differences found in two files: "+ (NumberOfDifferences));
				  Variables.OutputBuffer += "\nNumber of Differences found in two files = "+ (NumberOfDifferences);
			  	  return false;
			  }
	 	
	 }
	 public static boolean CompareCSVFiles_WORKING_Ver02(String filename1,String filename2) throws Exception //***WORKING WITH field number by changing the ROW number comparison and duplicates found.
	 { 
			  //List countries = new ArrayList<>(); 
			  int NumberOfDifferences=0,i,j; //AnotherFileCount=0,i,j,k;
			  int mismatch =0,file1count=0,file2count=0,RowNum=1;//Highestloopcount=0;		
			  //Variables.OutputBuffer="";
			  BufferedReader file1,file2,file1tmp,file2tmp;
			  String SecondFile,file1_line,file2_line,New_file2_line = null,New_file1_line=null,New_file1_Field = "",New_file2_Field="";
			  boolean Change_of_Line = true,isOldFieldNumberAlreadyFound = false,isCurrFieldNumberAlreadyFound=false;
			  String[] file2_fields;
			  //Variables.notequalcolorlist=null;
			  //Variables.duplicatecolorlist=null;
			  
			  file1tmp = new BufferedReader(new FileReader(filename1));
			  file2tmp = new BufferedReader(new FileReader(filename2));
			 
			  while(file1tmp.readLine()!= null)
			      file1count++;
			  while(file2tmp.readLine()!= null)
			      file2count++;		
			  
			  if (file1count >= file2count)
			  {
				  //Highestloopcount = file1count;
			      //AnotherFileCount = file2count;
			      file1 = new BufferedReader(new FileReader(filename1));
				  //file2 = new BufferedReader(new FileReader(filename2));
				  SecondFile = filename2;
				  //Variables.strWorkbook = "ST";
			  }
			  else
			  {
				  //Highestloopcount = file2count;
				  //AnotherFileCount = file1count;
				  file1 = new BufferedReader(new FileReader(filename2));
				  //file2 = new BufferedReader(new FileReader(filename1));
				  SecondFile = filename1;
				  //Variables.strWorkbook = "UAT";
			  }			  
		  
			  /*Variables.OutputBuffer += "\nNumber of records in file: "+ filename1+" = " + file1count;       // Full path and file name will be displayed
			  Variables.OutputBuffer += "\nNumber of records in file: "+ filename2+" = " + file2count+"\n";*/  // Full path and file name will be displayed

			  Variables.OutputBuffer += "\nNumber of records in file: "+ filename1.substring(30)+" = " + file1count;       // Only the last file name will be displayed
			  Variables.OutputBuffer += "\nNumber of records in file: "+ filename2.substring(30)+" = " + file2count+"\n";  // Only the last file name will be displayed
			
			  //System.out.println("No.of Records in File 1=" + file1count);       // Only the last file name will be displayed
			  //System.out.println("No.of Records in File 2=" + file2count); 
			
			   file1_line = file1.readLine(); // Reading header, Ignoring in file 1
			  //String file2_line = file2.readLine(); // Reading header, Ignoring in file 2
			   ArrayList<String> mylist = new ArrayList<String>();
			  //while ((file1_line = file1.readLine()) != null && !file1_line.isEmpty() && (file2_line = file2.readLine())!= null) 
			  file2 = new BufferedReader(new FileReader(SecondFile));
			  file2_line = file2.readLine();  //Ignoring the heading row		  
		
			  while((file1_line = file1.readLine()) != null)			  
			  { 				  
				  RowNum++;
				  String[] file1_fields = file1_line.split(",");
				  //String Previous_Line = file1_line;
				  //System.out.println("\nFile 2 Line: "+file2_line);
				  	
				  isCurrFieldNumberAlreadyFound = Utils.foundStringInaList(mylist,file1_fields[2]);				  		  
				  //isOldFieldNumberAlreadyFound = Utils.foundStringInaList(mylist,New_file1_Field);
				  				  				  
				  //if(!Change_of_Line && !isOldFieldNumberAlreadyFound)
				  //if((!Change_of_Line && !isOldFieldNumberAlreadyFound) && (!Change_of_Line && !isCurrFieldNumberAlreadyFound))
				  if(!Change_of_Line && !isCurrFieldNumberAlreadyFound && (!file1_fields[2].equals(New_file2_Field)))
				  {
				  	  do{
				  		  file2_line = file2.readLine();
				  		  file2_fields = file2_line.split(",");
				  		  //System.out.println("\nI am in do while");
				  	  }while(!file1_fields[2].equals(file2_fields[2])&& (file2_line!=null));
						  
				  }				  
				  //else if((Change_of_Line && !isFieldNumberAlreadyFound) || (Change_of_Line && isFieldNumberAlreadyFound))
				  else if(Change_of_Line)
				  {
					  file2_line = file2.readLine();
					  //System.out.println("\nI am in one line advance read");
				  }				  
				  else if (!Change_of_Line)
				  {
					  file2_line = New_file2_line;
					  //System.out.println("\nI am Not changed line number");
				  }
				 // else if(!Change_of_Line && isFieldNumberAlreadyFound)
				  
				  
				 /* if(Change_of_Line)
					  file2_line = file2.readLine();				  
				  else
					  file2_line = New_file2_line;	*/		
					  
				  //System.out.println("File 1 Line: "+file1_line);
				  //System.out.println("File 2 Line: "+file2_line);
				  //System.out.println("File 1 Line: "+file1_line);
				  mismatch=0;
				  if (!Utils.foundStringInaArray(Config.IgnoreFieldlist,file1_fields[2]))
				  {
					  //while((file2_line = file2.readLine()) != null)
					  //System.out.println("I am in if");
					  while(file2_line != null)
					  //if (mismatch==0)
					  {
						  //System.out.println("I am in second while");
						  //System.out.println("\nFile 2 Line: "+file2_line);					  
						  mismatch = 0;
						  file2_fields = file2_line.split(",");
						  New_file1_line  = file1_line;
						  New_file1_Field = file1_fields[2];
						  New_file2_Field = file2_fields[2];
						  //isOldFieldNumberAlreadyFound = Utils.foundStringInaList(mylist,New_file1_Field);	
						  New_file2_line =  file2_line;
				
						  if(file1_fields[2].equals(file2_fields[2]))
						  {
							  //System.out.println("I am in second if");
							  //Variables.OutputBuffer += "\nField Matched:"+file2_fields[2];
							  if (!file1_line.equals(file2_line))
							  {   
								  //mismatch = 0;
								  //System.out.println("I am in 3rd if");
								  if (file1_line.charAt(file1_line.length() - 1) == ',')  //*** ignoring the last char (,) in downloaded file
									  mismatch = 0;
								  if (file1_fields.length != file2_fields.length)
								  	  mismatch=1;
									  
								  else
								  {
									  for (i=0;i<file1_fields.length;i++)
									  {
										  //System.out.println("I am in for");
										  //System.out.println("file1_fields= "+file1_fields[i]);							  
										  if (!file1_fields[i].equals(file2_fields[i]))
											  if (file1_line.indexOf("\"") != -1)  //*** ignoring the last char (") in downloaded file
												  mismatch = 0;
										  else
											  mismatch = 1;										
									  }
								  }
							  }
						  }
						  else
						  {
							  /*New_file1_line  = file1_line;
							  New_file1_Field = file1_fields[2];
							  New_file2_Field = file2_fields[2];
							  //isOldFieldNumberAlreadyFound = Utils.foundStringInaList(mylist,New_file1_Field);	
							  New_file2_line =  file2_line;*/
							  Variables.OutputBuffer += "\nDifferences found: "+"\n"+file1_line+'\n'+file2_line+"\n";
							  Variables.notequalcolorlist.add(String.valueOf(RowNum));
							  Variables.duplicatecolorlist.add(String.valueOf(RowNum));	
							  NumberOfDifferences++;
							  //System.out.println("Diff= "+NumberOfDifferences);
							  //System.out.println("I am in not equal field num");
							  Change_of_Line = false;
							  break;
						  }
						  if (mismatch == 1)
						  {
							  //System.out.println("\nDifferences found: "+"\n"+file1_line+'\n'+file2_line);
							  Variables.OutputBuffer += "\nDifferences found: "+"\n"+file1_line+'\n'+file2_line+"\n";
							  Variables.notequalcolorlist.add(String.valueOf(RowNum));
							  Variables.duplicatecolorlist.add(String.valueOf(RowNum));	
							  //System.out.println("\nRownum= "+RowNum);
							 // Variables.notequalcolorlist.add(file1_fields[2]);
							 // Variables.duplicatecolorlist.add(file1_fields[2]);
							  NumberOfDifferences++;			
							  //System.out.println("I am in mismatch record");
							  Change_of_Line = true;
							  break;
						  }		
						  else
						  {
							  Change_of_Line = true;
							  //System.out.println("I am in matching record");
							  break;
						  }
					  }//second file while close loop
					  
					  isOldFieldNumberAlreadyFound = Utils.foundStringInaList(mylist,New_file1_Field);
					  //System.out.println("Old Field Num= "+New_file1_Field);
					  //System.out.println("Old Field Yes/NO= "+isOldFieldNumberAlreadyFound);
					  //isCurrFieldNumberAlreadyFound = Utils.foundStringInaList(mylist,file1_fields[2]);	
					  if (!isCurrFieldNumberAlreadyFound)
						  mylist.add(file1_fields[2]);
			
				  }//Ignore list closing if clause
				
			  } //First file while close loop
			  
			  file1.close(); 
			  file2.close();
			  
			  if (NumberOfDifferences == 0)
			  {
		    	  //System.out.println("No Differences found in two files");
			  	  Variables.OutputBuffer += "No Differences found in two files";
			  	  return true;
			  }
			  else
			  {
				  //System.out.println("\nNumber of Differences found in two files: "+ (NumberOfDifferences));
				  Variables.OutputBuffer += "\nNumber of Differences found in two files = "+ (NumberOfDifferences);
			  	  return false;
			  }
	 	
	 }
	 @SuppressWarnings("resource")
	public static boolean CompareCSVFiles(String filename1,String filename2) throws Exception //***WORKING DONE - To sort out the extra fields(Not added in converted CSV) "Section 0,1,2,3,..etc in MS downloaded CSV file
	 {	                                                                                      //***WORKING DONE - If any records missing in Exported and in Converted file, will capture in report 
			  int NumberOfDifferences=0,i,j;
			  int mismatch =0,file1count=0,file2count=0,RowNum=1;		
	
			  BufferedReader file1,file2,file1tmp,file2tmp;
			  String SecondFile,file1_line,file2_line,New_file2_line = null,New_file1_line=null,New_file1_Field = "",New_file2_Field="";
			  boolean Change_of_Line = true,isOldFieldNumberAlreadyFound = false,isCurrFieldNumberAlreadyFound=false;
			  String[] file2_fields;
			  ArrayList<String> mylist = new ArrayList<String>();
			  
			  file1tmp = new BufferedReader(new FileReader(filename1));
			  file2tmp = new BufferedReader(new FileReader(filename2));
			 
			  while(file1tmp.readLine()!= null)
			      file1count++;
			  while(file2tmp.readLine()!= null)
			      file2count++;	
			  file1tmp.close();
			  file2tmp.close();
			  
			  Variables.OutputBuffer += "\nNumber of records in file: "+ filename1.substring(26)+" = " + file1count;       // Only the last file name will be displayed
			  Variables.OutputBuffer += "\nNumber of records in file: "+ filename2.substring(26)+" = " + file2count+"\n";  // Only the last file name will be displayed
	
			  file1 = new BufferedReader(new FileReader(filename1));
			  SecondFile = filename2;			  
			  file1_line = file1.readLine(); // Reading header, Ignoring in file 1
			  
			  file2 = new BufferedReader(new FileReader(SecondFile));
			  file2_line = file2.readLine();  //Reading header, Ignoring in file 2		  
		
			  while((file1_line = file1.readLine()) != null)			  
			  { 				  
				  RowNum++;
				  String[] file1_fields = file1_line.split(",");				  	
				  isCurrFieldNumberAlreadyFound = Utils.foundStringInaList(mylist,file1_fields[2]);
				  /*if(file1_fields[2].equals("189.1"))
					  System.out.println("Value 1= "+Change_of_Line+" Value2= "+isCurrFieldNumberAlreadyFound+" Value3= "+New_file2_Field);*/
				  //***To change and set the file reader in file 2(Converted CSV file) 
				  if((!Change_of_Line && !isCurrFieldNumberAlreadyFound) && (!file1_fields[2].equals(New_file2_Field)))
				  {
				  	  do{
				  		  file2_line = file2.readLine();
				  		  file2_fields = file2_line.split(",");
				  		  //System.out.println("\nI am in do while");				  		  
				  	  }while(!file1_fields[2].equals(file2_fields[2])&& (file2_line!=null));
						  
				  }
				  else if(Change_of_Line)
				  {
					  file2_line = file2.readLine();					
				  }				  
				  else if (!Change_of_Line)
				  {
					  file2_line = New_file2_line;					  
				  }	
				  
				  mismatch=0;
				  if (!Utils.foundStringInaArray(Config.IgnoreFieldlist,file1_fields[2]))
				  {					  
					  if(file2_line != null)				
					  {
						  mismatch = 0;						  
						  New_file1_line  = file1_line;
						  New_file1_Field = file1_fields[2];
						  file2_fields = file2_line.split(",");
						  New_file2_Field = file2_fields[2];						 	
						  New_file2_line =  file2_line;
				
						  if(file1_fields[2].equals(file2_fields[2]))
						  {
							  if (!file1_line.equals(file2_line))
							  {   
								  if (file1_line.charAt(file1_line.length() - 1) == ',')  //*** ignoring the last char (,) in downloaded file
									  mismatch = 0;
								  //if (file1_fields.length != file2_fields.length)
								  if ((file1_fields.length != file2_fields.length) && Variables.DocumentType.equals(Config.MRC_DocumentType_MS))
								  {
									  int file1_num_of_fields=0,file2_num_of_fields=0,Loop_file_num_of_fields=0;
									  mismatch=1;
									  j=0;
									  //System.out.println("len= "+file1_fields.length);
									  if(file1_fields.length > file2_fields.length)
									  {
										  file1_num_of_fields = file1_fields.length;
										  Loop_file_num_of_fields = file1_num_of_fields;
									  }
									  else 
									  {
										  file2_num_of_fields = file2_fields.length;
										  Loop_file_num_of_fields = file2_num_of_fields;
									  }
									  
									  for (i=0;i<Loop_file_num_of_fields;i++)
									  {										  
										  try{
										  if(!Utils.foundStringInaArray(Config.IgnoreSectionlist,file1_fields[i]))   //***Ignoring the (Section 0/1/2..) columns in downloaded CSV file
										  {											                                //*** Need to be fixed for MS MRC. Converted CSV file not generating properly.
											 try{
											  if (!file1_fields[i].equals(file2_fields[j]))
											  { 
												  boolean tested = false;
												  //*** Verify and ignore the "(Section 0)" text added in the heading for MS MRC.
												  if(file2_fields[1].contains("("))
												  {
													  tested = true;
													  String tempsection = file2_fields[1].substring(file2_fields[1].indexOf("(")-1,file2_fields[1].indexOf(")")+1);
													  String newHeading = file2_fields[1].replace(tempsection, "");
													  //System.out.println("text1="+file2_fields[1]);
													  //System.out.println("text2="+tempsection);
													  //System.out.println("text3="+newHeading);
													  if (!file1_fields[1].equals(newHeading)){mismatch=1; break;}
												  }	//*** Verify and ignore the "(Section 0)" text added in the heading for MS MRC.
												  if(!tested){
												    mismatch=1;
												    break;}
											  }											  
											  else
												  mismatch=0;
											 }catch(Exception e){ mismatch=1; break;}
											  j++;
										  }
										  else										 							  
											 continue;	
										  }catch(Exception e){ mismatch=1; break;}
									  }
								  }
								  else if (file1_fields.length == file2_fields.length)
								  {
									  for (i=0;i<file1_fields.length;i++)
									  {
										  if (!file1_fields[i].equals(file2_fields[i]))
											  if (file1_line.indexOf("\"") != -1)  //*** ignoring the last char (") in downloaded file
												  mismatch = 0;
										      else
											      mismatch = 1;										
									  }
								  }
								  else
									  mismatch =1;
							  }
						  }
						  else
						  {
							  Variables.OutputBuffer += "\nMismatch record in Sequencing Order found: "+"\n"+file1_line+'\n'+file2_line+"\n";
							  Variables.notequalcolorlist.add(String.valueOf(RowNum));
							  Variables.duplicatecolorlist.add(String.valueOf(RowNum));	
							  NumberOfDifferences++;
							  Change_of_Line = false;
							  isOldFieldNumberAlreadyFound = Utils.foundStringInaList(mylist,New_file1_Field);
							  if (!isCurrFieldNumberAlreadyFound)
								  mylist.add(file1_fields[2]);
			
							  continue;
							  //break;
						  }
						  if (mismatch == 1)
						  //if (mismatch == 1 && file1_fields[2].equals(file2_fields[2]))
						  {							  
							  Variables.OutputBuffer += "\nDifferences found: "+"\n"+file1_line+'\n'+file2_line+"\n";
							  Variables.notequalcolorlist.add(String.valueOf(RowNum));
							  Variables.duplicatecolorlist.add(String.valueOf(RowNum));	
							  NumberOfDifferences++;
							  Change_of_Line = true;
							  //break;
						  }		
						  else
						  {
							  Change_of_Line = true;							
							  //break;
						  }
					  }//second file if not null close
					  
					  isOldFieldNumberAlreadyFound = Utils.foundStringInaList(mylist,New_file1_Field);
					  if (!isCurrFieldNumberAlreadyFound)
						  mylist.add(file1_fields[2]);
			
				  }//Ignore list closing if clause
				
			  } //First file while close loop
			  
			  file1.close(); 
			  file2.close();
			  
			  if (NumberOfDifferences == 0)
			  {
		    	  //System.out.println("No Differences found in two files");
			  	  Variables.OutputBuffer += "\nNo Differences found in two files.";
			  	  return true;
			  }
			  else
			  {
				  //System.out.println("\nNumber of Differences found in two files: "+ (NumberOfDifferences));
				  Variables.OutputBuffer += "\nNumber of Differences found in two files = "+ (NumberOfDifferences);
			  	  return false;
			  }
	 	
	 }
	 //public static void convertTextFileToCSV(String txtFile,String NewCSVFile) throws Exception   //*** IN progress
	 //public static void convertTextFileToCSV(String txtFile) throws Exception   //*** IN progress
	 //public static void convertTextFileToCSV(String txtFile) throws Exception   //*** IN progress
	 public static boolean convertTextFileToCSV(String InputTxtFile,String OutputCSVFile) throws Exception   //*** IN progress
	 {	  	  
		 	boolean FileConverted = false;
		 	
		 	FileWriter writer = null;
	        //File file = new File("C:\\TEMP\\txtfile.txt");
	        File file1 = new File(InputTxtFile);
	        Scanner scan = new Scanner(file1);
	        File file2 = new File(OutputCSVFile);
	        file1.createNewFile();
	        writer = new FileWriter(file2);

	        while (scan.hasNext()) 
	        {
	            String csv = scan.nextLine().replace("  ", "\n");
	            //System.out.println(csv);
	            writer.append(csv);
	            writer.append("\n");
	            writer.flush();
	        }
	        //file.exists()
	        if (file1.exists() && file2.exists())
	        	FileConverted = true;
	       
	        return FileConverted;
	 }	 
	 public static void sendEmailWithAttachment() {
		 
		// Create object of Property file
			Properties props = new Properties();
	 
			// this will set host of server- you can change based on your requirement 
			props.put("mail.smtp.host", "smtp.gmail.com");
		    //props.put("mail.smtp.host", "smtp-mail.outlook.com");			
			props.put("mail.smtp.starttls.enable", "true");	 
			// set the port of socket factory 
			props.put("mail.smtp.socketFactory.port", "465");	 
			// set socket factory
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");	 
			// set the authentication to true
			props.put("mail.smtp.auth", "true");	 
			// set the port of SMTP server
			//props.put("mail.smtp.port", "465");
			props.put("mail.smtp.port", "587");
	 
			// This will handle the complete authentication
			//Session session = Session.getDefaultInstance(props,
			Session session = Session.getInstance(props,
	 
					new javax.mail.Authenticator() {
	 
						protected PasswordAuthentication getPasswordAuthentication() {
	 					
						//return new PasswordAuthentication("SDC_Automation@outlook.com", "SDCLogin123$");
						return new PasswordAuthentication(Config.Email_Sender, Config.Email_Password);
	 
						}
	 
					});
					//session.setDebug(true);
			try {
	 
				// Create object of MimeMessage class
				Message message = new MimeMessage(session);	 
				// Set the from address
				message.setFrom(new InternetAddress(Config.Email_Sender));			
				//message.setFrom(new InternetAddress("SDC_Automation@outlook.com"));	 
				// Set the recipient address
				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(Config.Email_Recipients));   ///Receiver address	            
	            // Add the subject link
				message.setSubject(Config.Email_Subject);
	 
				// Create object to add multimedia type content
				BodyPart messageBodyPart1 = new MimeBodyPart();	 
				// Set the body of email
				messageBodyPart1.setText("Automation Team is sending this email to advise you that there has been a successful completion of the Automation Test and is detailed with the attachement files for any further references.");					 
				// Create another object to add another content
				MimeBodyPart messageBodyPart2 = new MimeBodyPart();	 
				// Mention the file which you want to send
				//String filename = Config.Path_EmailReport ;
				String filename = Config.ZipFileName ;	 
				// Create data source and pass the filename
				DataSource source = new FileDataSource(filename);	 
				// set the handler
				messageBodyPart2.setDataHandler(new DataHandler(source));	 
				// set the file
				filename = Config.File_EmailReport; 
				messageBodyPart2.setFileName(filename);
				
				
				// Create another object to add another content
				MimeBodyPart messageBodyPart3 = new MimeBodyPart(); 
				// Mention the file which you want to send
				String filename2 = Config.Path_EmailReport2 ; 
				// Create data source and pass the filename
				DataSource source2 = new FileDataSource(filename2); 
				// set the handler
				messageBodyPart3.setDataHandler(new DataHandler(source2)); 
				filename2 = Config.File_EmailReport2; 
				messageBodyPart3.setFileName(filename2);
				
	 
				// Create object of MimeMultipart class
				Multipart multipart = new MimeMultipart();	 
				// add body part 1
				multipart.addBodyPart(messageBodyPart3);				
				// add body part 2
				multipart.addBodyPart(messageBodyPart2);	 
				// add body part 3
				multipart.addBodyPart(messageBodyPart1);				
	 
				// set the content
				message.setContent(multipart);
	 
				// finally send the email
				Transport.send(message);
	 
				System.out.println("\n=====Test Report Email Sent Successfully=====");
				Log.info("=====Test Report Email Sent Successfully=====");
				Reporter.log("\n=====Test Report Email Sent Successfully=====");
	 
			} catch (MessagingException e) { 
				throw new RuntimeException(e); 
			}
		}	 
	 public static boolean foundStringInaList(ArrayList<String> list,String SearchStr)
	 {
		 boolean StringFound=false;
		 		 
		 //List <String> listClone = new ArrayList<String>(); 
         for (String string : list) 
         {
             if(string.matches(SearchStr))
                 //listClone.add(string);
                 StringFound = true;
         }
		 //System.out.println("String found: "+StringFound);
         return StringFound;     
		  
	 }
	 public static boolean foundStringInaArray(String [] array,String SearchStr)      
	 {
		 boolean IgnoreListFound=false;
		 		 
		 //List <String> listClone = new ArrayList<String>(); 
         for (int i=0; i<array.length; i++) 
         {
             if(array[i].equals(SearchStr))
             {                 
            	 IgnoreListFound = true;
                 break;
             }
         }
		 //System.out.println("String found: "+StringFound);
         return IgnoreListFound;     
		  
	 }
	 public static boolean rightClickElementandSave(WebElement element, String Env,String Path,String OutputFileName) throws UnsupportedFlavorException, IOException, AWTException, InterruptedException
	 {
		StringSelection ss;
		String strFileName;
		Actions action = new Actions(driver);
		
		action.contextClick(element).build().perform();
		Thread.sleep(1000);
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
			
		//Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		//Transferable contents = clipboard.getContents(null);
		//String x = (String) contents.getTransferData(DataFlavor.stringFlavor);
		//return robot;
		
		if (Env.equalsIgnoreCase("ST"))
		{
			ss = new StringSelection(Path+"Exported-ST-"+OutputFileName);
			strFileName = Path+"Exported-ST-"+OutputFileName;
		}
		else
		{
			ss = new StringSelection(Path+"Exported-UAT-"+OutputFileName);
			strFileName = Path+"Exported-UAT-"+OutputFileName;
		}
		
		Thread.sleep(3000);
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(500);
		if (Utils.isFileExist(strFileName))
		{
			//System.out.println("file exported");
			return true;
		}
		else
		{
			//System.out.println("file NOT exported");
			return false;
		}
			
		
		//System.out.println("\nFile exported");
			
	 }	 
	 public static boolean downloadWebPageFiles(WebElement element, String Env,String Path,String OutputFileName) throws UnsupportedFlavorException, IOException, AWTException, InterruptedException
	 {
		
		String strFileName;
			
		if (Env.equalsIgnoreCase("ST"))
			strFileName = Path+"Exported-ST-"+OutputFileName;
			//System.out.println("Path="+strFileName);
		
		else
			strFileName = Path+"Exported-UAT-"+OutputFileName;
			//System.out.println("Path="+strFileName);
			
		element.click();
		Thread.sleep(2000);
		Utils.CopyFile(strFileName);

		if (Utils.isFileExist(strFileName))
		{
			//System.out.println("file exported");
			return true;
		}
		else
		{
			//System.out.println("file NOT exported");
			return false;
		}
			
		
		//System.out.println("\nFile exported");
			
	 }	 
	 public static void setMRCType(String MRCType)
	 {
		   if (Variables.DocumentType.equals("")&& MRCType.equals("EWSS"))
			   Variables.DocumentType = Config.MRC_DocumentType_EWSS;
		   else if (Variables.DocumentType.equals("")&& MRCType.equals("SS"))
			   Variables.DocumentType = Config.MRC_DocumentType_SS;
		   else if (Variables.DocumentType.equals("")&& MRCType.equals("EO"))
			   Variables.DocumentType = Config.MRC_DocumentType_EO;
		   else if (Variables.DocumentType.equals("")&& MRCType.equals("MS"))
			   Variables.DocumentType = Config.MRC_DocumentType_MS;
		 		
		   if(Variables.intNumOfMRCSections==0)
			   Variables.intNumOfMRCSections = 6;
		
	 }
	 
	 public static void createZipFile(String sourceFile, String zipOutFileName) throws IOException 
	 {
	      //String sourceFile = "C:\\TEMP\\OutputFiles";
	      FileOutputStream fos = new FileOutputStream(zipOutFileName);
	      ZipOutputStream zipOut = new ZipOutputStream(fos);
	      File fileToZip = new File(sourceFile);
	      zipFile(fileToZip, fileToZip.getName(), zipOut);	      
	      zipOut.close();
	      Log.info("--Output Files Zipped successfully");
	      fos.close();
	 }

     public static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException 
	  //private static void zipFile(String sourceFile, String zipOutFileName) throws IOException
	  {		      
		  if (fileToZip.isHidden()) 
	          return;
	     
	      if (fileToZip.isDirectory()) 
	      {
	          if (fileName.endsWith("/")) {
	              zipOut.putNextEntry(new ZipEntry(fileName));
	              zipOut.closeEntry();
	          } else {
	              zipOut.putNextEntry(new ZipEntry(fileName + "/"));
	              zipOut.closeEntry();
	          }
	          File[] children = fileToZip.listFiles();
	          for (File childFile : children) {
	              zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
	          }
	          return;
	      }
	      FileInputStream fis = new FileInputStream(fileToZip);
	      ZipEntry zipEntry = new ZipEntry(fileName);
	      zipOut.putNextEntry(zipEntry);
	      byte[] bytes = new byte[1024];
	      int length;
	      while ((length = fis.read(bytes)) >= 0) {
	          zipOut.write(bytes, 0, length);
	      }
	      fis.close();
	      
	  }
     public static boolean CreateADirectory(String DirectoryName)
	 {
	        //project directory
	        //String workingDirectory = System.getProperty("user.dir");
	        //String  dir = workingDirectory + File.separator + DirectoryName;

		  String  dir = DirectoryName;  
		  //System.out.println("Final file directory : " + dir);      

	        File file = new File(dir);

	        if (!file.exists()) {
	            file.mkdir();
	            //System.out.println("Directory is created!");
	            return true;
	        } else {
	            //System.out.println("Directory is already existed!");
	        	return false;
	        }

	  }
     
     public static boolean isFileExist(String FileName)
	 {
	        //project directory
	        //String workingDirectory = System.getProperty("user.dir");
	        //String  dir = workingDirectory + File.separator + DirectoryName;

		    String  dir = FileName;  
		  //System.out.println("Final file directory : " + dir);      

	        File file = new File(dir);

	        if (!file.exists()) {
	            //file.mkdir();
	            //System.out.println("Directory is created!");
	            return false;
	        } else {
	            //System.out.println("Directory is already existed!");
	        	return true;
	        }

	  }
     public static long startTimer()
 	 {
    	//FluentWaitNew objWait = new FluentWaitNew(); 
    	Variables.startTime = 0;
      	Variables.startTime = System.currentTimeMillis(); 
      	return Variables.startTime;
 	 }
     public static long stopTimer()
 	 {
    	Variables.stopTime = 0;
    	Variables.elapsedTime = 0;
    	Variables.stopTime = System.currentTimeMillis();
	    Variables.elapsedTime = (Variables.stopTime - Variables.startTime) / 60000;
	    return Variables.elapsedTime;
 	 }
     public static void deleteFolder(String FolderName) 
     {
         //String folder = "/Users/pankaj/tmp";
         //delete folder recursively
         recursiveDelete(new File(FolderName));
     }
     
     public static void recursiveDelete(File file)
     {
         //to end the recursive loop
         if (!file.exists())
             return;
         
         //if directory, go inside and call recursively
         if (file.isDirectory()) {
             for (File f : file.listFiles()) {
                 //call recursively
                 recursiveDelete(f);
             }
         }
         //call delete to delete files and empty directory
         file.delete();
         Log.info("All the files and folders deleted under the given path: "+Config.DIR_Child);
         //System.out.println("Deleted file/folder: "+file.getAbsolutePath());
     }
     public static void maintainDirectoryStructure() throws IOException
     {    	
    	//Utils.deleteFolder(Config.DIR_Child);  //***** To delete the Output File folder
    	
    	if(Utils.CreateADirectory(Config.DIR_OutputSource))
			Log.info("--Source Directory Created");
		else
			Log.info("--Source Directory Already Existed");
	     	
    	
    	//System.out.println("\nString Parent File= "+Config.DIR_Parent);
    	//System.out.println("\nString Child File= "+Config.DIR_Child);
    	
		if(Utils.CreateADirectory(Config.DIR_Child))		
			Log.info("--Child Directory Created");
		else
			Log.info("--Child Directory Already Existed");		
		
		if(Utils.CreateADirectory(Config.DIR_Fields))
			Log.info("--MRC Field Directory Created");
		else
			Log.info("--MRC Field Directory Already Existed");
		
		if(Utils.CreateADirectory(Config.DIR_XML))
			Log.info("--XML Directory Created");
		else
			Log.info("--XML Directory Already Existed");	
		
		if(Utils.CreateADirectory(Config.DIR_Screenshot))
			Log.info("--Screenshot Directory Created");
		else
			Log.info("--Screenshot Directory Already Existed");
		
		if(Utils.CreateADirectory(Config.DIR_ZipFiles))
			Log.info("--ZipFiles Directory Created");
		else
			Log.info("--ZipFiles Directory Already Existed");
		
		if(Utils.reCreateFile(Config.logFileSource))
			Log.info("--LogFile re-created");
		else
			Log.info("--LogFile not created successfully");
	
		
     }
     
     public static boolean CopyReportFiles() throws IOException
   	 {
    	 /*File SourceFilename1 = new File("C:\\Automation\\SDC\\test-output\\emailable-report.html");
    	 File SourceFilename2 = new File("C:\\Automation\\SDC\\test-output\\index.html");
    	 File SourceFilename3 = new File("C:\\Automation\\SDC\\logfile.log");*/
    	 
    	 File SourceFilename1 = new File(Config.htmlReportFileSource);
    	 File SourceFilename2 = new File(Config.htmlReportIndexFileSource);
    	 File SourceFilename3 = new File(Config.logFileSource);
    	 
    	 //File DestFilename1 = new File(Config.DIR_Child +"\\"+ "emailable-report.html"); 
    	 File DestFilename1 = new File(Config.DIR_Child +"\\"+ "customized-emailable-report.html");    	 
    	 File DestFilename2 = new File(Config.DIR_Child +"\\"+ "index.html");
    	 File DestFilename3 = new File(Config.DIR_Child +"\\"+ "logfile.log");
    	 //File DestFilename = new File(Config.DIR_Child);
    	 FileUtils.copyFile(SourceFilename1,DestFilename1);
    	 FileUtils.copyFile(SourceFilename2,DestFilename2);
    	 FileUtils.copyFile(SourceFilename3,DestFilename3);
   	      
    	 if ((!DestFilename1.exists()) || (!DestFilename2.exists()) || (!DestFilename3.exists()))	           
	            return false;
	     else
	     {
	    	 //Log.info("--Report files and log files copied to output directory");
	    	 return true;
	     }
   	 }     
     
     //public static boolean CopyFile(String sourceDir,String destDir) throws IOException
     public static void CopyFile(String destFilewithPath) throws IOException
   	 {
     	 //File file = new File("C:\\SDC_Automation_Outputs\\OutputFiles-EO");
    	 String srcFile = "";
    	 File file = new File(Config.DIR_Child);
         String[] fileList = file.list();
         for(String name:fileList)
         {   
             if(name.contains("."))
             {
             	srcFile = name;
             	//System.out.println(name);
             	//System.out.println(srcFile);
             }
         }
         
    	 //String sourceDir = "C:\\SDC_Automation_Outputs\\OutputFiles-EO\\Extracted-380bfaaa-ec2d-47fb-8595-271dea0cee0f.csv";
     	 String sourceFile = Config.DIR_Child+"\\"+srcFile;
     	 //String destFile = "C:\\SDC_Automation_Outputs\\OutputFiles-EO\\MRCFields\\"+"Exported-ST-"+"OutputFileName.csv";
     	 String destFile = destFilewithPath;
     	 //System.out.println("SourceFile= "+sourceFile);
     	 //System.out.println("DestinationFile= "+destFile);
     	 File SourceFilename = new File(sourceFile); 	    		 
     	 File DestFilename = new File(destFile);
     	 FileUtils.copyFile(SourceFilename,DestFilename); 
     	 SourceFilename.delete();
	     
   	 }
  
     public static boolean reCreateFile(String Filename) throws IOException
   	 {
	     File file = new File(Filename); 
	     //File file = new File("C:\\SDC_Automation_Input and Outputs\\OutputFiles-EWSS\\logfile.log");
	
	     if (file.exists()) 
	          file.delete();	     
	     file.createNewFile();
	     
	     if (!file.exists())
	    	 return false;
	     else
	    	 return true;	    			 
   	 }
     
     public static void main(String[] args) throws Exception
     {
    	 //Utils.CompareCSVFiles(Config.Path_MRCFieldOutput+"Exported-"+Config.File_CSVExportSTFields,Config.Path_MRCFieldOutput+"Converted-"+Config.File_CSVExportSTFields);
    	 //Utils.writeFileCompareOutput(Config.Path_MRCFieldOutput,"Downloaded-Vs-OnPage-"+"ST"+"-"+Config.File_CompareOutput,Variables.OutputBuffer);
    	 //Utils.convertTextFileToCSV("C:\\TEMP\\txtfile.txt");
    	 //CopyReportFiles();
    	 //reCreateFile("C:\\Automation\\SDC\\logfile.log");
    	 
    	 /*File myXLSXFile;
    	 myXLSXFile = new File("C:\\SDC_Automation_Outputs\\OutputFiles-MS\\MRCFields\\ST-Page-Fields.xlsx");
    	 Utils.convertXLSXFileToCSV(myXLSXFile,"C:\\SDC_Automation_Outputs\\OutputFiles-MS\\MRCFields\\Converted-ST-Page-Fields-CSV.csv");*/
    	 
    	 //CopyFile("C:\\SDC_Automation_Outputs\\OutputFiles-EO\\MRCFields\\Exported-ST-Page-Fields-CSV.csv");
     }
	  
}
